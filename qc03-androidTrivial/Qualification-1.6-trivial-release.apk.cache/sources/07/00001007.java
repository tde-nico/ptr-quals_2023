package org.esa.ptr23.qualification;

import android.content.Context;

/* loaded from: classes.dex */
public class Verifier {
    private Verifier() {
    }

    public static boolean verifyPassword(Context context, String str) {
        return context.getString(org.esa.ptr23.qualification.trivial.R.string.something_hidden).equals(str.trim());
    }
}