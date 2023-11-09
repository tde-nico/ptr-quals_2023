package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0;
import androidx.core.os.CancellationSignal;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class FragmentTransitionCompat21 extends FragmentTransitionImpl {
    @Override // androidx.fragment.app.FragmentTransitionImpl
    public boolean canHandle(Object obj) {
        return Trace$$ExternalSyntheticApiModelOutline0.m267m(obj);
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object cloneTransition(Object obj) {
        Transition clone;
        if (obj != null) {
            clone = NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj).clone();
            return clone;
        }
        return null;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object wrapTransitionInSet(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj));
        return transitionSet;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList) {
        List targets;
        TransitionSet m248m = Trace$$ExternalSyntheticApiModelOutline0.m248m(obj);
        targets = m248m.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            bfsAddViewChildren(targets, arrayList.get(i));
        }
        targets.add(view);
        arrayList.add(view);
        addTargets(m248m, arrayList);
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setEpicenter(Object obj, View view) {
        if (view != null) {
            Transition m16m = NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj);
            final Rect rect = new Rect();
            getBoundsOnScreen(view, rect);
            m16m.setEpicenterCallback(new Transition.EpicenterCallback() { // from class: androidx.fragment.app.FragmentTransitionCompat21.1
                @Override // android.transition.Transition.EpicenterCallback
                public Rect onGetEpicenter(Transition transition) {
                    return rect;
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void addTargets(Object obj, ArrayList<View> arrayList) {
        List targets;
        int transitionCount;
        Transition transitionAt;
        Transition m16m = NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj);
        if (m16m == null) {
            return;
        }
        int i = 0;
        if (Trace$$ExternalSyntheticApiModelOutline0.m$1((Object) m16m)) {
            TransitionSet m248m = Trace$$ExternalSyntheticApiModelOutline0.m248m((Object) m16m);
            transitionCount = m248m.getTransitionCount();
            while (i < transitionCount) {
                transitionAt = m248m.getTransitionAt(i);
                addTargets(transitionAt, arrayList);
                i++;
            }
        } else if (hasSimpleTarget(m16m)) {
        } else {
            targets = m16m.getTargets();
            if (isNullOrEmpty(targets)) {
                int size = arrayList.size();
                while (i < size) {
                    m16m.addTarget(arrayList.get(i));
                    i++;
                }
            }
        }
    }

    private static boolean hasSimpleTarget(Transition transition) {
        List targetIds;
        List targetNames;
        List targetTypes;
        targetIds = transition.getTargetIds();
        if (isNullOrEmpty(targetIds)) {
            targetNames = transition.getTargetNames();
            if (isNullOrEmpty(targetNames)) {
                targetTypes = transition.getTargetTypes();
                if (isNullOrEmpty(targetTypes)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.addTransition(NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj));
        }
        if (obj2 != null) {
            transitionSet.addTransition(NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj2));
        }
        if (obj3 != null) {
            transitionSet.addTransition(NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj3));
        }
        return transitionSet;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void scheduleHideFragmentView(Object obj, final View view, final ArrayList<View> arrayList) {
        NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj).addListener(new Transition.TransitionListener() { // from class: androidx.fragment.app.FragmentTransitionCompat21.2
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
                transition.removeListener(this);
                transition.addListener(this);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                view.setVisibility(8);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((View) arrayList.get(i)).setVisibility(0);
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3) {
        TransitionSet addTransition;
        TransitionSet addTransition2;
        Transition m16m = NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj);
        Transition m16m2 = NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj2);
        Transition m16m3 = NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj3);
        if (m16m != null && m16m2 != null) {
            addTransition = new TransitionSet().addTransition(m16m);
            addTransition2 = addTransition.addTransition(m16m2);
            m16m = addTransition2.setOrdering(1);
        } else if (m16m == null) {
            m16m = m16m2 != null ? m16m2 : null;
        }
        if (m16m3 != null) {
            TransitionSet transitionSet = new TransitionSet();
            if (m16m != null) {
                transitionSet.addTransition(m16m);
            }
            transitionSet.addTransition(m16m3);
            return transitionSet;
        }
        return m16m;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj));
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void scheduleRemoveTargets(Object obj, final Object obj2, final ArrayList<View> arrayList, final Object obj3, final ArrayList<View> arrayList2, final Object obj4, final ArrayList<View> arrayList3) {
        NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj).addListener(new Transition.TransitionListener() { // from class: androidx.fragment.app.FragmentTransitionCompat21.3
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
                Object obj5 = obj2;
                if (obj5 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj5, arrayList, null);
                }
                Object obj6 = obj3;
                if (obj6 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj6, arrayList2, null);
                }
                Object obj7 = obj4;
                if (obj7 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj7, arrayList3, null);
                }
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setListenerForTransitionEnd(Fragment fragment, Object obj, CancellationSignal cancellationSignal, final Runnable runnable) {
        NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj).addListener(new Transition.TransitionListener() { // from class: androidx.fragment.app.FragmentTransitionCompat21.4
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                runnable.run();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List targets;
        List targets2;
        TransitionSet m248m = Trace$$ExternalSyntheticApiModelOutline0.m248m(obj);
        if (m248m != null) {
            targets = m248m.getTargets();
            targets.clear();
            targets2 = m248m.getTargets();
            targets2.addAll(arrayList2);
            replaceTargets(m248m, arrayList, arrayList2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
        r0 = r5.getTargets();
     */
    @Override // androidx.fragment.app.FragmentTransitionImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void replaceTargets(java.lang.Object r5, java.util.ArrayList<android.view.View> r6, java.util.ArrayList<android.view.View> r7) {
        /*
            r4 = this;
            android.transition.Transition r5 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m16m(r5)
            boolean r0 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m$1(r5)
            r1 = 0
            if (r0 == 0) goto L1f
            android.transition.TransitionSet r5 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m248m(r5)
            int r0 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m(r5)
        L13:
            if (r1 >= r0) goto L65
            android.transition.Transition r2 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m(r5, r1)
            r4.replaceTargets(r2, r6, r7)
            int r1 = r1 + 1
            goto L13
        L1f:
            boolean r0 = hasSimpleTarget(r5)
            if (r0 != 0) goto L65
            java.util.List r0 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m259m(r5)
            if (r0 == 0) goto L65
            int r2 = r0.size()
            int r3 = r6.size()
            if (r2 != r3) goto L65
            boolean r0 = r0.containsAll(r6)
            if (r0 == 0) goto L65
            if (r7 != 0) goto L3f
            r0 = 0
            goto L43
        L3f:
            int r0 = r7.size()
        L43:
            if (r1 >= r0) goto L51
            java.lang.Object r2 = r7.get(r1)
            android.view.View r2 = (android.view.View) r2
            androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m(r5, r2)
            int r1 = r1 + 1
            goto L43
        L51:
            int r7 = r6.size()
            int r7 = r7 + (-1)
        L57:
            if (r7 < 0) goto L65
            java.lang.Object r0 = r6.get(r7)
            android.view.View r0 = (android.view.View) r0
            androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m$1(r5, r0)
            int r7 = r7 + (-1)
            goto L57
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentTransitionCompat21.replaceTargets(java.lang.Object, java.util.ArrayList, java.util.ArrayList):void");
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void addTarget(Object obj, View view) {
        if (obj != null) {
            NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj).addTarget(view);
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void removeTarget(Object obj, View view) {
        if (obj != null) {
            NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj).removeTarget(view);
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setEpicenter(Object obj, final Rect rect) {
        if (obj != null) {
            NavUtils$$ExternalSyntheticApiModelOutline0.m16m(obj).setEpicenterCallback(new Transition.EpicenterCallback() { // from class: androidx.fragment.app.FragmentTransitionCompat21.5
                @Override // android.transition.Transition.EpicenterCallback
                public Rect onGetEpicenter(Transition transition) {
                    Rect rect2 = rect;
                    if (rect2 == null || rect2.isEmpty()) {
                        return null;
                    }
                    return rect;
                }
            });
        }
    }
}