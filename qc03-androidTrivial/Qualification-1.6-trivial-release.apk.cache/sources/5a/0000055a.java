package androidx.core.util;

import android.util.SparseLongArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SparseLongArray.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001aH\u0010\f\u001a\u00020\r*\u00020\u000226\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\r0\u000fH\u0087\bø\u0001\u0000\u001a\u001d\u0010\u0012\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u000bH\u0087\b\u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015H\u0087\bø\u0001\u0000\u001a\r\u0010\u0016\u001a\u00020\u0006*\u00020\u0002H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0006*\u00020\u0002H\u0087\b\u001a\f\u0010\u0018\u001a\u00020\u0019*\u00020\u0002H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0087\u0002\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0007\u001a\u001c\u0010\u001d\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\u001d\u0010\u001e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0087\n\u001a\f\u0010\u001f\u001a\u00020 *\u00020\u0002H\u0007\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"size", "", "Landroid/util/SparseLongArray;", "getSize", "(Landroid/util/SparseLongArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "Lkotlin/collections/LongIterator;", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SparseLongArrayKt {
    public static final int getSize(SparseLongArray sparseLongArray) {
        int size;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        size = sparseLongArray.size();
        return size;
    }

    public static final boolean contains(SparseLongArray sparseLongArray, int i) {
        int indexOfKey;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        indexOfKey = sparseLongArray.indexOfKey(i);
        return indexOfKey >= 0;
    }

    public static final void set(SparseLongArray sparseLongArray, int i, long j) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        sparseLongArray.put(i, j);
    }

    public static final SparseLongArray plus(SparseLongArray sparseLongArray, SparseLongArray other) {
        int size;
        int size2;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        size = sparseLongArray.size();
        size2 = other.size();
        SparseLongArray sparseLongArray2 = new SparseLongArray(size + size2);
        putAll(sparseLongArray2, sparseLongArray);
        putAll(sparseLongArray2, other);
        return sparseLongArray2;
    }

    public static final boolean containsKey(SparseLongArray sparseLongArray, int i) {
        int indexOfKey;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        indexOfKey = sparseLongArray.indexOfKey(i);
        return indexOfKey >= 0;
    }

    public static final boolean containsValue(SparseLongArray sparseLongArray, long j) {
        int indexOfValue;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        indexOfValue = sparseLongArray.indexOfValue(j);
        return indexOfValue >= 0;
    }

    public static final long getOrDefault(SparseLongArray sparseLongArray, int i, long j) {
        long j2;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        j2 = sparseLongArray.get(i, j);
        return j2;
    }

    public static final long getOrElse(SparseLongArray sparseLongArray, int i, Function0<Long> defaultValue) {
        int indexOfKey;
        long valueAt;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        indexOfKey = sparseLongArray.indexOfKey(i);
        if (indexOfKey >= 0) {
            valueAt = sparseLongArray.valueAt(indexOfKey);
            return valueAt;
        }
        return defaultValue.invoke().longValue();
    }

    public static final boolean isEmpty(SparseLongArray sparseLongArray) {
        int size;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        size = sparseLongArray.size();
        return size == 0;
    }

    public static final boolean isNotEmpty(SparseLongArray sparseLongArray) {
        int size;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        size = sparseLongArray.size();
        return size != 0;
    }

    public static final boolean remove(SparseLongArray sparseLongArray, int i, long j) {
        int indexOfKey;
        long valueAt;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        indexOfKey = sparseLongArray.indexOfKey(i);
        if (indexOfKey >= 0) {
            valueAt = sparseLongArray.valueAt(indexOfKey);
            if (j == valueAt) {
                sparseLongArray.removeAt(indexOfKey);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final void forEach(SparseLongArray sparseLongArray, Function2<? super Integer, ? super Long, Unit> action) {
        int size;
        int keyAt;
        long valueAt;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        size = sparseLongArray.size();
        for (int i = 0; i < size; i++) {
            keyAt = sparseLongArray.keyAt(i);
            Integer valueOf = Integer.valueOf(keyAt);
            valueAt = sparseLongArray.valueAt(i);
            action.invoke(valueOf, Long.valueOf(valueAt));
        }
    }

    public static final IntIterator keyIterator(final SparseLongArray sparseLongArray) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return new IntIterator() { // from class: androidx.core.util.SparseLongArrayKt$keyIterator$1
            private int index;

            public final int getIndex() {
                return this.index;
            }

            public final void setIndex(int i) {
                this.index = i;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                int size;
                int i = this.index;
                size = sparseLongArray.size();
                return i < size;
            }

            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                int keyAt;
                SparseLongArray sparseLongArray2 = sparseLongArray;
                int i = this.index;
                this.index = i + 1;
                keyAt = sparseLongArray2.keyAt(i);
                return keyAt;
            }
        };
    }

    public static final LongIterator valueIterator(final SparseLongArray sparseLongArray) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return new LongIterator() { // from class: androidx.core.util.SparseLongArrayKt$valueIterator$1
            private int index;

            public final int getIndex() {
                return this.index;
            }

            public final void setIndex(int i) {
                this.index = i;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                int size;
                int i = this.index;
                size = sparseLongArray.size();
                return i < size;
            }

            @Override // kotlin.collections.LongIterator
            public long nextLong() {
                long valueAt;
                SparseLongArray sparseLongArray2 = sparseLongArray;
                int i = this.index;
                this.index = i + 1;
                valueAt = sparseLongArray2.valueAt(i);
                return valueAt;
            }
        };
    }

    public static final void putAll(SparseLongArray sparseLongArray, SparseLongArray other) {
        int size;
        int keyAt;
        long valueAt;
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        size = other.size();
        for (int i = 0; i < size; i++) {
            keyAt = other.keyAt(i);
            valueAt = other.valueAt(i);
            sparseLongArray.put(keyAt, valueAt);
        }
    }
}