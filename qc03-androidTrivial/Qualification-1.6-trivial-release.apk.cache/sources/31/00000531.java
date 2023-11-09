package androidx.core.util;

import android.util.LongSparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LongSparseArray.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0000\u001a!\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0087\n\u001a!\u0010\n\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0087\b\u001a&\u0010\u000b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\f\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\r\u001aT\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000326\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000f0\u0011H\u0087\bø\u0001\u0000\u001a.\u0010\u0014\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0016\u001a7\u0010\u0017\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0018H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u0019\u0010\u001a\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a\u0019\u0010\u001b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a\u0018\u0010\u001c\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a-\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\u0002\u001a&\u0010 \u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a-\u0010!\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\"\u001a.\u0010#\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010$\u001a\u001e\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00020&\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\"\"\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"}, d2 = {"size", "", "T", "Landroid/util/LongSparseArray;", "getSize", "(Landroid/util/LongSparseArray;)I", "contains", "", "key", "", "containsKey", "containsValue", "value", "(Landroid/util/LongSparseArray;Ljava/lang/Object;)Z", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroid/util/LongSparseArray;JLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/LongIterator;", "plus", "other", "putAll", "remove", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Z", "set", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)V", "valueIterator", "", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class LongSparseArrayKt {
    public static final <T> int getSize(LongSparseArray<T> longSparseArray) {
        int size;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        size = longSparseArray.size();
        return size;
    }

    public static final <T> boolean contains(LongSparseArray<T> longSparseArray, long j) {
        int indexOfKey;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        indexOfKey = longSparseArray.indexOfKey(j);
        return indexOfKey >= 0;
    }

    public static final <T> void set(LongSparseArray<T> longSparseArray, long j, T t) {
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        longSparseArray.put(j, t);
    }

    public static final <T> LongSparseArray<T> plus(LongSparseArray<T> longSparseArray, LongSparseArray<T> other) {
        int size;
        int size2;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        size = longSparseArray.size();
        size2 = other.size();
        LongSparseArray<T> longSparseArray2 = new LongSparseArray<>(size + size2);
        putAll(longSparseArray2, longSparseArray);
        putAll(longSparseArray2, other);
        return longSparseArray2;
    }

    public static final <T> boolean containsKey(LongSparseArray<T> longSparseArray, long j) {
        int indexOfKey;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        indexOfKey = longSparseArray.indexOfKey(j);
        return indexOfKey >= 0;
    }

    public static final <T> boolean containsValue(LongSparseArray<T> longSparseArray, T t) {
        int indexOfValue;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        indexOfValue = longSparseArray.indexOfValue(t);
        return indexOfValue >= 0;
    }

    public static final <T> T getOrDefault(LongSparseArray<T> longSparseArray, long j, T t) {
        Object obj;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        obj = longSparseArray.get(j);
        T t2 = (T) obj;
        return t2 == null ? t : t2;
    }

    public static final <T> T getOrElse(LongSparseArray<T> longSparseArray, long j, Function0<? extends T> defaultValue) {
        Object obj;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        obj = longSparseArray.get(j);
        T t = (T) obj;
        return t == null ? defaultValue.invoke() : t;
    }

    public static final <T> boolean isEmpty(LongSparseArray<T> longSparseArray) {
        int size;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        size = longSparseArray.size();
        return size == 0;
    }

    public static final <T> boolean isNotEmpty(LongSparseArray<T> longSparseArray) {
        int size;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        size = longSparseArray.size();
        return size != 0;
    }

    public static final <T> boolean remove(LongSparseArray<T> longSparseArray, long j, T t) {
        int indexOfKey;
        Object valueAt;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        indexOfKey = longSparseArray.indexOfKey(j);
        if (indexOfKey >= 0) {
            valueAt = longSparseArray.valueAt(indexOfKey);
            if (Intrinsics.areEqual(t, valueAt)) {
                longSparseArray.removeAt(indexOfKey);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final <T> void forEach(LongSparseArray<T> longSparseArray, Function2<? super Long, ? super T, Unit> action) {
        int size;
        long keyAt;
        Object valueAt;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        size = longSparseArray.size();
        for (int i = 0; i < size; i++) {
            keyAt = longSparseArray.keyAt(i);
            Long valueOf = Long.valueOf(keyAt);
            valueAt = longSparseArray.valueAt(i);
            action.invoke(valueOf, (Object) valueAt);
        }
    }

    public static final <T> LongIterator keyIterator(final LongSparseArray<T> longSparseArray) {
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        return new LongIterator() { // from class: androidx.core.util.LongSparseArrayKt$keyIterator$1
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
                size = longSparseArray.size();
                return i < size;
            }

            @Override // kotlin.collections.LongIterator
            public long nextLong() {
                long keyAt;
                LongSparseArray<T> longSparseArray2 = longSparseArray;
                int i = this.index;
                this.index = i + 1;
                keyAt = longSparseArray2.keyAt(i);
                return keyAt;
            }
        };
    }

    public static final <T> Iterator<T> valueIterator(LongSparseArray<T> longSparseArray) {
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        return new LongSparseArrayKt$valueIterator$1(longSparseArray);
    }

    public static final <T> void putAll(LongSparseArray<T> longSparseArray, LongSparseArray<T> other) {
        int size;
        long keyAt;
        Object valueAt;
        Intrinsics.checkNotNullParameter(longSparseArray, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        size = other.size();
        for (int i = 0; i < size; i++) {
            keyAt = other.keyAt(i);
            valueAt = other.valueAt(i);
            longSparseArray.put(keyAt, valueAt);
        }
    }
}