package com.example.handwash_reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;

public class NotificationHelper extends ContextWrapper {
    private static final String CHANNEL_ID="personal_noti";
    private static final String CHANNEL_NAME="mychan";
    private NotificationManager manager;
    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }
    private void createChannels()
    {
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(Color.GREEN);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(notificationChannel);
    }
    public NotificationManager getManager()
    {
        if(manager==null)
            manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            return manager;
    }
    public Notification.Builder getChannelNotification(String title,String body)
    {
        return new Notification.Builder(getApplicationContext(),CHANNEL_ID).setContentText("Immediately")
                .setContentTitle("Wash")
                .setSmallIcon(R.drawable.ic_comment)
                .setAutoCancel(true);
    }
}
