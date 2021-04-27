package com.example.panicbutton.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.panicbutton.R;
import com.example.panicbutton.controllers.DropCheckService;
import com.example.panicbutton.controllers.LocationService;
import com.example.panicbutton.controllers.MainActivityController;
import com.example.panicbutton.controllers.ReminderService;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView  currentLocation;
    private MainActivityController mainActivityController;
    private SensorManager sensorManager;
    private LocationService locationService;
    private DropCheckService dropCheckService;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocation = findViewById(R.id.CurrentLocation);

        mainActivityController = new MainActivityController(this);
        currentLocation.setText(mainActivityController.getLocation());
        locationService = new LocationService(this, currentLocation);


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

        dropCheckService = new DropCheckService();
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && mainActivityController.getDropFlag()){
            double ay = event.values[1];

            if(dropCheckService.checkForDrop(ay)) {
                Intent intent = new Intent(this, PanicActivity.class);
                startActivity(intent);

                Log.d("MainActivity", "SENSOR: " + String.valueOf(ay));
            }
            else {
                Log.d("MainActivity", "SENSOR: " + String.valueOf(ay));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}