package com.example.samsung.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    /*http://liveonthekeyboard.tistory.com/129*/

    private static final long START_TIME_IN_MILLIS = 60000; //1분 -> 나중에 여기다가 설정하면 될듯.
    private TextView mTextViewCountDown;
    private Button mButtonStart;
    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer pCountDownTimer;
    private boolean mTimerRunning;
    private long mTimerLeftInMillis = START_TIME_IN_MILLIS; //xml에서 -10

    int value =590;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStart = findViewById(R.id.button_start);
        mProgressBar = findViewById(R.id.myProgressBar);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTimerRunning) {
                    startProgressBar();
                    startTimer();

                }
            }
        });


    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimerLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTimerLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                show();
            }
        }.start();

        mTimerRunning = true;
        mButtonStart.setText("");
    }
    public void startProgressBar(){
        pCountDownTimer = new CountDownTimer(mTimerLeftInMillis,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis = millisUntilFinished;
                if(value>0) {
                    mProgressBar.setProgress(--value);
                }

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimerLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimerLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);


    }
    /*new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });*/
    private void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Time is over");
        builder.setMessage("Finished?");
        builder.setPositiveButton("Yes", null); //나중에 null 에다가 뭔가하는것일듯
        builder.setNegativeButton("No",null);
        builder.show();
    }
}