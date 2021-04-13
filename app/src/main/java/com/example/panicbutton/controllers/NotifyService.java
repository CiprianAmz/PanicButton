package com.example.panicbutton.controllers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.panicbutton.R;

import static androidx.core.content.ContextCompat.getSystemService;

public class NotifyService extends BroadcastReceiver {
    private NotificationManager mNotifyManager;

    private static final String PRIMARY_CHANNEL_ID = "reminder_channel";

    private static final int NOTIFICATION_ID = 1;


    @Override
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);
        sendNotification(context);
    }

    private NotificationCompat.Builder getNotificationBuilder(Context context){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setContentTitle("Stay Safe")
                .setContentText("Please take all the precautions to be safe!")
                .setSmallIcon(R.mipmap.ic_launcher);
        return notifyBuilder;
    }

    public void sendNotification(Context context){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder(context);
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }


    public void createNotificationChannel(Context context) {
        mNotifyManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Stay Safe Reminder", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification received to remind to take precautions.");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
}