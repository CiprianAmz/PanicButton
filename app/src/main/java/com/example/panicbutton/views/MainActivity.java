package com.example.panicbutton.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.panicbutton.R;
import com.example.panicbutton.controllers.LocationAsyncTask;
import com.example.panicbutton.controllers.MainActivityController;
import com.example.panicbutton.controllers.ReminderService;

public class MainActivity extends AppCompatActivity {
    TextView  currentLocation;
    MainActivityController mainActivityController;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocation = findViewById(R.id.CurrentLocation);

        mainActivityController = new MainActivityController(this);
        currentLocation.setText(mainActivityController.getLocation());
        startTask();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);

            Intent intent = new Intent(getApplicationContext(), ReminderService.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void launchPanicActivity(View view) {
        Log.d("MainActivity", "Panic");

        Intent intent = new Intent(this, PanicActivity.class);
        startActivity(intent);
    }

    public void launchLocationActivity(View view) {
        Log.d("MainActivity", "Location");
        Intent intent = new Intent(this,LocationActivity.class);
        startActivity(intent);
        currentLocation.setText(mainActivityController.getLocation());
    }

    public void launchManageContactsActivity(View view) {
        Intent intent = new Intent(this, ManageContactsActivity.class);
        startActivity(intent);
    }


    public void launchSettingsActivity(View view) {
        Log.d("MainActivity", "Settings");
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startTask() {

        // Start the AsyncTask.
        // The AsyncTask has a callback that will update the text view.
        new LocationAsyncTask( this, currentLocation).execute();
    }
}