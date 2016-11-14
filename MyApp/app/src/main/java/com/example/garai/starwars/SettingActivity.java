package com.example.garai.starwars;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);



    }

    /**
     * 通知設定
     */
    protected void setTime(){
        Button button = (Button)findViewById(R.id.button_timeset);
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);

                int notifyTime = timePicker.getHour();
                int notifyMinute = timePicker.getMinute();

                Log.d("TIME", String.valueOf(notifyTime));
                Log.d("MINUTE", String.valueOf(notifyMinute));


            }
        });
    }
}
