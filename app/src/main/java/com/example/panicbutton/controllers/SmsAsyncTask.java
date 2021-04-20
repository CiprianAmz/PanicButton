package com.example.panicbutton.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

public class SmsAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String SMS_MESSAGE_FAILED = "SMS failed... Please protect yourself and call the police.";
    private static final String SMS_MESSAGE_SUCCESS = "SOS was send to your friends.";
    SmsService smsService;
    int retry;
    boolean retryFlag;

    public SmsAsyncTask(Context ctx, Activity act){
        smsService = new SmsService(ctx,act);
        retry = 0;
        retryFlag = true;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String msg = SMS_MESSAGE_FAILED;
        retryFlag = true;
        while (retryFlag == true){
            msg = smsService.sendSMS();
            if( msg == SMS_MESSAGE_FAILED){
                retry++;
            }else {
                retryFlag = false;
            }
            if(retry == 3){
                retryFlag = false;
                retry = 0;
            }
        }
        return msg;
    }

}
