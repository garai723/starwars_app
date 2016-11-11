package com.example.garai.starwars;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.id.empty;

public class SecondActivity extends AppMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        backToTop();

        moveResult();

    }

    /**
     * backボタンが押されたとき
     */
    protected void backToTop() {
        Button returnButton = (Button) findViewById(R.id.button_back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    /**
     * resultボタンが押されたとき
     */
    protected void moveResult() {

        //TODO 送る値決まり次第resultに値渡す

        final Button button = (Button) findViewById(R.id.button_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker2);

                RadioGroup radioBlood = (RadioGroup) findViewById(R.id.radio_blood);
                RadioGroup radioWeapon = (RadioGroup) findViewById(R.id.radio_weapon);
                RadioGroup radioPartner = (RadioGroup) findViewById(R.id.radio_partner);
                RadioGroup radioHair = (RadioGroup) findViewById(R.id.radio_hair);

                int bloodId = radioBlood.getCheckedRadioButtonId();
                int weaponId = radioWeapon.getCheckedRadioButtonId();
                int partnerId = radioPartner.getCheckedRadioButtonId();
                int hairColorId = radioHair.getCheckedRadioButtonId();


                RadioButton bloodButton = (RadioButton) findViewById(bloodId);
                RadioButton weaponButton = (RadioButton) findViewById(weaponId);
                RadioButton partnerButton = (RadioButton) findViewById(partnerId);
                RadioButton hairButton = (RadioButton) findViewById(hairColorId);


                String blood = (String) bloodButton.getText();
                String weapon = (String) weaponButton.getText();
                String partner = (String) partnerButton.getText();
                String hairColor = (String) hairButton.getText();


                int birthYear = datePicker.getYear();
                int birthMonth = datePicker.getMonth();
                int birthDate = datePicker.getDayOfMonth();

                Log.d("VALUE", blood);
                Log.d("VALUE", weapon);
                Log.d("VALUE", partner);
                Log.d("VALUE", hairColor);
                Log.d("BIRTH", Integer.toString(birthYear) + "年" + Integer.toString(birthMonth) + "月" + Integer.toString(birthDate) + "日");
                Log.d("ID", getId());

                setNotification();

                Intent intent = new Intent(getApplication(), ResultActivity.class);
                intent.putExtra("BLOOD", blood);
                intent.putExtra("WEAPON", weapon);
                intent.putExtra("PARTNER", partner);
                intent.putExtra("BIRTHDAY", birthYear + "-" + birthMonth + "-" + birthDate);
                startActivity(intent);
                finish();
            }
        });
    }


    /**
     * ローカルプッシュ通知をセットする
     */
    protected void setNotification() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);         //年を取得
        int month = calendar.get(Calendar.MONTH);       //月を取得
        int date = calendar.get(Calendar.DATE);         //日を取得
//        int hour = calendar.get(Calendar.HOUR);         //時を取得
//        int minute = calendar.get(Calendar.MINUTE);    //分を取得


        // 初回実行時間設定（過去の時間設定の場合即実行）
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, date + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 00);

        //処理の実行感覚
        long interval = 60 * 60 * 12 * 1000;

        Intent intent = new Intent(getApplicationContext(), Notifier.class);
        intent.putExtra("intentId", 2);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        // アラームをセットする
        AlarmManager am = (AlarmManager) SecondActivity.this.getSystemService(ALARM_SERVICE);
        //am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);　//単発処理
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pending); //繰り返し処理

        //確認用トースト（後で消去）
        Toast.makeText(getApplicationContext(), "SET NOTIFICATION", Toast.LENGTH_SHORT).show();
    }
}

