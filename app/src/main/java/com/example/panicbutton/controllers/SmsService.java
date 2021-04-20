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
    private static final String SMS_MESSAGE_FAILED = "SMS failed... Please protect yourself and call the police.";
    private static final String SMS_MESSAGE_SUCCESS = "SOS was send to your friends.";
    Context context;
    Activity activity;

    public SmsService(Context ctx, Activity act) {
        context = ctx;
        pannicActivityController = new PannicActivityController(context);
        activity = act;
    }


    public String sendSMS() {
        String message = pannicActivityController.getPanicText();
        String SmsNotificationMessage;
        SmsNotificationMessage = SMS_MESSAGE_FAILED;
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
                SmsNotificationMessage = SMS_MESSAGE_SUCCESS;
            }

        }
        return SmsNotificationMessage;
    }
}
