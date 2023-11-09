package androidx.core.util;

import androidx.core.util.Predicate;

/* loaded from: classes.dex */
public interface Predicate<T> {
    Predicate<T> and(Predicate<? super T> predicate);

    Predicate<T> negate();

    Predicate<T> or(Predicate<? super T> predicate);

    boolean test(T t);

    /* renamed from: androidx.core.util.Predicate$-CC */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static Predicate $default$and(final Predicate _this, final Predicate predicate) {
            predicate.getClass();
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda4
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate2) {
                    return Predicate.CC.$default$and(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate2) {
                    return Predicate.CC.$default$or(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$and$0(Predicate.this, predicate, obj);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$and$0(Predicate _this, Predicate predicate, Object obj) {
            return _this.test(obj) && predicate.test(obj);
        }

        public static Predicate $default$negate(final Predicate _this) {
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda5
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$negate$1(Predicate.this, obj);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$negate$1(Predicate _this, Object obj) {
            return !_this.test(obj);
        }

        public static Predicate $default$or(final Predicate _this, final Predicate predicate) {
            predicate.getClass();
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda1
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate2) {
                    return Predicate.CC.$default$and(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate2) {
                    return Predicate.CC.$default$or(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$or$2(Predicate.this, predicate, obj);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$or$2(Predicate _this, Predicate predicate, Object obj) {
            return _this.test(obj) || predicate.test(obj);
        }

        public static <T> Predicate<T> isEqual(final Object obj) {
            if (obj == null) {
                return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda2
                    @Override // androidx.core.util.Predicate
                    public /* synthetic */ Predicate and(Predicate predicate) {
                        return Predicate.CC.$default$and(this, predicate);
                    }

                    @Override // androidx.core.util.Predicate
                    public /* synthetic */ Predicate negate() {
                        return Predicate.CC.$default$negate(this);
                    }

                    @Override // androidx.core.util.Predicate
                    public /* synthetic */ Predicate or(Predicate predicate) {
                        return Predicate.CC.$default$or(this, predicate);
                    }

                    @Override // androidx.core.util.Predicate
                    public final boolean test(Object obj2) {
                        return Predicate.CC.lambda$isEqual$3(obj2);
                    }
                };
            }
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda3
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj2) {
                    return Predicate.CC.lambda$isEqual$4(obj, obj2);
                }
            };
        }

        public static /* synthetic */ boolean lambda$isEqual$3(Object obj) {
            return Predicate$$ExternalSyntheticBackport0.m(obj);
        }

        public static /* synthetic */ boolean lambda$isEqual$4(Object obj, Object obj2) {
            return obj.equals(obj2);
        }

        public static <T> Predicate<T> not(Predicate<? super T> predicate) {
            predicate.getClass();
            return (Predicate<? super T>) predicate.negate();
        }
    }
}