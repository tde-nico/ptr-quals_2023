package androidx.core.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import androidx.core.content.pm.PackageInfoCompat$$ExternalSyntheticApiModelOutline0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    private static String sEnabledNotificationListeners;
    private static SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    private static final Object sEnabledNotificationListenersLock = new Object();
    private static Set<String> sEnabledNotificationListenerPackages = new HashSet();
    private static final Object sLock = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface Task {
        void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException;
    }

    public static NotificationManagerCompat from(Context context) {
        return new NotificationManagerCompat(context);
    }

    private NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
    }

    public void cancel(int i) {
        cancel(null, i);
    }

    public void cancel(String str, int i) {
        this.mNotificationManager.cancel(str, i);
        if (Build.VERSION.SDK_INT <= 19) {
            pushSideChannelQueue(new CancelTask(this.mContext.getPackageName(), i, str));
        }
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
        if (Build.VERSION.SDK_INT <= 19) {
            pushSideChannelQueue(new CancelTask(this.mContext.getPackageName()));
        }
    }

    public void notify(int i, Notification notification) {
        notify(null, i, notification);
    }

    public void notify(String str, int i, Notification notification) {
        if (useSideChannelForNotification(notification)) {
            pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), i, str, notification));
            this.mNotificationManager.cancel(str, i);
            return;
        }
        this.mNotificationManager.notify(str, i, notification);
    }

    public boolean areNotificationsEnabled() {
        boolean areNotificationsEnabled;
        if (Build.VERSION.SDK_INT >= 24) {
            areNotificationsEnabled = this.mNotificationManager.areNotificationsEnabled();
            return areNotificationsEnabled;
        } else if (Build.VERSION.SDK_INT >= 19) {
            AppOpsManager m8m = NavUtils$$ExternalSyntheticApiModelOutline0.m8m(this.mContext.getSystemService("appops"));
            ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
            String packageName = this.mContext.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            try {
                Class<?> cls = Class.forName(NavUtils$$ExternalSyntheticApiModelOutline0.m22m().getName());
                return ((Integer) cls.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class).invoke(m8m, Integer.valueOf(((Integer) cls.getDeclaredField(OP_POST_NOTIFICATION).get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
                return true;
            }
        } else {
            return true;
        }
    }

    public int getImportance() {
        int importance;
        if (Build.VERSION.SDK_INT >= 24) {
            importance = this.mNotificationManager.getImportance();
            return importance;
        }
        return IMPORTANCE_UNSPECIFIED;
    }

    public void createNotificationChannel(NotificationChannel notificationChannel) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void createNotificationChannel(NotificationChannelCompat notificationChannelCompat) {
        createNotificationChannel(notificationChannelCompat.getNotificationChannel());
    }

    public void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mNotificationManager.createNotificationChannelGroup(notificationChannelGroup);
        }
    }

    public void createNotificationChannelGroup(NotificationChannelGroupCompat notificationChannelGroupCompat) {
        createNotificationChannelGroup(notificationChannelGroupCompat.getNotificationChannelGroup());
    }

    public void createNotificationChannels(List<NotificationChannel> list) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mNotificationManager.createNotificationChannels(list);
        }
    }

    public void createNotificationChannelsCompat(List<NotificationChannelCompat> list) {
        if (Build.VERSION.SDK_INT < 26 || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (NotificationChannelCompat notificationChannelCompat : list) {
            arrayList.add(notificationChannelCompat.getNotificationChannel());
        }
        this.mNotificationManager.createNotificationChannels(arrayList);
    }

    public void createNotificationChannelGroups(List<NotificationChannelGroup> list) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mNotificationManager.createNotificationChannelGroups(list);
        }
    }

    public void createNotificationChannelGroupsCompat(List<NotificationChannelGroupCompat> list) {
        if (Build.VERSION.SDK_INT < 26 || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (NotificationChannelGroupCompat notificationChannelGroupCompat : list) {
            arrayList.add(notificationChannelGroupCompat.getNotificationChannelGroup());
        }
        this.mNotificationManager.createNotificationChannelGroups(arrayList);
    }

    public void deleteNotificationChannel(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mNotificationManager.deleteNotificationChannel(str);
        }
    }

    public void deleteNotificationChannelGroup(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mNotificationManager.deleteNotificationChannelGroup(str);
        }
    }

    public void deleteUnlistedNotificationChannels(Collection<String> collection) {
        List<Object> notificationChannels;
        String id;
        String id2;
        String parentChannelId;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationChannels = this.mNotificationManager.getNotificationChannels();
            for (Object obj : notificationChannels) {
                NotificationChannel m9m = NavUtils$$ExternalSyntheticApiModelOutline0.m9m(obj);
                id = m9m.getId();
                if (!collection.contains(id)) {
                    if (Build.VERSION.SDK_INT >= 30) {
                        parentChannelId = m9m.getParentChannelId();
                        if (collection.contains(parentChannelId)) {
                        }
                    }
                    NotificationManager notificationManager = this.mNotificationManager;
                    id2 = m9m.getId();
                    notificationManager.deleteNotificationChannel(id2);
                }
            }
        }
    }

    public NotificationChannel getNotificationChannel(String str) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationChannel = this.mNotificationManager.getNotificationChannel(str);
            return notificationChannel;
        }
        return null;
    }

    public NotificationChannelCompat getNotificationChannelCompat(String str) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT < 26 || (notificationChannel = getNotificationChannel(str)) == null) {
            return null;
        }
        return new NotificationChannelCompat(notificationChannel);
    }

    public NotificationChannel getNotificationChannel(String str, String str2) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT >= 30) {
            notificationChannel = this.mNotificationManager.getNotificationChannel(str, str2);
            return notificationChannel;
        }
        return getNotificationChannel(str);
    }

    public NotificationChannelCompat getNotificationChannelCompat(String str, String str2) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT < 26 || (notificationChannel = getNotificationChannel(str, str2)) == null) {
            return null;
        }
        return new NotificationChannelCompat(notificationChannel);
    }

    public NotificationChannelGroup getNotificationChannelGroup(String str) {
        String id;
        NotificationChannelGroup notificationChannelGroup;
        if (Build.VERSION.SDK_INT >= 28) {
            notificationChannelGroup = this.mNotificationManager.getNotificationChannelGroup(str);
            return notificationChannelGroup;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            for (NotificationChannelGroup notificationChannelGroup2 : getNotificationChannelGroups()) {
                NotificationChannelGroup m = PackageInfoCompat$$ExternalSyntheticApiModelOutline0.m((Object) notificationChannelGroup2);
                id = m.getId();
                if (id.equals(str)) {
                    return m;
                }
            }
        }
        return null;
    }

    public NotificationChannelGroupCompat getNotificationChannelGroupCompat(String str) {
        NotificationChannelGroup notificationChannelGroup;
        if (Build.VERSION.SDK_INT >= 28) {
            NotificationChannelGroup notificationChannelGroup2 = getNotificationChannelGroup(str);
            if (notificationChannelGroup2 != null) {
                return new NotificationChannelGroupCompat(notificationChannelGroup2);
            }
            return null;
        } else if (Build.VERSION.SDK_INT < 26 || (notificationChannelGroup = getNotificationChannelGroup(str)) == null) {
            return null;
        } else {
            return new NotificationChannelGroupCompat(notificationChannelGroup, getNotificationChannels());
        }
    }

    public List<NotificationChannel> getNotificationChannels() {
        List<NotificationChannel> notificationChannels;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationChannels = this.mNotificationManager.getNotificationChannels();
            return notificationChannels;
        }
        return Collections.emptyList();
    }

    public List<NotificationChannelCompat> getNotificationChannelsCompat() {
        if (Build.VERSION.SDK_INT >= 26) {
            List<NotificationChannel> notificationChannels = getNotificationChannels();
            if (!notificationChannels.isEmpty()) {
                ArrayList arrayList = new ArrayList(notificationChannels.size());
                for (NotificationChannel notificationChannel : notificationChannels) {
                    arrayList.add(new NotificationChannelCompat(NavUtils$$ExternalSyntheticApiModelOutline0.m9m((Object) notificationChannel)));
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }

    public List<NotificationChannelGroup> getNotificationChannelGroups() {
        List<NotificationChannelGroup> notificationChannelGroups;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationChannelGroups = this.mNotificationManager.getNotificationChannelGroups();
            return notificationChannelGroups;
        }
        return Collections.emptyList();
    }

    public List<NotificationChannelGroupCompat> getNotificationChannelGroupsCompat() {
        List<NotificationChannel> notificationChannels;
        if (Build.VERSION.SDK_INT >= 26) {
            List<NotificationChannelGroup> notificationChannelGroups = getNotificationChannelGroups();
            if (!notificationChannelGroups.isEmpty()) {
                if (Build.VERSION.SDK_INT >= 28) {
                    notificationChannels = Collections.emptyList();
                } else {
                    notificationChannels = getNotificationChannels();
                }
                ArrayList arrayList = new ArrayList(notificationChannelGroups.size());
                for (NotificationChannelGroup notificationChannelGroup : notificationChannelGroups) {
                    NotificationChannelGroup m = PackageInfoCompat$$ExternalSyntheticApiModelOutline0.m((Object) notificationChannelGroup);
                    if (Build.VERSION.SDK_INT >= 28) {
                        arrayList.add(new NotificationChannelGroupCompat(m));
                    } else {
                        arrayList.add(new NotificationChannelGroupCompat(m, notificationChannels));
                    }
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }

    public static Set<String> getEnabledListenerPackages(Context context) {
        Set<String> set;
        String string = Settings.Secure.getString(context.getContentResolver(), SETTING_ENABLED_NOTIFICATION_LISTENERS);
        synchronized (sEnabledNotificationListenersLock) {
            if (string != null) {
                if (!string.equals(sEnabledNotificationListeners)) {
                    String[] split = string.split(":", -1);
                    HashSet hashSet = new HashSet(split.length);
                    for (String str : split) {
                        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                        if (unflattenFromString != null) {
                            hashSet.add(unflattenFromString.getPackageName());
                        }
                    }
                    sEnabledNotificationListenerPackages = hashSet;
                    sEnabledNotificationListeners = string;
                }
            }
            set = sEnabledNotificationListenerPackages;
        }
        return set;
    }

    private static boolean useSideChannelForNotification(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    private void pushSideChannelQueue(Task task) {
        synchronized (sLock) {
            if (sSideChannelManager == null) {
                sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
            }
            sSideChannelManager.queueTask(task);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SideChannelManager implements Handler.Callback, ServiceConnection {
        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();
        private Set<String> mCachedEnabledPackages = new HashSet();

        SideChannelManager(Context context) {
            this.mContext = context;
            HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), this);
        }

        public void queueTask(Task task) {
            this.mHandler.obtainMessage(0, task).sendToTarget();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                handleQueueTask((Task) message.obj);
                return true;
            } else if (i == 1) {
                ServiceConnectedEvent serviceConnectedEvent = (ServiceConnectedEvent) message.obj;
                handleServiceConnected(serviceConnectedEvent.componentName, serviceConnectedEvent.iBinder);
                return true;
            } else if (i == 2) {
                handleServiceDisconnected((ComponentName) message.obj);
                return true;
            } else if (i != 3) {
                return false;
            } else {
                handleRetryListenerQueue((ComponentName) message.obj);
                return true;
            }
        }

        private void handleQueueTask(Task task) {
            updateListenerMap();
            for (ListenerRecord listenerRecord : this.mRecordMap.values()) {
                listenerRecord.taskQueue.add(task);
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceConnected(ComponentName componentName, IBinder iBinder) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                listenerRecord.service = INotificationSideChannel.Stub.asInterface(iBinder);
                listenerRecord.retryCount = 0;
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                ensureServiceUnbound(listenerRecord);
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                processListenerQueue(listenerRecord);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Connected to service " + componentName);
            }
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Disconnected from service " + componentName);
            }
            this.mHandler.obtainMessage(2, componentName).sendToTarget();
        }

        private void updateListenerMap() {
            Set<String> enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if (enabledListenerPackages.equals(this.mCachedEnabledPackages)) {
                return;
            }
            this.mCachedEnabledPackages = enabledListenerPackages;
            List<ResolveInfo> queryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                    ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w(NotificationManagerCompat.TAG, "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.mRecordMap.containsKey(componentName2)) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Adding listener record for " + componentName2);
                    }
                    this.mRecordMap.put(componentName2, new ListenerRecord(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, ListenerRecord>> it = this.mRecordMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, ListenerRecord> next = it.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Removing listener record for " + next.getKey());
                    }
                    ensureServiceUnbound(next.getValue());
                    it.remove();
                }
            }
        }

        private boolean ensureServiceBound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                return true;
            }
            listenerRecord.bound = this.mContext.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(listenerRecord.componentName), this, 33);
            if (listenerRecord.bound) {
                listenerRecord.retryCount = 0;
            } else {
                Log.w(NotificationManagerCompat.TAG, "Unable to bind to listener " + listenerRecord.componentName);
                this.mContext.unbindService(this);
            }
            return listenerRecord.bound;
        }

        private void ensureServiceUnbound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                this.mContext.unbindService(this);
                listenerRecord.bound = false;
            }
            listenerRecord.service = null;
        }

        private void scheduleListenerRetry(ListenerRecord listenerRecord) {
            if (this.mHandler.hasMessages(3, listenerRecord.componentName)) {
                return;
            }
            listenerRecord.retryCount++;
            if (listenerRecord.retryCount > 6) {
                Log.w(NotificationManagerCompat.TAG, "Giving up on delivering " + listenerRecord.taskQueue.size() + " tasks to " + listenerRecord.componentName + " after " + listenerRecord.retryCount + " retries");
                listenerRecord.taskQueue.clear();
                return;
            }
            int i = (1 << (listenerRecord.retryCount - 1)) * 1000;
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Scheduling retry for " + i + " ms");
            }
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, listenerRecord.componentName), i);
        }

        private void processListenerQueue(ListenerRecord listenerRecord) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Processing component " + listenerRecord.componentName + ", " + listenerRecord.taskQueue.size() + " queued tasks");
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            if (!ensureServiceBound(listenerRecord) || listenerRecord.service == null) {
                scheduleListenerRetry(listenerRecord);
                return;
            }
            while (true) {
                Task peek = listenerRecord.taskQueue.peek();
                if (peek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Sending task " + peek);
                    }
                    peek.send(listenerRecord.service);
                    listenerRecord.taskQueue.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Remote service has died: " + listenerRecord.componentName);
                    }
                } catch (RemoteException e) {
                    Log.w(NotificationManagerCompat.TAG, "RemoteException communicating with " + listenerRecord.componentName, e);
                }
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            scheduleListenerRetry(listenerRecord);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class ListenerRecord {
            final ComponentName componentName;
            INotificationSideChannel service;
            boolean bound = false;
            ArrayDeque<Task> taskQueue = new ArrayDeque<>();
            int retryCount = 0;

            ListenerRecord(ComponentName componentName) {
                this.componentName = componentName;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
            this.componentName = componentName;
            this.iBinder = iBinder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class NotifyTask implements Task {
        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        NotifyTask(String str, int i, String str2, Notification notification) {
            this.packageName = str;
            this.id = i;
            this.tag = str2;
            this.notif = notification;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException {
            iNotificationSideChannel.notify(this.packageName, this.id, this.tag, this.notif);
        }

        public String toString() {
            return "NotifyTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CancelTask implements Task {
        final boolean all;
        final int id;
        final String packageName;
        final String tag;

        CancelTask(String str) {
            this.packageName = str;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        CancelTask(String str, int i, String str2) {
            this.packageName = str;
            this.id = i;
            this.tag = str2;
            this.all = false;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException {
            if (this.all) {
                iNotificationSideChannel.cancelAll(this.packageName);
            } else {
                iNotificationSideChannel.cancel(this.packageName, this.id, this.tag);
            }
        }

        public String toString() {
            return "CancelTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + ", all:" + this.all + "]";
        }
    }
}