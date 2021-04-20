package com.example.panicbutton.controllers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import com.example.panicbutton.R;

public class NotificationService {
    private NotificationManager mNotifyManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private static final String SMS_MESSAGE_FAILED = "SMS failed... Please protect yourself and call the police.";
    private static final String SMS_MESSAGE_SUCCESS = "SOS was send to your friends.";
    private String SmsNotificationMessage;
    Context context;

    public NotificationService(Context ctx){
        context = ctx;
    }

    private NotificationCompat.Builder getNotificationBuilder(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setContentTitle("SOS")
                .setContentText(SmsNotificationMessage + "")
                .setSmallIcon(R.mipmap.ic_launcher);
        return notifyBuilder;
    }
    public void sendNotification(boolean status){
        if(status){
            SmsNotificationMessage = SMS_MESSAGE_SUCCESS;
        }else{
            SmsNotificationMessage = SMS_MESSAGE_FAILED;
        }
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }


    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Panic Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification received when the Panic messages are sent.");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

}
