package com.example.android.ihatealarms;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlarmReceiver extends Service {
    int alarmselected;
    public MediaPlayer alarm;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         alarmselected= intent.getIntExtra("alarmselected",1);

        switch (alarmselected){
            case 1: alarm = MediaPlayer.create(getBaseContext(),R.raw.alarm1);
              break;
            case 2: alarm = MediaPlayer.create(getBaseContext(),R.raw.alarm2);
                break;
            case 3: alarm = MediaPlayer.create(getBaseContext(),R.raw.alarm3);
                break;
            case 4: alarm = MediaPlayer.create(getBaseContext(),R.raw.alarm4);
                break;
            case 5: alarm = MediaPlayer.create(getBaseContext(),R.raw.alarm5);
                break;

        }


        alarm.start();
        Toast.makeText(getApplicationContext(),"Alarm......",Toast.LENGTH_SHORT).show();


        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartintent = new Intent(getApplicationContext(),this.getClass());
        restartintent.setPackage(getPackageName());
        startService(restartintent);

        super.onTaskRemoved(rootIntent);
    }
}