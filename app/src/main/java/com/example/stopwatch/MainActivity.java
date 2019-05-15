package com.example.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button start, pause, reset;

    long msTime,startTime,timeBuff,updateTime=0L;

    Handler handler;

    int seconds, minutes, miliseconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView)findViewById(R.id.timer);
        start = (Button)findViewById(R.id.btStart);
        pause = (Button)findViewById(R.id.btPause);
        reset = (Button)findViewById(R.id.btReset);

        handler = new Handler();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);

                reset.setEnabled(false);
            }
        });



      pause.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              timeBuff = timeBuff + msTime;

              handler.removeCallbacks(runnable);

              reset.setEnabled(true);
          }
      });

      reset.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              msTime = 0L;
              startTime = 0L;
              timeBuff = 0L;
              updateTime = 0L;
              seconds = 0;
              minutes = 0;
              miliseconds = 0;

              timer.setText("00:00:00");
          }
      });

    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            msTime = SystemClock.uptimeMillis() - startTime;

            updateTime = timeBuff + msTime;

            seconds = (int) (updateTime/1000);

            minutes = seconds / 60;
            seconds = seconds % 60;
            miliseconds = (int)(updateTime % 1000);

            timer.setText(""+minutes+":"+String.format("%02d", seconds)
             +":"+String.format("%03d", miliseconds));

            handler.postDelayed(this,0);
        }
    };


}
