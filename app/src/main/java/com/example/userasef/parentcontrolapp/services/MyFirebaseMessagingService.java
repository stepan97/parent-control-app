package com.example.userasef.parentcontrolapp.services;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.userasef.parentcontrolapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static String TAG = "TAGO";
    private static String CHANNEL_ID = "my_notification_channel_id";
    private static int notificationId = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "FROM: " + remoteMessage.getFrom());

        // Check if message contains data
        if(remoteMessage.getData() != null){
            Log.d(TAG, "DATA: " + remoteMessage.getData());
        }

        String title = "No title";
        String body = "No body";

        // Check if message contains notification
        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "NOTIFICATION: " + remoteMessage.getNotification().getBody());
            body = remoteMessage.getNotification().getBody();
            title = remoteMessage.getNotification().getTitle();

            showNotification(title, body);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showNotification(String title, String content){
        createNotificationChannel();

        if(title == null || title.equals(""))
            title = getString(R.string.notification_title);
        if(content == null || content.equals(""))
            content = getString(R.string.notification_content);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(content))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, mBuilder.build());
        notificationId++;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
