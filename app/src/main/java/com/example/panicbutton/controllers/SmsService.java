package com.example.panicbutton.controllers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;



public class SmsService {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    public PannicActivityController pannicActivityController;
    Context context;
    Activity activity;

    public SmsService(Context ctx, Activity act) {
        context = ctx;
        pannicActivityController = new PannicActivityController(context);
        activity = act;
    }


    public boolean sendSMS() {
        String message = pannicActivityController.getPanicText();
        String SmsNotificationMessage;
        boolean retVal = false;
        for (String phoneNo : pannicActivityController.getPhoneNumbers()) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.SEND_SMS)) {
                } else {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);
                retVal = true;
            }

        }
        return retVal;
    }
}
