package androidx.core.util;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class Predicate$$ExternalSyntheticBackport0 {
    public static /* synthetic */ int m(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        return i < i2 ? -1 : 1;
    }

    public static /* synthetic */ int m(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static /* synthetic */ boolean m(Object obj) {
        return obj == null;
    }

    public static /* synthetic */ boolean m(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static /* synthetic */ Throwable[] m(Throwable th) {
        try {
            return (Throwable[]) Throwable.class.getDeclaredMethod("getSuppressed", new Class[0]).invoke(th, new Object[0]);
        } catch (Exception unused) {
            return new Throwable[0];
        }
    }
}