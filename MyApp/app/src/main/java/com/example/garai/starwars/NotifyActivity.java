package com.example.garai.starwars;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class NotifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        // 日時を指定したアラーム
        Button  button = (Button)this.findViewById(R.id.button_notify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar2 = Calendar.getInstance();

                int year = calendar2.get(Calendar.YEAR);         //年を取得
                int month = calendar2.get(Calendar.MONTH);       //月を取得
                int date = calendar2.get(Calendar.DATE);         //日を取得
                int hour = calendar2.get(Calendar.HOUR);         //時を取得
                int minute = calendar2.get(Calendar.MINUTE);    //分を取得


                // 過去の時間は即実行されます
                calendar2.set(Calendar.YEAR, year);
                calendar2.set(Calendar.MONTH, month);
                calendar2.set(Calendar.DATE, date);
                calendar2.set(Calendar.HOUR_OF_DAY, hour);
                calendar2.set(Calendar.MINUTE, minute );


                Intent intent = new Intent(getApplicationContext(), Notifier.class);
                intent.putExtra("intentId", 2);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager)NotifyActivity.this.getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pending);
                Toast.makeText(getApplicationContext(), "ALARM 2", Toast.LENGTH_SHORT).show();

            }
        });
    }

}