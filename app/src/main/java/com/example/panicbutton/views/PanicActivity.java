package com.example.panicbutton.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panicbutton.R;
import com.example.panicbutton.controllers.PannicActivityController;
import com.example.panicbutton.controllers.SmsAsyncTask;
import com.example.panicbutton.controllers.SmsService;

public class PanicActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0;
    public PannicActivityController pannicActivityController;
    SmsService smsService;
    SmsAsyncTask smsAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        TextView panicText = (TextView) findViewById(R.id.PanicText);

        pannicActivityController = new PannicActivityController(this);
        smsService = new SmsService(this,this);
        smsAsyncTask = new SmsAsyncTask(this,this);
        //createNotificationChannel();
        sendSMSMessage();
        panicText.setText(pannicActivityController.getPanicText());

    }



    protected void sendSMSMessage() {
        //SmsNotificationMessage = smsService.sendSMS();
        startSmsTask();
       // sendNotification();
    }
    public void startSmsTask(){
        smsAsyncTask.execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    String message = pannicActivityController.getPanicText();

                    for(String phoneNo:pannicActivityController.getPhoneNumbers()) {
                        smsManager.sendTextMessage(phoneNo, null, message, null, null);
                        Toast.makeText(getApplicationContext(), "SMS sent.",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }



}