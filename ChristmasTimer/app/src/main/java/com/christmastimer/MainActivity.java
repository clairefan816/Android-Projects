package com.christmastimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes * 60;
        String minutesString = Integer.toString(minutes);
        String secondString = Integer.toString(seconds);
        if (seconds <= 9){
            secondString = "0" + secondString;
        }
        timerTextView.setText(minutesString + " : " + secondString);
    }

    public void controlTimer(View view){
        if (!counterIsActive) {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            // take care of the delay, so we add 100 miliseconds here
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    //update the timer
                    // l here is the miniseconds
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    // normally use this in the creat(this, R.raw.bells)
                    // here this indicate the CountDownTimer
                    // so we have to refer to that manually
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bells);
                    mplayer.start();
                }
            }.start();
        } else {
            resetTimer();
        }

    }

    public void resetTimer(){
        timerTextView.setText("0 : 30");
        timerSeekBar.setProgress(30);
        controllerButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        //in seconds
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}