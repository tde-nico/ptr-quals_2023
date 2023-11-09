package org.esa.ptr23.qualification;

import android.content.Context;

/* loaded from: classes.dex */
public class Verifier {
    private static final String TAG = "Verifier";

    public static native String checkPasswordByJNI07(String str);

    static {
        System.loadLibrary("native-lib");
    }

    private Verifier() {
    }

    public static boolean verifyPassword(Context context, String str) {
        return "TRUE".equals(checkPasswordByJNI07(str));
    }
}