package androidx.core.app;

import android.app.ActivityManager;
import android.os.Build;

/* loaded from: classes.dex */
public final class ActivityManagerCompat {
    private ActivityManagerCompat() {
    }

    public static boolean isLowRamDevice(ActivityManager activityManager) {
        boolean isLowRamDevice;
        if (Build.VERSION.SDK_INT >= 19) {
            isLowRamDevice = activityManager.isLowRamDevice();
            return isLowRamDevice;
        }
        return false;
    }
}