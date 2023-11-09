package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.R;
import androidx.core.os.BuildCompat;
import androidx.core.view.WindowInsetsCompat$$ExternalSyntheticApiModelOutline0;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AccessibilityNodeInfoCompat {
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final String ACTION_ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT = "android.view.accessibility.action.ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 524288;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_DISMISS = 1048576;
    public static final int ACTION_EXPAND = 262144;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int ACTION_SET_TEXT = 2097152;
    private static final int BOOLEAN_PROPERTY_IS_HEADING = 2;
    private static final int BOOLEAN_PROPERTY_IS_SHOWING_HINT = 4;
    private static final int BOOLEAN_PROPERTY_IS_TEXT_ENTRY_KEY = 8;
    private static final String BOOLEAN_PROPERTY_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY";
    private static final int BOOLEAN_PROPERTY_SCREEN_READER_FOCUSABLE = 1;
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH = "android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH";
    public static final int EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH = 20000;
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX = "android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY = "android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY";
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    private static final String HINT_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY";
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private static final String PANE_TITLE_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY";
    private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    private static final String SPANS_ACTION_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY";
    private static final String SPANS_END_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY";
    private static final String SPANS_FLAGS_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY";
    private static final String SPANS_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY";
    private static final String SPANS_START_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY";
    private static final String STATE_DESCRIPTION_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY";
    private static final String TOOLTIP_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY";
    private static final String UNIQUE_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY";
    private static int sClickableSpanId;
    private final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId = -1;
    private int mVirtualDescendantId = -1;

    private static String getActionSymbolicName(int i) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 4:
                        return "ACTION_SELECT";
                    case 8:
                        return "ACTION_CLEAR_SELECTION";
                    case 16:
                        return "ACTION_CLICK";
                    case 32:
                        return "ACTION_LONG_CLICK";
                    case 64:
                        return "ACTION_ACCESSIBILITY_FOCUS";
                    case 128:
                        return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                    case 256:
                        return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                    case 512:
                        return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                    case 1024:
                        return "ACTION_NEXT_HTML_ELEMENT";
                    case 2048:
                        return "ACTION_PREVIOUS_HTML_ELEMENT";
                    case 4096:
                        return "ACTION_SCROLL_FORWARD";
                    case 8192:
                        return "ACTION_SCROLL_BACKWARD";
                    case 16384:
                        return "ACTION_COPY";
                    case 32768:
                        return "ACTION_PASTE";
                    case 65536:
                        return "ACTION_CUT";
                    case 131072:
                        return "ACTION_SET_SELECTION";
                    case 262144:
                        return "ACTION_EXPAND";
                    case 524288:
                        return "ACTION_COLLAPSE";
                    case 2097152:
                        return "ACTION_SET_TEXT";
                    case 16908354:
                        return "ACTION_MOVE_WINDOW";
                    default:
                        switch (i) {
                            case 16908342:
                                return "ACTION_SHOW_ON_SCREEN";
                            case 16908343:
                                return "ACTION_SCROLL_TO_POSITION";
                            case 16908344:
                                return "ACTION_SCROLL_UP";
                            case 16908345:
                                return "ACTION_SCROLL_LEFT";
                            case 16908346:
                                return "ACTION_SCROLL_DOWN";
                            case 16908347:
                                return "ACTION_SCROLL_RIGHT";
                            case 16908348:
                                return "ACTION_CONTEXT_CLICK";
                            case 16908349:
                                return "ACTION_SET_PROGRESS";
                            default:
                                switch (i) {
                                    case 16908356:
                                        return "ACTION_SHOW_TOOLTIP";
                                    case 16908357:
                                        return "ACTION_HIDE_TOOLTIP";
                                    case 16908358:
                                        return "ACTION_PAGE_UP";
                                    case 16908359:
                                        return "ACTION_PAGE_DOWN";
                                    case 16908360:
                                        return "ACTION_PAGE_LEFT";
                                    case 16908361:
                                        return "ACTION_PAGE_RIGHT";
                                    case 16908362:
                                        return "ACTION_PRESS_AND_HOLD";
                                    default:
                                        switch (i) {
                                            case 16908372:
                                                return "ACTION_IME_ENTER";
                                            case 16908373:
                                                return "ACTION_DRAG_START";
                                            case 16908374:
                                                return "ACTION_DRAG_DROP";
                                            case 16908375:
                                                return "ACTION_DRAG_CANCEL";
                                            default:
                                                return "ACTION_UNKNOWN";
                                        }
                                }
                        }
                }
            }
            return "ACTION_CLEAR_FOCUS";
        }
        return "ACTION_FOCUS";
    }

    /* loaded from: classes.dex */
    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
        public static final AccessibilityActionCompat ACTION_DRAG_CANCEL;
        public static final AccessibilityActionCompat ACTION_DRAG_DROP;
        public static final AccessibilityActionCompat ACTION_DRAG_START;
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP;
        public static final AccessibilityActionCompat ACTION_IME_ENTER;
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW;
        public static final AccessibilityActionCompat ACTION_PAGE_DOWN;
        public static final AccessibilityActionCompat ACTION_PAGE_LEFT;
        public static final AccessibilityActionCompat ACTION_PAGE_RIGHT;
        public static final AccessibilityActionCompat ACTION_PAGE_UP;
        public static final AccessibilityActionCompat ACTION_PRESS_AND_HOLD;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        public static final AccessibilityActionCompat ACTION_SHOW_TEXT_SUGGESTIONS;
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP;
        private static final String TAG = "A11yActionCompat";
        final Object mAction;
        protected final AccessibilityViewCommand mCommand;
        private final int mId;
        private final Class<? extends AccessibilityViewCommand.CommandArguments> mViewCommandArgumentClass;
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
        public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
        public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, (CharSequence) null, AccessibilityViewCommand.MoveAtGranularityArguments.class);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, (CharSequence) null, AccessibilityViewCommand.MoveAtGranularityArguments.class);
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, (CharSequence) null, AccessibilityViewCommand.MoveHtmlArguments.class);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, (CharSequence) null, AccessibilityViewCommand.MoveHtmlArguments.class);
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
        public static final AccessibilityActionCompat ACTION_COPY = new AccessibilityActionCompat(16384, null);
        public static final AccessibilityActionCompat ACTION_PASTE = new AccessibilityActionCompat(32768, null);
        public static final AccessibilityActionCompat ACTION_CUT = new AccessibilityActionCompat(65536, null);
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, (CharSequence) null, AccessibilityViewCommand.SetSelectionArguments.class);
        public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
        public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
        public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
        public static final AccessibilityActionCompat ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, (CharSequence) null, AccessibilityViewCommand.SetTextArguments.class);

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction9;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction10;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction11;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction12;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction13;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction14;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction15;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction16;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction17;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction18;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction19;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction20;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction21;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction22;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction23;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction24;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction25;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction26;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction27;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction28;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction29;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction30;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction31;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction32;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction33;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction34;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction35;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction36;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction37;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction38;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction39;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction40;
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction40 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
                accessibilityAction = accessibilityAction40;
            } else {
                accessibilityAction = null;
            }
            ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(accessibilityAction, 16908342, null, null, null);
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction39 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
                accessibilityAction2 = accessibilityAction39;
            } else {
                accessibilityAction2 = null;
            }
            ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(accessibilityAction2, 16908343, null, null, AccessibilityViewCommand.ScrollToPositionArguments.class);
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction38 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
                accessibilityAction3 = accessibilityAction38;
            } else {
                accessibilityAction3 = null;
            }
            ACTION_SCROLL_UP = new AccessibilityActionCompat(accessibilityAction3, 16908344, null, null, null);
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction37 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
                accessibilityAction4 = accessibilityAction37;
            } else {
                accessibilityAction4 = null;
            }
            ACTION_SCROLL_LEFT = new AccessibilityActionCompat(accessibilityAction4, 16908345, null, null, null);
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction36 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
                accessibilityAction5 = accessibilityAction36;
            } else {
                accessibilityAction5 = null;
            }
            ACTION_SCROLL_DOWN = new AccessibilityActionCompat(accessibilityAction5, 16908346, null, null, null);
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction35 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
                accessibilityAction6 = accessibilityAction35;
            } else {
                accessibilityAction6 = null;
            }
            ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(accessibilityAction6, 16908347, null, null, null);
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction34 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
                accessibilityAction7 = accessibilityAction34;
            } else {
                accessibilityAction7 = null;
            }
            ACTION_PAGE_UP = new AccessibilityActionCompat(accessibilityAction7, 16908358, null, null, null);
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction33 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
                accessibilityAction8 = accessibilityAction33;
            } else {
                accessibilityAction8 = null;
            }
            ACTION_PAGE_DOWN = new AccessibilityActionCompat(accessibilityAction8, 16908359, null, null, null);
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction32 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
                accessibilityAction9 = accessibilityAction32;
            } else {
                accessibilityAction9 = null;
            }
            ACTION_PAGE_LEFT = new AccessibilityActionCompat(accessibilityAction9, 16908360, null, null, null);
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction31 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
                accessibilityAction10 = accessibilityAction31;
            } else {
                accessibilityAction10 = null;
            }
            ACTION_PAGE_RIGHT = new AccessibilityActionCompat(accessibilityAction10, 16908361, null, null, null);
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction30 = AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
                accessibilityAction11 = accessibilityAction30;
            } else {
                accessibilityAction11 = null;
            }
            ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(accessibilityAction11, 16908348, null, null, null);
            if (Build.VERSION.SDK_INT >= 24) {
                accessibilityAction29 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
                accessibilityAction12 = accessibilityAction29;
            } else {
                accessibilityAction12 = null;
            }
            ACTION_SET_PROGRESS = new AccessibilityActionCompat(accessibilityAction12, 16908349, null, null, AccessibilityViewCommand.SetProgressArguments.class);
            if (Build.VERSION.SDK_INT >= 26) {
                accessibilityAction28 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
                accessibilityAction13 = accessibilityAction28;
            } else {
                accessibilityAction13 = null;
            }
            ACTION_MOVE_WINDOW = new AccessibilityActionCompat(accessibilityAction13, 16908354, null, null, AccessibilityViewCommand.MoveWindowArguments.class);
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction27 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
                accessibilityAction14 = accessibilityAction27;
            } else {
                accessibilityAction14 = null;
            }
            ACTION_SHOW_TOOLTIP = new AccessibilityActionCompat(accessibilityAction14, 16908356, null, null, null);
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction26 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
                accessibilityAction15 = accessibilityAction26;
            } else {
                accessibilityAction15 = null;
            }
            ACTION_HIDE_TOOLTIP = new AccessibilityActionCompat(accessibilityAction15, 16908357, null, null, null);
            if (Build.VERSION.SDK_INT >= 30) {
                accessibilityAction25 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD;
                accessibilityAction16 = accessibilityAction25;
            } else {
                accessibilityAction16 = null;
            }
            ACTION_PRESS_AND_HOLD = new AccessibilityActionCompat(accessibilityAction16, 16908362, null, null, null);
            if (Build.VERSION.SDK_INT >= 30) {
                accessibilityAction24 = AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER;
                accessibilityAction17 = accessibilityAction24;
            } else {
                accessibilityAction17 = null;
            }
            ACTION_IME_ENTER = new AccessibilityActionCompat(accessibilityAction17, 16908372, null, null, null);
            if (Build.VERSION.SDK_INT >= 32) {
                accessibilityAction23 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START;
                accessibilityAction18 = accessibilityAction23;
            } else {
                accessibilityAction18 = null;
            }
            ACTION_DRAG_START = new AccessibilityActionCompat(accessibilityAction18, 16908373, null, null, null);
            if (Build.VERSION.SDK_INT >= 32) {
                accessibilityAction22 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP;
                accessibilityAction19 = accessibilityAction22;
            } else {
                accessibilityAction19 = null;
            }
            ACTION_DRAG_DROP = new AccessibilityActionCompat(accessibilityAction19, 16908374, null, null, null);
            if (Build.VERSION.SDK_INT >= 32) {
                accessibilityAction21 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL;
                accessibilityAction20 = accessibilityAction21;
            } else {
                accessibilityAction20 = null;
            }
            ACTION_DRAG_CANCEL = new AccessibilityActionCompat(accessibilityAction20, 16908375, null, null, null);
            ACTION_SHOW_TEXT_SUGGESTIONS = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 33 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS : null, 16908376, null, null, null);
        }

        public AccessibilityActionCompat(int i, CharSequence charSequence) {
            this(null, i, charSequence, null, null);
        }

        public AccessibilityActionCompat(int i, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
            this(null, i, charSequence, accessibilityViewCommand, null);
        }

        AccessibilityActionCompat(Object obj) {
            this(obj, 0, null, null, null);
        }

        private AccessibilityActionCompat(int i, CharSequence charSequence, Class<? extends AccessibilityViewCommand.CommandArguments> cls) {
            this(null, i, charSequence, null, cls);
        }

        AccessibilityActionCompat(Object obj, int i, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand, Class<? extends AccessibilityViewCommand.CommandArguments> cls) {
            this.mId = i;
            this.mCommand = accessibilityViewCommand;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.mAction = new AccessibilityNodeInfo.AccessibilityAction(i, charSequence);
            } else {
                this.mAction = obj;
            }
            this.mViewCommandArgumentClass = cls;
        }

        public int getId() {
            int id;
            if (Build.VERSION.SDK_INT >= 21) {
                id = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m(this.mAction).getId();
                return id;
            }
            return 0;
        }

        public CharSequence getLabel() {
            CharSequence label;
            if (Build.VERSION.SDK_INT >= 21) {
                label = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m(this.mAction).getLabel();
                return label;
            }
            return null;
        }

        public boolean perform(View view, Bundle bundle) {
            if (this.mCommand != null) {
                Class<? extends AccessibilityViewCommand.CommandArguments> cls = this.mViewCommandArgumentClass;
                AccessibilityViewCommand.CommandArguments commandArguments = null;
                if (cls != null) {
                    try {
                        AccessibilityViewCommand.CommandArguments newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                        try {
                            newInstance.setBundle(bundle);
                            commandArguments = newInstance;
                        } catch (Exception e) {
                            e = e;
                            commandArguments = newInstance;
                            Class<? extends AccessibilityViewCommand.CommandArguments> cls2 = this.mViewCommandArgumentClass;
                            String name = cls2 == null ? "null" : cls2.getName();
                            Log.e(TAG, "Failed to execute command with argument class ViewCommandArgument: " + name, e);
                            return this.mCommand.perform(view, commandArguments);
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                return this.mCommand.perform(view, commandArguments);
            }
            return false;
        }

        public AccessibilityActionCompat createReplacementAction(CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
            return new AccessibilityActionCompat(null, this.mId, charSequence, accessibilityViewCommand, this.mViewCommandArgumentClass);
        }

        public int hashCode() {
            Object obj = this.mAction;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof AccessibilityActionCompat)) {
                AccessibilityActionCompat accessibilityActionCompat = (AccessibilityActionCompat) obj;
                Object obj2 = this.mAction;
                return obj2 == null ? accessibilityActionCompat.mAction == null : obj2.equals(accessibilityActionCompat.mAction);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class CollectionInfoCompat {
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        final Object mInfo;

        public static CollectionInfoCompat obtain(int i, int i2, boolean z, int i3) {
            AccessibilityNodeInfo.CollectionInfo obtain;
            AccessibilityNodeInfo.CollectionInfo obtain2;
            if (Build.VERSION.SDK_INT >= 21) {
                obtain2 = AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3);
                return new CollectionInfoCompat(obtain2);
            } else if (Build.VERSION.SDK_INT >= 19) {
                obtain = AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z);
                return new CollectionInfoCompat(obtain);
            } else {
                return new CollectionInfoCompat(null);
            }
        }

        public static CollectionInfoCompat obtain(int i, int i2, boolean z) {
            AccessibilityNodeInfo.CollectionInfo obtain;
            if (Build.VERSION.SDK_INT >= 19) {
                obtain = AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z);
                return new CollectionInfoCompat(obtain);
            }
            return new CollectionInfoCompat(null);
        }

        CollectionInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public int getColumnCount() {
            int columnCount;
            if (Build.VERSION.SDK_INT >= 19) {
                columnCount = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m210m(this.mInfo).getColumnCount();
                return columnCount;
            }
            return -1;
        }

        public int getRowCount() {
            int rowCount;
            if (Build.VERSION.SDK_INT >= 19) {
                rowCount = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m210m(this.mInfo).getRowCount();
                return rowCount;
            }
            return -1;
        }

        public boolean isHierarchical() {
            boolean isHierarchical;
            if (Build.VERSION.SDK_INT >= 19) {
                isHierarchical = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m210m(this.mInfo).isHierarchical();
                return isHierarchical;
            }
            return false;
        }

        public int getSelectionMode() {
            int selectionMode;
            if (Build.VERSION.SDK_INT >= 21) {
                selectionMode = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m210m(this.mInfo).getSelectionMode();
                return selectionMode;
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public static class CollectionItemInfoCompat {
        final Object mInfo;

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            AccessibilityNodeInfo.CollectionItemInfo obtain;
            AccessibilityNodeInfo.CollectionItemInfo obtain2;
            if (Build.VERSION.SDK_INT >= 21) {
                obtain2 = AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2);
                return new CollectionItemInfoCompat(obtain2);
            } else if (Build.VERSION.SDK_INT >= 19) {
                obtain = AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z);
                return new CollectionItemInfoCompat(obtain);
            } else {
                return new CollectionItemInfoCompat(null);
            }
        }

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z) {
            AccessibilityNodeInfo.CollectionItemInfo obtain;
            if (Build.VERSION.SDK_INT >= 19) {
                obtain = AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z);
                return new CollectionItemInfoCompat(obtain);
            }
            return new CollectionItemInfoCompat(null);
        }

        CollectionItemInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public int getColumnIndex() {
            int columnIndex;
            if (Build.VERSION.SDK_INT >= 19) {
                columnIndex = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m190m(this.mInfo).getColumnIndex();
                return columnIndex;
            }
            return 0;
        }

        public int getColumnSpan() {
            int columnSpan;
            if (Build.VERSION.SDK_INT >= 19) {
                columnSpan = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m190m(this.mInfo).getColumnSpan();
                return columnSpan;
            }
            return 0;
        }

        public int getRowIndex() {
            int rowIndex;
            if (Build.VERSION.SDK_INT >= 19) {
                rowIndex = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m190m(this.mInfo).getRowIndex();
                return rowIndex;
            }
            return 0;
        }

        public int getRowSpan() {
            int rowSpan;
            if (Build.VERSION.SDK_INT >= 19) {
                rowSpan = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m190m(this.mInfo).getRowSpan();
                return rowSpan;
            }
            return 0;
        }

        @Deprecated
        public boolean isHeading() {
            boolean isHeading;
            if (Build.VERSION.SDK_INT >= 19) {
                isHeading = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m190m(this.mInfo).isHeading();
                return isHeading;
            }
            return false;
        }

        public boolean isSelected() {
            boolean isSelected;
            if (Build.VERSION.SDK_INT >= 21) {
                isSelected = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m190m(this.mInfo).isSelected();
                return isSelected;
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class RangeInfoCompat {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;

        public static RangeInfoCompat obtain(int i, float f, float f2, float f3) {
            AccessibilityNodeInfo.RangeInfo obtain;
            if (Build.VERSION.SDK_INT >= 19) {
                obtain = AccessibilityNodeInfo.RangeInfo.obtain(i, f, f2, f3);
                return new RangeInfoCompat(obtain);
            }
            return new RangeInfoCompat(null);
        }

        RangeInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public float getCurrent() {
            float current;
            if (Build.VERSION.SDK_INT >= 19) {
                current = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m213m(this.mInfo).getCurrent();
                return current;
            }
            return 0.0f;
        }

        public float getMax() {
            float max;
            if (Build.VERSION.SDK_INT >= 19) {
                max = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m213m(this.mInfo).getMax();
                return max;
            }
            return 0.0f;
        }

        public float getMin() {
            float min;
            if (Build.VERSION.SDK_INT >= 19) {
                min = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m213m(this.mInfo).getMin();
                return min;
            }
            return 0.0f;
        }

        public int getType() {
            int type;
            if (Build.VERSION.SDK_INT >= 19) {
                type = AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m213m(this.mInfo).getType();
                return type;
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public static final class TouchDelegateInfoCompat {
        final AccessibilityNodeInfo.TouchDelegateInfo mInfo;

        public TouchDelegateInfoCompat(Map<Region, View> map) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.mInfo = new AccessibilityNodeInfo.TouchDelegateInfo(map);
            } else {
                this.mInfo = null;
            }
        }

        TouchDelegateInfoCompat(AccessibilityNodeInfo.TouchDelegateInfo touchDelegateInfo) {
            this.mInfo = touchDelegateInfo;
        }

        public int getRegionCount() {
            int regionCount;
            if (Build.VERSION.SDK_INT >= 29) {
                regionCount = this.mInfo.getRegionCount();
                return regionCount;
            }
            return 0;
        }

        public Region getRegionAt(int i) {
            Region regionAt;
            if (Build.VERSION.SDK_INT >= 29) {
                regionAt = this.mInfo.getRegionAt(i);
                return regionAt;
            }
            return null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
            r3 = r2.mInfo.getTargetForRegion(r3);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public androidx.core.view.accessibility.AccessibilityNodeInfoCompat getTargetForRegion(android.graphics.Region r3) {
            /*
                r2 = this;
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 29
                if (r0 < r1) goto L13
                android.view.accessibility.AccessibilityNodeInfo$TouchDelegateInfo r0 = r2.mInfo
                android.view.accessibility.AccessibilityNodeInfo r3 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m(r0, r3)
                if (r3 == 0) goto L13
                androidx.core.view.accessibility.AccessibilityNodeInfoCompat r3 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.wrap(r3)
                return r3
            L13:
                r3 = 0
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.accessibility.AccessibilityNodeInfoCompat.TouchDelegateInfoCompat.getTargetForRegion(android.graphics.Region):androidx.core.view.accessibility.AccessibilityNodeInfoCompat");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccessibilityNodeInfoCompat wrapNonNullInstance(Object obj) {
        if (obj != null) {
            return new AccessibilityNodeInfoCompat(obj);
        }
        return null;
    }

    @Deprecated
    public AccessibilityNodeInfoCompat(Object obj) {
        this.mInfo = (AccessibilityNodeInfo) obj;
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.mInfo = accessibilityNodeInfo;
    }

    public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    @Deprecated
    public Object getInfo() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat obtain(View view) {
        return wrap(AccessibilityNodeInfo.obtain(view));
    }

    public static AccessibilityNodeInfoCompat obtain(View view, int i) {
        AccessibilityNodeInfo obtain;
        if (Build.VERSION.SDK_INT >= 16) {
            obtain = AccessibilityNodeInfo.obtain(view, i);
            return wrapNonNullInstance(obtain);
        }
        return null;
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return wrap(AccessibilityNodeInfo.obtain(accessibilityNodeInfoCompat.mInfo));
    }

    public void setSource(View view) {
        this.mVirtualDescendantId = -1;
        this.mInfo.setSource(view);
    }

    public void setSource(View view, int i) {
        this.mVirtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setSource(view, i);
        }
    }

    public AccessibilityNodeInfoCompat findFocus(int i) {
        AccessibilityNodeInfo findFocus;
        if (Build.VERSION.SDK_INT >= 16) {
            findFocus = this.mInfo.findFocus(i);
            return wrapNonNullInstance(findFocus);
        }
        return null;
    }

    public AccessibilityNodeInfoCompat focusSearch(int i) {
        AccessibilityNodeInfo focusSearch;
        if (Build.VERSION.SDK_INT >= 16) {
            focusSearch = this.mInfo.focusSearch(i);
            return wrapNonNullInstance(focusSearch);
        }
        return null;
    }

    public int getWindowId() {
        return this.mInfo.getWindowId();
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public AccessibilityNodeInfoCompat getChild(int i) {
        return wrapNonNullInstance(this.mInfo.getChild(i));
    }

    public void addChild(View view) {
        this.mInfo.addChild(view);
    }

    public void addChild(View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.addChild(view, i);
        }
    }

    public boolean removeChild(View view) {
        boolean removeChild;
        if (Build.VERSION.SDK_INT >= 21) {
            removeChild = this.mInfo.removeChild(view);
            return removeChild;
        }
        return false;
    }

    public boolean removeChild(View view, int i) {
        boolean removeChild;
        if (Build.VERSION.SDK_INT >= 21) {
            removeChild = this.mInfo.removeChild(view, i);
            return removeChild;
        }
        return false;
    }

    @Deprecated
    public int getActions() {
        return this.mInfo.getActions();
    }

    public void addAction(int i) {
        this.mInfo.addAction(i);
    }

    private List<Integer> extrasIntList(String str) {
        Bundle extras;
        Bundle extras2;
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        extras = this.mInfo.getExtras();
        ArrayList<Integer> integerArrayList = extras.getIntegerArrayList(str);
        if (integerArrayList == null) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            extras2 = this.mInfo.getExtras();
            extras2.putIntegerArrayList(str, arrayList);
            return arrayList;
        }
        return integerArrayList;
    }

    public void addAction(AccessibilityActionCompat accessibilityActionCompat) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.addAction(AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m(accessibilityActionCompat.mAction));
        }
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityActionCompat) {
        boolean removeAction;
        if (Build.VERSION.SDK_INT >= 21) {
            removeAction = this.mInfo.removeAction(AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m(accessibilityActionCompat.mAction));
            return removeAction;
        }
        return false;
    }

    public boolean performAction(int i) {
        return this.mInfo.performAction(i);
    }

    public boolean performAction(int i, Bundle bundle) {
        boolean performAction;
        if (Build.VERSION.SDK_INT >= 16) {
            performAction = this.mInfo.performAction(i, bundle);
            return performAction;
        }
        return false;
    }

    public void setMovementGranularities(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setMovementGranularities(i);
        }
    }

    public int getMovementGranularities() {
        int movementGranularities;
        if (Build.VERSION.SDK_INT >= 16) {
            movementGranularities = this.mInfo.getMovementGranularities();
            return movementGranularities;
        }
        return 0;
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String str) {
        ArrayList arrayList = new ArrayList();
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = this.mInfo.findAccessibilityNodeInfosByText(str);
        int size = findAccessibilityNodeInfosByText.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(wrap(findAccessibilityNodeInfosByText.get(i)));
        }
        return arrayList;
    }

    public AccessibilityNodeInfoCompat getParent() {
        return wrapNonNullInstance(this.mInfo.getParent());
    }

    public void setParent(View view) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo.setParent(view);
    }

    public void setParent(View view, int i) {
        this.mParentVirtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setParent(view, i);
        }
    }

    @Deprecated
    public void getBoundsInParent(Rect rect) {
        this.mInfo.getBoundsInParent(rect);
    }

    @Deprecated
    public void setBoundsInParent(Rect rect) {
        this.mInfo.setBoundsInParent(rect);
    }

    public void getBoundsInScreen(Rect rect) {
        this.mInfo.getBoundsInScreen(rect);
    }

    public void setBoundsInScreen(Rect rect) {
        this.mInfo.setBoundsInScreen(rect);
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public void setCheckable(boolean z) {
        this.mInfo.setCheckable(z);
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public void setChecked(boolean z) {
        this.mInfo.setChecked(z);
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public void setFocusable(boolean z) {
        this.mInfo.setFocusable(z);
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public void setFocused(boolean z) {
        this.mInfo.setFocused(z);
    }

    public boolean isVisibleToUser() {
        boolean isVisibleToUser;
        if (Build.VERSION.SDK_INT >= 16) {
            isVisibleToUser = this.mInfo.isVisibleToUser();
            return isVisibleToUser;
        }
        return false;
    }

    public void setVisibleToUser(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setVisibleToUser(z);
        }
    }

    public boolean isAccessibilityFocused() {
        boolean isAccessibilityFocused;
        if (Build.VERSION.SDK_INT >= 16) {
            isAccessibilityFocused = this.mInfo.isAccessibilityFocused();
            return isAccessibilityFocused;
        }
        return false;
    }

    public void setAccessibilityFocused(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setAccessibilityFocused(z);
        }
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public void setSelected(boolean z) {
        this.mInfo.setSelected(z);
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public void setClickable(boolean z) {
        this.mInfo.setClickable(z);
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public void setLongClickable(boolean z) {
        this.mInfo.setLongClickable(z);
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public void setEnabled(boolean z) {
        this.mInfo.setEnabled(z);
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public void setPassword(boolean z) {
        this.mInfo.setPassword(z);
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public void setScrollable(boolean z) {
        this.mInfo.setScrollable(z);
    }

    public boolean isTextSelectable() {
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.isTextSelectable(this.mInfo);
        }
        return false;
    }

    public void setTextSelectable(boolean z) {
        if (Build.VERSION.SDK_INT >= 33) {
            Api33Impl.setTextSelectable(this.mInfo, z);
        }
    }

    public boolean isImportantForAccessibility() {
        boolean isImportantForAccessibility;
        if (Build.VERSION.SDK_INT >= 24) {
            isImportantForAccessibility = this.mInfo.isImportantForAccessibility();
            return isImportantForAccessibility;
        }
        return true;
    }

    public void setImportantForAccessibility(boolean z) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setImportantForAccessibility(z);
        }
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public void setPackageName(CharSequence charSequence) {
        this.mInfo.setPackageName(charSequence);
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public void setClassName(CharSequence charSequence) {
        this.mInfo.setClassName(charSequence);
    }

    public CharSequence getText() {
        if (hasSpans()) {
            List<Integer> extrasIntList = extrasIntList(SPANS_START_KEY);
            List<Integer> extrasIntList2 = extrasIntList(SPANS_END_KEY);
            List<Integer> extrasIntList3 = extrasIntList(SPANS_FLAGS_KEY);
            List<Integer> extrasIntList4 = extrasIntList(SPANS_ID_KEY);
            SpannableString spannableString = new SpannableString(TextUtils.substring(this.mInfo.getText(), 0, this.mInfo.getText().length()));
            for (int i = 0; i < extrasIntList.size(); i++) {
                spannableString.setSpan(new AccessibilityClickableSpanCompat(extrasIntList4.get(i).intValue(), this, getExtras().getInt(SPANS_ACTION_ID_KEY)), extrasIntList.get(i).intValue(), extrasIntList2.get(i).intValue(), extrasIntList3.get(i).intValue());
            }
            return spannableString;
        }
        return this.mInfo.getText();
    }

    public void setText(CharSequence charSequence) {
        this.mInfo.setText(charSequence);
    }

    public void addSpansToExtras(CharSequence charSequence, View view) {
        if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 26) {
            return;
        }
        clearExtrasSpans();
        removeCollectedSpans(view);
        ClickableSpan[] clickableSpans = getClickableSpans(charSequence);
        if (clickableSpans == null || clickableSpans.length <= 0) {
            return;
        }
        getExtras().putInt(SPANS_ACTION_ID_KEY, R.id.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> orCreateSpansFromViewTags = getOrCreateSpansFromViewTags(view);
        for (int i = 0; clickableSpans != null && i < clickableSpans.length; i++) {
            int idForClickableSpan = idForClickableSpan(clickableSpans[i], orCreateSpansFromViewTags);
            orCreateSpansFromViewTags.put(idForClickableSpan, new WeakReference<>(clickableSpans[i]));
            addSpanLocationToExtras(clickableSpans[i], (Spanned) charSequence, idForClickableSpan);
        }
    }

    private SparseArray<WeakReference<ClickableSpan>> getOrCreateSpansFromViewTags(View view) {
        SparseArray<WeakReference<ClickableSpan>> spansFromViewTags = getSpansFromViewTags(view);
        if (spansFromViewTags == null) {
            SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
            view.setTag(R.id.tag_accessibility_clickable_spans, sparseArray);
            return sparseArray;
        }
        return spansFromViewTags;
    }

    private SparseArray<WeakReference<ClickableSpan>> getSpansFromViewTags(View view) {
        return (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
    }

    public static ClickableSpan[] getClickableSpans(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    private int idForClickableSpan(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                if (clickableSpan.equals(sparseArray.valueAt(i).get())) {
                    return sparseArray.keyAt(i);
                }
            }
        }
        int i2 = sClickableSpanId;
        sClickableSpanId = i2 + 1;
        return i2;
    }

    private boolean hasSpans() {
        return !extrasIntList(SPANS_START_KEY).isEmpty();
    }

    private void clearExtrasSpans() {
        Bundle extras;
        Bundle extras2;
        Bundle extras3;
        Bundle extras4;
        if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            extras.remove(SPANS_START_KEY);
            extras2 = this.mInfo.getExtras();
            extras2.remove(SPANS_END_KEY);
            extras3 = this.mInfo.getExtras();
            extras3.remove(SPANS_FLAGS_KEY);
            extras4 = this.mInfo.getExtras();
            extras4.remove(SPANS_ID_KEY);
        }
    }

    private void addSpanLocationToExtras(ClickableSpan clickableSpan, Spanned spanned, int i) {
        extrasIntList(SPANS_START_KEY).add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        extrasIntList(SPANS_END_KEY).add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        extrasIntList(SPANS_FLAGS_KEY).add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        extrasIntList(SPANS_ID_KEY).add(Integer.valueOf(i));
    }

    private void removeCollectedSpans(View view) {
        SparseArray<WeakReference<ClickableSpan>> spansFromViewTags = getSpansFromViewTags(view);
        if (spansFromViewTags != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < spansFromViewTags.size(); i++) {
                if (spansFromViewTags.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                spansFromViewTags.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public CharSequence getStateDescription() {
        Bundle extras;
        CharSequence stateDescription;
        if (BuildCompat.isAtLeastR()) {
            stateDescription = this.mInfo.getStateDescription();
            return stateDescription;
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            return extras.getCharSequence(STATE_DESCRIPTION_KEY);
        } else {
            return null;
        }
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mInfo.setContentDescription(charSequence);
    }

    public void setStateDescription(CharSequence charSequence) {
        Bundle extras;
        if (BuildCompat.isAtLeastR()) {
            this.mInfo.setStateDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            extras.putCharSequence(STATE_DESCRIPTION_KEY, charSequence);
        }
    }

    public String getUniqueId() {
        Bundle extras;
        String uniqueId;
        if (BuildCompat.isAtLeastT()) {
            uniqueId = this.mInfo.getUniqueId();
            return uniqueId;
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            return extras.getString(UNIQUE_ID_KEY);
        } else {
            return null;
        }
    }

    public void setUniqueId(String str) {
        Bundle extras;
        if (BuildCompat.isAtLeastT()) {
            this.mInfo.setUniqueId(str);
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            extras.putString(UNIQUE_ID_KEY, str);
        }
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public void setViewIdResourceName(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setViewIdResourceName(str);
        }
    }

    public String getViewIdResourceName() {
        String viewIdResourceName;
        if (Build.VERSION.SDK_INT >= 18) {
            viewIdResourceName = this.mInfo.getViewIdResourceName();
            return viewIdResourceName;
        }
        return null;
    }

    public int getLiveRegion() {
        int liveRegion;
        if (Build.VERSION.SDK_INT >= 19) {
            liveRegion = this.mInfo.getLiveRegion();
            return liveRegion;
        }
        return 0;
    }

    public void setLiveRegion(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setLiveRegion(i);
        }
    }

    public int getDrawingOrder() {
        int drawingOrder;
        if (Build.VERSION.SDK_INT >= 24) {
            drawingOrder = this.mInfo.getDrawingOrder();
            return drawingOrder;
        }
        return 0;
    }

    public void setDrawingOrder(int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setDrawingOrder(i);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
        r0 = r2.mInfo.getCollectionInfo();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat getCollectionInfo() {
        /*
            r2 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 19
            if (r0 < r1) goto L14
            android.view.accessibility.AccessibilityNodeInfo r0 = r2.mInfo
            android.view.accessibility.AccessibilityNodeInfo$CollectionInfo r0 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m209m(r0)
            if (r0 == 0) goto L14
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat r1 = new androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat
            r1.<init>(r0)
            return r1
        L14:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.accessibility.AccessibilityNodeInfoCompat.getCollectionInfo():androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat");
    }

    public void setCollectionInfo(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionInfo(obj == null ? null : AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m210m(((CollectionInfoCompat) obj).mInfo));
        }
    }

    public void setCollectionItemInfo(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionItemInfo(obj == null ? null : WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m190m(((CollectionItemInfoCompat) obj).mInfo));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
        r0 = r2.mInfo.getCollectionItemInfo();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat getCollectionItemInfo() {
        /*
            r2 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 19
            if (r0 < r1) goto L14
            android.view.accessibility.AccessibilityNodeInfo r0 = r2.mInfo
            android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo r0 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m211m(r0)
            if (r0 == 0) goto L14
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r1 = new androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat
            r1.<init>(r0)
            return r1
        L14:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.accessibility.AccessibilityNodeInfoCompat.getCollectionItemInfo():androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat");
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
        r0 = r2.mInfo.getRangeInfo();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.RangeInfoCompat getRangeInfo() {
        /*
            r2 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 19
            if (r0 < r1) goto L14
            android.view.accessibility.AccessibilityNodeInfo r0 = r2.mInfo
            android.view.accessibility.AccessibilityNodeInfo$RangeInfo r0 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m212m(r0)
            if (r0 == 0) goto L14
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$RangeInfoCompat r1 = new androidx.core.view.accessibility.AccessibilityNodeInfoCompat$RangeInfoCompat
            r1.<init>(r0)
            return r1
        L14:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.accessibility.AccessibilityNodeInfoCompat.getRangeInfo():androidx.core.view.accessibility.AccessibilityNodeInfoCompat$RangeInfoCompat");
    }

    public void setRangeInfo(RangeInfoCompat rangeInfoCompat) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setRangeInfo(AccessibilityNodeInfoCompat$$ExternalSyntheticApiModelOutline2.m213m(rangeInfoCompat.mInfo));
        }
    }

    public AccessibilityNodeInfo.ExtraRenderingInfo getExtraRenderingInfo() {
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getExtraRenderingInfo(this.mInfo);
        }
        return null;
    }

    public List<AccessibilityActionCompat> getActionList() {
        List actionList = Build.VERSION.SDK_INT >= 21 ? this.mInfo.getActionList() : null;
        if (actionList != null) {
            ArrayList arrayList = new ArrayList();
            int size = actionList.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new AccessibilityActionCompat(actionList.get(i)));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public void setContentInvalid(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setContentInvalid(z);
        }
    }

    public boolean isContentInvalid() {
        boolean isContentInvalid;
        if (Build.VERSION.SDK_INT >= 19) {
            isContentInvalid = this.mInfo.isContentInvalid();
            return isContentInvalid;
        }
        return false;
    }

    public boolean isContextClickable() {
        boolean isContextClickable;
        if (Build.VERSION.SDK_INT >= 23) {
            isContextClickable = this.mInfo.isContextClickable();
            return isContextClickable;
        }
        return false;
    }

    public void setContextClickable(boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mInfo.setContextClickable(z);
        }
    }

    public CharSequence getHintText() {
        Bundle extras;
        CharSequence hintText;
        if (Build.VERSION.SDK_INT >= 26) {
            hintText = this.mInfo.getHintText();
            return hintText;
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            return extras.getCharSequence(HINT_TEXT_KEY);
        } else {
            return null;
        }
    }

    public void setHintText(CharSequence charSequence) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setHintText(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            extras.putCharSequence(HINT_TEXT_KEY, charSequence);
        }
    }

    public void setError(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.setError(charSequence);
        }
    }

    public CharSequence getError() {
        CharSequence error;
        if (Build.VERSION.SDK_INT >= 21) {
            error = this.mInfo.getError();
            return error;
        }
        return null;
    }

    public void setLabelFor(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(view);
        }
    }

    public void setLabelFor(View view, int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(view, i);
        }
    }

    public AccessibilityNodeInfoCompat getLabelFor() {
        AccessibilityNodeInfo labelFor;
        if (Build.VERSION.SDK_INT >= 17) {
            labelFor = this.mInfo.getLabelFor();
            return wrapNonNullInstance(labelFor);
        }
        return null;
    }

    public void setLabeledBy(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(view);
        }
    }

    public void setLabeledBy(View view, int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(view, i);
        }
    }

    public AccessibilityNodeInfoCompat getLabeledBy() {
        AccessibilityNodeInfo labeledBy;
        if (Build.VERSION.SDK_INT >= 17) {
            labeledBy = this.mInfo.getLabeledBy();
            return wrapNonNullInstance(labeledBy);
        }
        return null;
    }

    public boolean canOpenPopup() {
        boolean canOpenPopup;
        if (Build.VERSION.SDK_INT >= 19) {
            canOpenPopup = this.mInfo.canOpenPopup();
            return canOpenPopup;
        }
        return false;
    }

    public void setCanOpenPopup(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCanOpenPopup(z);
        }
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByViewId(String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        if (Build.VERSION.SDK_INT >= 18) {
            findAccessibilityNodeInfosByViewId = this.mInfo.findAccessibilityNodeInfosByViewId(str);
            ArrayList arrayList = new ArrayList();
            for (AccessibilityNodeInfo accessibilityNodeInfo : findAccessibilityNodeInfosByViewId) {
                arrayList.add(wrap(accessibilityNodeInfo));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public Bundle getExtras() {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            return extras;
        }
        return new Bundle();
    }

    public int getInputType() {
        int inputType;
        if (Build.VERSION.SDK_INT >= 19) {
            inputType = this.mInfo.getInputType();
            return inputType;
        }
        return 0;
    }

    public void setInputType(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setInputType(i);
        }
    }

    public List<String> getAvailableExtraData() {
        List<String> availableExtraData;
        if (Build.VERSION.SDK_INT >= 26) {
            availableExtraData = this.mInfo.getAvailableExtraData();
            return availableExtraData;
        }
        return Collections.emptyList();
    }

    public void setAvailableExtraData(List<String> list) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setAvailableExtraData(list);
        }
    }

    public void setMaxTextLength(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.setMaxTextLength(i);
        }
    }

    public int getMaxTextLength() {
        int maxTextLength;
        if (Build.VERSION.SDK_INT >= 21) {
            maxTextLength = this.mInfo.getMaxTextLength();
            return maxTextLength;
        }
        return -1;
    }

    public void setTextSelection(int i, int i2) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setTextSelection(i, i2);
        }
    }

    public int getTextSelectionStart() {
        int textSelectionStart;
        if (Build.VERSION.SDK_INT >= 18) {
            textSelectionStart = this.mInfo.getTextSelectionStart();
            return textSelectionStart;
        }
        return -1;
    }

    public int getTextSelectionEnd() {
        int textSelectionEnd;
        if (Build.VERSION.SDK_INT >= 18) {
            textSelectionEnd = this.mInfo.getTextSelectionEnd();
            return textSelectionEnd;
        }
        return -1;
    }

    public AccessibilityNodeInfoCompat getTraversalBefore() {
        AccessibilityNodeInfo traversalBefore;
        if (Build.VERSION.SDK_INT >= 22) {
            traversalBefore = this.mInfo.getTraversalBefore();
            return wrapNonNullInstance(traversalBefore);
        }
        return null;
    }

    public void setTraversalBefore(View view) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view);
        }
    }

    public void setTraversalBefore(View view, int i) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view, i);
        }
    }

    public AccessibilityNodeInfoCompat getTraversalAfter() {
        AccessibilityNodeInfo traversalAfter;
        if (Build.VERSION.SDK_INT >= 22) {
            traversalAfter = this.mInfo.getTraversalAfter();
            return wrapNonNullInstance(traversalAfter);
        }
        return null;
    }

    public void setTraversalAfter(View view) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view);
        }
    }

    public void setTraversalAfter(View view, int i) {
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view, i);
        }
    }

    public AccessibilityWindowInfoCompat getWindow() {
        AccessibilityWindowInfo window;
        if (Build.VERSION.SDK_INT >= 21) {
            window = this.mInfo.getWindow();
            return AccessibilityWindowInfoCompat.wrapNonNullInstance(window);
        }
        return null;
    }

    public boolean isDismissable() {
        boolean isDismissable;
        if (Build.VERSION.SDK_INT >= 19) {
            isDismissable = this.mInfo.isDismissable();
            return isDismissable;
        }
        return false;
    }

    public void setDismissable(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setDismissable(z);
        }
    }

    public boolean isEditable() {
        boolean isEditable;
        if (Build.VERSION.SDK_INT >= 18) {
            isEditable = this.mInfo.isEditable();
            return isEditable;
        }
        return false;
    }

    public void setEditable(boolean z) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setEditable(z);
        }
    }

    public boolean isMultiLine() {
        boolean isMultiLine;
        if (Build.VERSION.SDK_INT >= 19) {
            isMultiLine = this.mInfo.isMultiLine();
            return isMultiLine;
        }
        return false;
    }

    public void setMultiLine(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setMultiLine(z);
        }
    }

    public CharSequence getTooltipText() {
        Bundle extras;
        CharSequence tooltipText;
        if (Build.VERSION.SDK_INT >= 28) {
            tooltipText = this.mInfo.getTooltipText();
            return tooltipText;
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            return extras.getCharSequence(TOOLTIP_TEXT_KEY);
        } else {
            return null;
        }
    }

    public void setTooltipText(CharSequence charSequence) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setTooltipText(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            extras.putCharSequence(TOOLTIP_TEXT_KEY, charSequence);
        }
    }

    public void setPaneTitle(CharSequence charSequence) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setPaneTitle(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            extras.putCharSequence(PANE_TITLE_KEY, charSequence);
        }
    }

    public CharSequence getPaneTitle() {
        Bundle extras;
        CharSequence paneTitle;
        if (Build.VERSION.SDK_INT >= 28) {
            paneTitle = this.mInfo.getPaneTitle();
            return paneTitle;
        } else if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            return extras.getCharSequence(PANE_TITLE_KEY);
        } else {
            return null;
        }
    }

    public boolean isScreenReaderFocusable() {
        boolean isScreenReaderFocusable;
        if (Build.VERSION.SDK_INT >= 28) {
            isScreenReaderFocusable = this.mInfo.isScreenReaderFocusable();
            return isScreenReaderFocusable;
        }
        return getBooleanProperty(1);
    }

    public void setScreenReaderFocusable(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setScreenReaderFocusable(z);
        } else {
            setBooleanProperty(1, z);
        }
    }

    public boolean isShowingHintText() {
        boolean isShowingHintText;
        if (Build.VERSION.SDK_INT >= 26) {
            isShowingHintText = this.mInfo.isShowingHintText();
            return isShowingHintText;
        }
        return getBooleanProperty(4);
    }

    public void setShowingHintText(boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setShowingHintText(z);
        } else {
            setBooleanProperty(4, z);
        }
    }

    public boolean isHeading() {
        boolean isHeading;
        if (Build.VERSION.SDK_INT >= 28) {
            isHeading = this.mInfo.isHeading();
            return isHeading;
        } else if (getBooleanProperty(2)) {
            return true;
        } else {
            CollectionItemInfoCompat collectionItemInfo = getCollectionItemInfo();
            return collectionItemInfo != null && collectionItemInfo.isHeading();
        }
    }

    public void setHeading(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setHeading(z);
        } else {
            setBooleanProperty(2, z);
        }
    }

    public boolean isTextEntryKey() {
        boolean isTextEntryKey;
        if (Build.VERSION.SDK_INT >= 29) {
            isTextEntryKey = this.mInfo.isTextEntryKey();
            return isTextEntryKey;
        }
        return getBooleanProperty(8);
    }

    public void setTextEntryKey(boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            this.mInfo.setTextEntryKey(z);
        } else {
            setBooleanProperty(8, z);
        }
    }

    public boolean refresh() {
        boolean refresh;
        if (Build.VERSION.SDK_INT >= 18) {
            refresh = this.mInfo.refresh();
            return refresh;
        }
        return false;
    }

    public CharSequence getRoleDescription() {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            return extras.getCharSequence(ROLE_DESCRIPTION_KEY);
        }
        return null;
    }

    public void setRoleDescription(CharSequence charSequence) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 19) {
            extras = this.mInfo.getExtras();
            extras.putCharSequence(ROLE_DESCRIPTION_KEY, charSequence);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
        r0 = r2.mInfo.getTouchDelegateInfo();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.TouchDelegateInfoCompat getTouchDelegateInfo() {
        /*
            r2 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 < r1) goto L14
            android.view.accessibility.AccessibilityNodeInfo r0 = r2.mInfo
            android.view.accessibility.AccessibilityNodeInfo$TouchDelegateInfo r0 = androidx.core.view.WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m191m(r0)
            if (r0 == 0) goto L14
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$TouchDelegateInfoCompat r1 = new androidx.core.view.accessibility.AccessibilityNodeInfoCompat$TouchDelegateInfoCompat
            r1.<init>(r0)
            return r1
        L14:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.accessibility.AccessibilityNodeInfoCompat.getTouchDelegateInfo():androidx.core.view.accessibility.AccessibilityNodeInfoCompat$TouchDelegateInfoCompat");
    }

    public void setTouchDelegateInfo(TouchDelegateInfoCompat touchDelegateInfoCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            this.mInfo.setTouchDelegateInfo(touchDelegateInfoCompat.mInfo);
        }
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof AccessibilityNodeInfoCompat)) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
            if (accessibilityNodeInfo == null) {
                if (accessibilityNodeInfoCompat.mInfo != null) {
                    return false;
                }
            } else if (!accessibilityNodeInfo.equals(accessibilityNodeInfoCompat.mInfo)) {
                return false;
            }
            return this.mVirtualDescendantId == accessibilityNodeInfoCompat.mVirtualDescendantId && this.mParentVirtualDescendantId == accessibilityNodeInfoCompat.mParentVirtualDescendantId;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(getPackageName());
        sb.append("; className: ");
        sb.append(getClassName());
        sb.append("; text: ");
        sb.append(getText());
        sb.append("; contentDescription: ");
        sb.append(getContentDescription());
        sb.append("; viewId: ");
        sb.append(getViewIdResourceName());
        sb.append("; uniqueId: ");
        sb.append(getUniqueId());
        sb.append("; checkable: ");
        sb.append(isCheckable());
        sb.append("; checked: ");
        sb.append(isChecked());
        sb.append("; focusable: ");
        sb.append(isFocusable());
        sb.append("; focused: ");
        sb.append(isFocused());
        sb.append("; selected: ");
        sb.append(isSelected());
        sb.append("; clickable: ");
        sb.append(isClickable());
        sb.append("; longClickable: ");
        sb.append(isLongClickable());
        sb.append("; enabled: ");
        sb.append(isEnabled());
        sb.append("; password: ");
        sb.append(isPassword());
        sb.append("; scrollable: " + isScrollable());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<AccessibilityActionCompat> actionList = getActionList();
            for (int i = 0; i < actionList.size(); i++) {
                AccessibilityActionCompat accessibilityActionCompat = actionList.get(i);
                String actionSymbolicName = getActionSymbolicName(accessibilityActionCompat.getId());
                if (actionSymbolicName.equals("ACTION_UNKNOWN") && accessibilityActionCompat.getLabel() != null) {
                    actionSymbolicName = accessibilityActionCompat.getLabel().toString();
                }
                sb.append(actionSymbolicName);
                if (i != actionList.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            int actions = getActions();
            while (actions != 0) {
                int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(actions);
                actions &= numberOfTrailingZeros ^ (-1);
                sb.append(getActionSymbolicName(numberOfTrailingZeros));
                if (actions != 0) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void setBooleanProperty(int i, boolean z) {
        Bundle extras = getExtras();
        if (extras != null) {
            int i2 = extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & (i ^ (-1));
            if (!z) {
                i = 0;
            }
            extras.putInt(BOOLEAN_PROPERTY_KEY, i | i2);
        }
    }

    private boolean getBooleanProperty(int i) {
        Bundle extras = getExtras();
        return extras != null && (extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & i) == i;
    }

    /* loaded from: classes.dex */
    private static class Api33Impl {
        private Api33Impl() {
        }

        public static AccessibilityNodeInfo.ExtraRenderingInfo getExtraRenderingInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getExtraRenderingInfo();
        }

        public static boolean isTextSelectable(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.isTextSelectable();
        }

        public static void setTextSelectable(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            accessibilityNodeInfo.setTextSelectable(z);
        }
    }
}