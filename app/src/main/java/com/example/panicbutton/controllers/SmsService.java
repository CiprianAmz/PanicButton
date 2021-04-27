package com.example.panicbutton.controllers;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.Toast;


public class SmsService {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    public PannicActivityController pannicActivityController;
    Context context;
    Activity activity;
    NotificationService notificationService;
    private static final String SMS_MESSAGE_FAILED = "SMS failed... Please protect yourself and call the police.";
    private static final String SMS_MESSAGE_SUCCESS = "SOS was send to your friends.";

    public SmsService(Context ctx, Activity act) {
        context = ctx;
        pannicActivityController = new PannicActivityController(context);
        activity = act;
        notificationService = new NotificationService(ctx);
    }

    static boolean retVal = false;
    public boolean sendSMS() {
        String message = pannicActivityController.getPanicText();
        String SmsNotificationMessage;
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
                retVal = false;
                String SENT = "SMS_SENT";
                String DELIVERED = "SMS_DELIVERED";
                PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT), 0);
                PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,new Intent(DELIVERED), 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNo, null,message,sentPI, deliveredPI);
                // ---when the SMS has been sent---
                context.registerReceiver(
                        new BroadcastReceiver()
                        {
                            @Override
                            public void onReceive(Context arg0,Intent arg1)
                            {
                                switch(getResultCode())
                                {
                                    case Activity.RESULT_OK:
                                        SetFlag();
                                        notificationService.createNotificationChannel();
                                        notificationService.sendNotification(true);
                                        context.unregisterReceiver(this);
                                        break;
                                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                        context.unregisterReceiver(this);
                                        notificationService.createNotificationChannel();
                                        notificationService.sendNotification(false);
                                        break;
                                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                                        context.unregisterReceiver(this);
                                        notificationService.createNotificationChannel();
                                        notificationService.sendNotification(false);
                                        break;
                                    case SmsManager.RESULT_ERROR_NULL_PDU:
                                        context.unregisterReceiver(this);
                                        notificationService.createNotificationChannel();
                                        notificationService.sendNotification(false);
                                        break;
                                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                                        context.unregisterReceiver(this);
                                        notificationService.createNotificationChannel();
                                        notificationService.sendNotification(false);
                                        break;
                                    default:
                                        notificationService.createNotificationChannel();
                                        notificationService.sendNotification(false);
                                        break;
                                }
                            }
                        }, new IntentFilter(SENT));

                // ---when the SMS has been delivered---
                context.registerReceiver(
                        new BroadcastReceiver()
                        {

                            @Override
                            public void onReceive(Context arg0,Intent arg1)
                            {
                                switch(getResultCode())
                                {
                                    case Activity.RESULT_OK:
                                        SetFlag();
                                        context.unregisterReceiver(this);
                                        break;
                                    case Activity.RESULT_CANCELED:
                                        context.unregisterReceiver(this);
                                        break;
                                }
                            }
                        }, new IntentFilter(DELIVERED));




//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(phoneNo, null, message, null, null);


            }
        }
        return retVal;
    }

    void SetFlag(){
        retVal = true;
    }

}
