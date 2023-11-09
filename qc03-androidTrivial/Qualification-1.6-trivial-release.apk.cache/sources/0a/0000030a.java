package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/* loaded from: classes.dex */
public final class NavUtils {
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";

    public static boolean shouldUpRecreateTask(Activity activity, Intent intent) {
        if (Build.VERSION.SDK_INT >= 16) {
            return Api16Impl.shouldUpRecreateTask(activity, intent);
        }
        String action = activity.getIntent().getAction();
        return (action == null || action.equals("android.intent.action.MAIN")) ? false : true;
    }

    public static void navigateUpFromSameTask(Activity activity) {
        Intent parentActivityIntent = getParentActivityIntent(activity);
        if (parentActivityIntent == null) {
            throw new IllegalArgumentException("Activity " + activity.getClass().getSimpleName() + " does not have a parent activity name specified. (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data>  element in your manifest?)");
        }
        navigateUpTo(activity, parentActivityIntent);
    }

    public static void navigateUpTo(Activity activity, Intent intent) {
        if (Build.VERSION.SDK_INT >= 16) {
            Api16Impl.navigateUpTo(activity, intent);
            return;
        }
        intent.addFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }

    public static Intent getParentActivityIntent(Activity activity) {
        Intent parentActivityIntent;
        if (Build.VERSION.SDK_INT < 16 || (parentActivityIntent = Api16Impl.getParentActivityIntent(activity)) == null) {
            String parentActivityName = getParentActivityName(activity);
            if (parentActivityName == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, parentActivityName);
            try {
                if (getParentActivityName(activity, componentName) == null) {
                    return Intent.makeMainActivity(componentName);
                }
                return new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e(TAG, "getParentActivityIntent: bad parentActivityName '" + parentActivityName + "' in manifest");
                return null;
            }
        }
        return parentActivityIntent;
    }

    public static Intent getParentActivityIntent(Context context, Class<?> cls) throws PackageManager.NameNotFoundException {
        String parentActivityName = getParentActivityName(context, new ComponentName(context, cls));
        if (parentActivityName == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(context, parentActivityName);
        if (getParentActivityName(context, componentName) == null) {
            return Intent.makeMainActivity(componentName);
        }
        return new Intent().setComponent(componentName);
    }

    public static Intent getParentActivityIntent(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String parentActivityName = getParentActivityName(context, componentName);
        if (parentActivityName == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), parentActivityName);
        if (getParentActivityName(context, componentName2) == null) {
            return Intent.makeMainActivity(componentName2);
        }
        return new Intent().setComponent(componentName2);
    }

    public static String getParentActivityName(Activity activity) {
        try {
            return getParentActivityName(activity, activity.getComponentName());
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        r0 = r4.parentActivityName;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getParentActivityName(android.content.Context r3, android.content.ComponentName r4) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            android.content.pm.PackageManager r0 = r3.getPackageManager()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r1 < r2) goto Le
            r1 = 269222528(0x100c0280, float:2.7612058E-29)
            goto L1a
        Le:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L18
            r1 = 787072(0xc0280, float:1.102923E-39)
            goto L1a
        L18:
            r1 = 640(0x280, float:8.97E-43)
        L1a:
            android.content.pm.ActivityInfo r4 = r0.getActivityInfo(r4, r1)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L2b
            java.lang.String r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m(r4)
            if (r0 == 0) goto L2b
            return r0
        L2b:
            android.os.Bundle r0 = r4.metaData
            r1 = 0
            if (r0 != 0) goto L31
            return r1
        L31:
            android.os.Bundle r4 = r4.metaData
            java.lang.String r0 = "android.support.PARENT_ACTIVITY"
            java.lang.String r4 = r4.getString(r0)
            if (r4 != 0) goto L3c
            return r1
        L3c:
            r0 = 0
            char r0 = r4.charAt(r0)
            r1 = 46
            if (r0 != r1) goto L58
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = r3.getPackageName()
            r0.append(r3)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
        L58:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NavUtils.getParentActivityName(android.content.Context, android.content.ComponentName):java.lang.String");
    }

    private NavUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api16Impl {
        private Api16Impl() {
        }

        static boolean shouldUpRecreateTask(Activity activity, Intent intent) {
            return activity.shouldUpRecreateTask(intent);
        }

        static boolean navigateUpTo(Activity activity, Intent intent) {
            return activity.navigateUpTo(intent);
        }

        static Intent getParentActivityIntent(Activity activity) {
            return activity.getParentActivityIntent();
        }
    }
}