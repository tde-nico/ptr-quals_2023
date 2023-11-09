package androidx.core.content.res;

import android.content.res.Resources;
import android.os.Build;

/* loaded from: classes.dex */
public final class ConfigurationHelper {
    private ConfigurationHelper() {
    }

    public static int getDensityDpi(Resources resources) {
        int i;
        if (Build.VERSION.SDK_INT >= 17) {
            i = resources.getConfiguration().densityDpi;
            return i;
        }
        return resources.getDisplayMetrics().densityDpi;
    }
}