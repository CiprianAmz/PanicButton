package com.example.panicbutton.controllers;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;

public class SmsAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String SMS_MESSAGE_FAILED = "SMS failed... Please protect yourself and call the police.";
    private static final String SMS_MESSAGE_SUCCESS = "SOS was send to your friends.";
    SmsService smsService;
    NotificationService notificationService;
    int retry;
    boolean retryFlag;

    public SmsAsyncTask(Context ctx, Activity act){
        smsService = new SmsService(ctx,act);
        notificationService = new NotificationService(ctx);
        retry = 0;
        retryFlag = true;
    }

    @Override
    protected String doInBackground(Void... voids) {
        boolean status = false;
        retryFlag = true;
        while (retryFlag == true){
            status = smsService.sendSMS();
            if( status == false){
                retry++;
            }else {
                retryFlag = false;
            }
            if(retry == 3){
                retryFlag = false;
                retry = 0;
            }
        }
        notificationService.createNotificationChannel();
        notificationService.sendNotification(status);
        return null;
    }

}
