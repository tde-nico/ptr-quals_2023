package androidx.core.content.pm;

import android.app.NotificationChannelGroup;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothManager;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.hardware.ConsumerIrManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.nsd.NsdManager;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import android.os.UserManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.view.accessibility.CaptioningManager;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class PackageInfoCompat$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ NotificationChannelGroup m(Object obj) {
        return (NotificationChannelGroup) obj;
    }

    /* renamed from: m  reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ShortcutInfo m76m(Object obj) {
        return (ShortcutInfo) obj;
    }

    /* renamed from: m  reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ CancellationSignal m77m(Object obj) {
        return (CancellationSignal) obj;
    }

    public static /* bridge */ /* synthetic */ Class m() {
        return SubscriptionManager.class;
    }

    /* renamed from: m  reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m90m(Object obj) {
        return obj instanceof OperationCanceledException;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return TvInputManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$10() {
        return NsdManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$11() {
        return UsageStatsManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$12() {
        return CameraManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$13() {
        return JobScheduler.class;
    }

    public static /* bridge */ /* synthetic */ Class m$14() {
        return LauncherApps.class;
    }

    public static /* bridge */ /* synthetic */ Class m$15() {
        return MediaProjectionManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$16() {
        return MediaSessionManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$17() {
        return RestrictionsManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$18() {
        return TelecomManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$2() {
        return CaptioningManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$3() {
        return ConsumerIrManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$4() {
        return PrintManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$5() {
        return BluetoothManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$6() {
        return DisplayManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$7() {
        return UserManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$8() {
        return InputManager.class;
    }

    public static /* bridge */ /* synthetic */ Class m$9() {
        return MediaRouter.class;
    }
}