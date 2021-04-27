package com.example.panicbutton.controllers;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;

public class SmsAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String SMS_MESSAGE_FAILED = "SMS failed... Please protect yourself and call the police.";
    private static final String SMS_MESSAGE_SUCCESS = "SOS was send to your friends.";

    private static final int SMS_SENT = 1;
    private static final int SMS_FAILED = 2;
    private static final int MAX_RETRY_COUNT = 3;

    SmsService smsService;
    NotificationService notificationService;

    public SmsAsyncTask(Context ctx, Activity act){
        smsService = new SmsService(ctx,act);
        notificationService = new NotificationService(ctx);
    }

    @Override
    protected String doInBackground(Void... voids) {
        int status = 0;
        int retryCount = 0;

        smsService.sendSMS();
        status = smsService.getCurSMSstatus();

        while (retryCount < MAX_RETRY_COUNT && status != SMS_SENT){
            status = smsService.getCurSMSstatus();

            if( status == SMS_FAILED){
                retryCount++;
                smsService.sendSMS();
            }
        }

        return null;
    }

}
