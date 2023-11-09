package androidx.core.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.pm.PackageInfoCompat$$ExternalSyntheticApiModelOutline0;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;

/* loaded from: classes.dex */
public final class ContentResolverCompat {
    private ContentResolverCompat() {
    }

    public static Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        Object cancellationSignalObject;
        if (Build.VERSION.SDK_INT >= 16) {
            if (cancellationSignal != null) {
                try {
                    cancellationSignalObject = cancellationSignal.getCancellationSignalObject();
                } catch (Exception e) {
                    if (PackageInfoCompat$$ExternalSyntheticApiModelOutline0.m90m((Object) e)) {
                        throw new OperationCanceledException();
                    }
                    throw e;
                }
            } else {
                cancellationSignalObject = null;
            }
            return Api16Impl.query(contentResolver, uri, strArr, str, strArr2, str2, PackageInfoCompat$$ExternalSyntheticApiModelOutline0.m77m(cancellationSignalObject));
        }
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        return contentResolver.query(uri, strArr, str, strArr2, str2);
    }

    /* loaded from: classes.dex */
    static class Api16Impl {
        private Api16Impl() {
        }

        static Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, android.os.CancellationSignal cancellationSignal) {
            return contentResolver.query(uri, strArr, str, strArr2, str2, cancellationSignal);
        }
    }
}