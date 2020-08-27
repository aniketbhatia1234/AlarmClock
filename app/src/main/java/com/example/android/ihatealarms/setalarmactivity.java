package com.example.android.ihatealarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class setalarmactivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TextView time;
    Calendar cal;
    int hourselected = -1, minselected = -1;

    Button setalarm;
    Button cancelalarm;
    CheckBox repeat;
    TextView sun, mon, tue, wed, thurs, fri, sat;
    boolean[] selected;
    Drawable highlight;
    Drawable unhighlight;
    Spinner alarmtone;
    int alarmselected;
    LinearLayout alarmsetcontainer;
    String[] daysoftheweek;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setalarm);
        time = findViewById(R.id.time);
        setalarm = findViewById(R.id.setalarm);

        repeat = findViewById(R.id.repeat);
        selected = new boolean[7];
        alarmtone= (Spinner) findViewById(R.id.spinner);
        String[] alarms = new String[]{"Alarm1","Alarm2", "Alarm3", "Alarm4","Alarm5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alarms);
        alarmtone.setAdapter(adapter);
        daysoftheweek= new String[]{"Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

        alarmtone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:alarmselected=1;
                        break;
                    case 1:alarmselected=2;
                        break;
                    case 2:alarmselected=3;
                        break;
                    case 3: alarmselected=4;
                        break;
                    case 4: alarmselected=5;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        for (int i = 0; i < 7; i++) {
            selected[i] = false;
        }

        sun = findViewById(R.id.sunday);
        mon = findViewById(R.id.monday);
        tue = findViewById(R.id.tuesday);
        wed = findViewById(R.id.wednesday);
        thurs = findViewById(R.id.thursday);
        fri = findViewById(R.id.friday);
        sat = findViewById(R.id.saturday);


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker = new TimepickerFragment();
                timepicker.show(getSupportFragmentManager(), "time picker");
            }
        });



        setalarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                checkpermission();
            }
        });

        repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    sun.setEnabled(false);
                    mon.setEnabled(false);
                    tue.setEnabled(false);
                    wed.setEnabled(false);
                    thurs.setEnabled(false);
                    fri.setEnabled(false);
                    sat.setEnabled(false);

                    sun.setBackground(unhighlight);
                    selected[0] = !selected[0];
                    mon.setBackground(unhighlight);
                    selected[1] = !selected[1];
                    tue.setBackground(unhighlight);
                    selected[2] = !selected[2];
                    wed.setBackground(unhighlight);
                    selected[3] = !selected[3];
                    thurs.setBackground(unhighlight);
                    selected[4] = !selected[4];
                    fri.setBackground(unhighlight);
                    selected[5] = !selected[5];
                    sat.setBackground(unhighlight);
                    selected[6] = !selected[6];

                }

                else {
                    sun.setEnabled(true);
                    mon.setEnabled(true);
                    tue.setEnabled(true);
                    wed.setEnabled(true);
                    thurs.setEnabled(true);
                    fri.setEnabled(true);
                    sat.setEnabled(true);

                    sun.setBackground(highlight);
                    selected[0] = !selected[0];
                    mon.setBackground(highlight);
                    selected[1] = !selected[1];
                    tue.setBackground(highlight);
                    selected[2] = !selected[2];
                    wed.setBackground(highlight);
                    selected[3] = !selected[3];
                    thurs.setBackground(highlight);
                    selected[4] = !selected[4];
                    fri.setBackground(highlight);
                    selected[5] = !selected[5];
                    sat.setBackground(highlight);
                    selected[6] = !selected[6];
                }
            }
        });

      highlight = getResources().getDrawable(R.drawable.highlightday);
        unhighlight = getResources().getDrawable(R.drawable.buttonstyle);

        sun.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (!selected[0]) {
                    sun.setBackground(highlight);

                } else
                    sun.setBackground(unhighlight);
                selected[0] = !selected[0];
            }
        });

        mon.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (!selected[1]) {
                    mon.setBackground(highlight);

                } else
                    mon.setBackground(unhighlight);
                selected[1] = !selected[1];
            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (!selected[2]) {
                    tue.setBackground(highlight);

                } else
                    tue.setBackground(unhighlight);
                selected[2] = !selected[2];
            }
        });

        wed.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (!selected[3]) {
                    wed.setBackground(highlight);

                } else
                    wed.setBackground(unhighlight);
                selected[3] = !selected[3];
            }
        });

        thurs.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (!selected[4]) {
                    thurs.setBackground(highlight);

                } else
                    thurs.setBackground(unhighlight);
                selected[4] = !selected[4];
            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (!selected[5]) {
                    fri.setBackground(highlight);

                } else
                    fri.setBackground(unhighlight);
                selected[5] = !selected[5];
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (!selected[6]) {
                    sat.setBackground(highlight);

                } else
                    sat.setBackground(unhighlight);
                selected[6] = !selected[6];
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkpermission() {
        int flag=0;
        if (hourselected == -1) {
            Toast.makeText(getApplicationContext(), "Select time", Toast.LENGTH_SHORT).show();
        }

        if(repeat.isChecked()){

            for(int i = 0; i<7; i++){
                if(selected[i]){
                   flag=1;
                   break;
                }
            }
            if(flag==0){
                Toast.makeText(getApplicationContext(), "Select atleast one day in the week to repeat",Toast.LENGTH_SHORT).show();

            }
            else
                for(int i=0; i<7;i++){
                    if(selected[i])
                        alarmschedule(i+1);
                }
        }
        else {
            cal = Calendar.getInstance();
            alarmschedule(cal.get(Calendar.DAY_OF_WEEK));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void alarmschedule(int dayselected) {

            cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, dayselected);
            cal.set(Calendar.HOUR_OF_DAY, hourselected);
            cal.set(Calendar.MINUTE, minselected);
            cal.set(Calendar.SECOND, 0);
            Intent intent = new Intent(setalarmactivity.this, AlarmReceiver.class);
            intent.putExtra("alarmselected", alarmselected);

            PendingIntent pendingIntent = PendingIntent.getForegroundService(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),7*24*3600*1000,pendingIntent);

        Toast.makeText(getApplicationContext(),"Alarm is Set",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourselected=hourOfDay;
        minselected=minute;
        time.setText(String.format("%02d:%02d", hourOfDay, minute));
    }



}