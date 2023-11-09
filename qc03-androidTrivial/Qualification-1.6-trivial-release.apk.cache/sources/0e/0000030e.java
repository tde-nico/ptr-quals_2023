package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public class NotificationChannelCompat {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private static final int DEFAULT_LIGHT_COLOR = 0;
    private static final boolean DEFAULT_SHOW_BADGE = true;
    AudioAttributes mAudioAttributes;
    private boolean mBypassDnd;
    private boolean mCanBubble;
    String mConversationId;
    String mDescription;
    String mGroupId;
    final String mId;
    int mImportance;
    private boolean mImportantConversation;
    int mLightColor;
    boolean mLights;
    private int mLockscreenVisibility;
    CharSequence mName;
    String mParentId;
    boolean mShowBadge;
    Uri mSound;
    boolean mVibrationEnabled;
    long[] mVibrationPattern;

    /* loaded from: classes.dex */
    public static class Builder {
        private final NotificationChannelCompat mChannel;

        public Builder(String str, int i) {
            this.mChannel = new NotificationChannelCompat(str, i);
        }

        public Builder setName(CharSequence charSequence) {
            this.mChannel.mName = charSequence;
            return this;
        }

        public Builder setImportance(int i) {
            this.mChannel.mImportance = i;
            return this;
        }

        public Builder setDescription(String str) {
            this.mChannel.mDescription = str;
            return this;
        }

        public Builder setGroup(String str) {
            this.mChannel.mGroupId = str;
            return this;
        }

        public Builder setShowBadge(boolean z) {
            this.mChannel.mShowBadge = z;
            return this;
        }

        public Builder setSound(Uri uri, AudioAttributes audioAttributes) {
            this.mChannel.mSound = uri;
            this.mChannel.mAudioAttributes = audioAttributes;
            return this;
        }

        public Builder setLightsEnabled(boolean z) {
            this.mChannel.mLights = z;
            return this;
        }

        public Builder setLightColor(int i) {
            this.mChannel.mLightColor = i;
            return this;
        }

        public Builder setVibrationEnabled(boolean z) {
            this.mChannel.mVibrationEnabled = z;
            return this;
        }

        public Builder setVibrationPattern(long[] jArr) {
            this.mChannel.mVibrationEnabled = (jArr == null || jArr.length <= 0) ? false : NotificationChannelCompat.DEFAULT_SHOW_BADGE;
            this.mChannel.mVibrationPattern = jArr;
            return this;
        }

        public Builder setConversationId(String str, String str2) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.mChannel.mParentId = str;
                this.mChannel.mConversationId = str2;
            }
            return this;
        }

        public NotificationChannelCompat build() {
            return this.mChannel;
        }
    }

    NotificationChannelCompat(String str, int i) {
        AudioAttributes audioAttributes;
        this.mShowBadge = DEFAULT_SHOW_BADGE;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        this.mId = (String) Preconditions.checkNotNull(str);
        this.mImportance = i;
        if (Build.VERSION.SDK_INT >= 21) {
            audioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
            this.mAudioAttributes = audioAttributes;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public NotificationChannelCompat(android.app.NotificationChannel r4) {
        /*
            r3 = this;
            java.lang.String r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m23m(r4)
            int r1 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m$1(r4)
            r3.<init>(r0, r1)
            java.lang.CharSequence r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m21m(r4)
            r3.mName = r0
            java.lang.String r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m$3(r4)
            r3.mDescription = r0
            java.lang.String r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m$4(r4)
            r3.mGroupId = r0
            boolean r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m40m$4(r4)
            r3.mShowBadge = r0
            android.net.Uri r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m12m(r4)
            r3.mSound = r0
            android.media.AudioAttributes r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m11m(r4)
            r3.mAudioAttributes = r0
            boolean r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m$5(r4)
            r3.mLights = r0
            int r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m$2(r4)
            r3.mLightColor = r0
            boolean r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m28m(r4)
            r3.mVibrationEnabled = r0
            long[] r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m34m(r4)
            r3.mVibrationPattern = r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 30
            if (r0 < r1) goto L59
            java.lang.String r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m35m$1(r4)
            r3.mParentId = r0
            java.lang.String r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m37m$2(r4)
            r3.mConversationId = r0
        L59:
            boolean r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m36m$1(r4)
            r3.mBypassDnd = r0
            int r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m(r4)
            r3.mLockscreenVisibility = r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r0 < r2) goto L71
            boolean r0 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m38m$2(r4)
            r3.mCanBubble = r0
        L71:
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r1) goto L7b
            boolean r4 = androidx.core.app.NavUtils$$ExternalSyntheticApiModelOutline0.m39m$3(r4)
            r3.mImportantConversation = r4
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationChannelCompat.<init>(android.app.NotificationChannel):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationChannel getNotificationChannel() {
        String str;
        String str2;
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationChannel notificationChannel = new NotificationChannel(this.mId, this.mName, this.mImportance);
        notificationChannel.setDescription(this.mDescription);
        notificationChannel.setGroup(this.mGroupId);
        notificationChannel.setShowBadge(this.mShowBadge);
        notificationChannel.setSound(this.mSound, this.mAudioAttributes);
        notificationChannel.enableLights(this.mLights);
        notificationChannel.setLightColor(this.mLightColor);
        notificationChannel.setVibrationPattern(this.mVibrationPattern);
        notificationChannel.enableVibration(this.mVibrationEnabled);
        if (Build.VERSION.SDK_INT >= 30 && (str = this.mParentId) != null && (str2 = this.mConversationId) != null) {
            notificationChannel.setConversationId(str, str2);
        }
        return notificationChannel;
    }

    public Builder toBuilder() {
        return new Builder(this.mId, this.mImportance).setName(this.mName).setDescription(this.mDescription).setGroup(this.mGroupId).setShowBadge(this.mShowBadge).setSound(this.mSound, this.mAudioAttributes).setLightsEnabled(this.mLights).setLightColor(this.mLightColor).setVibrationEnabled(this.mVibrationEnabled).setVibrationPattern(this.mVibrationPattern).setConversationId(this.mParentId, this.mConversationId);
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getImportance() {
        return this.mImportance;
    }

    public Uri getSound() {
        return this.mSound;
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public boolean shouldShowLights() {
        return this.mLights;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public boolean shouldVibrate() {
        return this.mVibrationEnabled;
    }

    public long[] getVibrationPattern() {
        return this.mVibrationPattern;
    }

    public boolean canShowBadge() {
        return this.mShowBadge;
    }

    public String getGroup() {
        return this.mGroupId;
    }

    public String getParentChannelId() {
        return this.mParentId;
    }

    public String getConversationId() {
        return this.mConversationId;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    public boolean canBubble() {
        return this.mCanBubble;
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }
}