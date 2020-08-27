package com.example.android.ihatealarms;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class timeractivity extends Fragment {

    private static long StartTime;
    EditText gettimemins,gettimesecs;
    TextView countdowntxtview;
    Button starttimerbtn,resettimerbtn;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    boolean timerrunning;
    long timeleftinmillis;
    String mins, secs;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer_fragment,container,false);
        gettimemins = view.findViewById(R.id.gettimemins);
        gettimesecs = view.findViewById(R.id.gettimesecs);

        countdowntxtview= view.findViewById(R.id.countdown);
        starttimerbtn = view.findViewById(R.id.starttimer);
        resettimerbtn = view.findViewById(R.id.resettimer);

        starttimerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mins= gettimemins.getEditableText().toString();
                secs= gettimesecs.getEditableText().toString();
                if(mins.isEmpty()||secs.isEmpty()) {
                Toast.makeText(getContext(),"Enter Time",Toast.LENGTH_SHORT).show();
            }
                else {

                    StartTime= Long.parseLong(mins)*60000+Long.parseLong(secs)*1000;
                    timeleftinmillis =StartTime;

                    if (timerrunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            }
        });


        resettimerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updatecountdowntext();

        return view;
    }
    private void startTimer() {
        countDownTimer= new CountDownTimer(timeleftinmillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinmillis= millisUntilFinished;
                updatecountdowntext();
            }

            @Override
            public void onFinish() {
                timerrunning= false;
                starttimerbtn.setText("Start");
                starttimerbtn.setVisibility(View.INVISIBLE);
                resettimerbtn.setVisibility(View.VISIBLE);
                mediaPlayer= MediaPlayer.create(getContext(), R.raw.alarm5);
                mediaPlayer.start();
            }
        }.start();

        timerrunning= true;
        starttimerbtn.setText("PAUSE");
        resettimerbtn.setVisibility(View.INVISIBLE);

    }

    private void updatecountdowntext() {
        int minutes= (int) timeleftinmillis/60000;
        int secs= (int) (timeleftinmillis/1000)%60;
        String timeleft =String.format(Locale.getDefault(),"%02d.%02d",minutes,secs);
        countdowntxtview.setText(timeleft);
    }

    private void resetTimer() {
        mediaPlayer.stop();
        timeleftinmillis=  StartTime;
        updatecountdowntext();
        resettimerbtn.setVisibility(View.INVISIBLE);
        starttimerbtn.setVisibility(View.VISIBLE);
    }


    private void pauseTimer() {
        countDownTimer.cancel();
        timerrunning=false;
        starttimerbtn.setText("Start");
        resettimerbtn.setVisibility(View.VISIBLE);
    }
}
