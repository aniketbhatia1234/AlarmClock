package com.example.android.ihatealarms;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class stopwatchactivity extends Fragment {

    Button start, stop, lap, reset;
    TextView timer;
    Handler customhandler= new Handler();
    LinearLayout lapcontainer;

    long starttime=0, timeinmillis=0,timeswap=0,updatetime=0;
    Runnable updateTimerThread= new Runnable() {
        @Override
        public void run() {
            timeinmillis=SystemClock.uptimeMillis()-starttime;
            updatetime= timeswap+timeinmillis;
            int secs= (int)(updatetime/1000);
            int mins=secs/60;
            secs%=60;
            int millisecs= (int)(updatetime%1000);
            timer.setText(""+mins+"."+String.format("%02d",secs)+"."+String.format("%03d",millisecs));
            customhandler.postDelayed(this,0);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.stopwatch_fragment,container,false);

        start=view.findViewById(R.id.start);
        stop=view.findViewById(R.id.stop);
        lap=view.findViewById(R.id.lap);
        reset=view.findViewById(R.id.reset);
        timer=view.findViewById(R.id.timer);
        lapcontainer= view.findViewById(R.id.container);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttime = SystemClock.uptimeMillis();

                customhandler.postDelayed(updateTimerThread,0);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeswap+=timeinmillis;
                customhandler.removeCallbacks(updateTimerThread);
            }
        });

        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView= inflater.inflate(R.layout.row,null);
                TextView textView = addView.findViewById(R.id.lapcontent);
                textView.setText(timer.getText());
                lapcontainer.addView(addView);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttime = SystemClock.uptimeMillis();
                timeinmillis=0;
                timeswap=0;
                updatetime=0;
                timer.setText("0.00");
            }
        });


        return view;
    }
}
