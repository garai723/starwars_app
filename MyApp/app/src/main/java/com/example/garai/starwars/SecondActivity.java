package com.example.garai.starwars;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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


        //START TEST

        int max = 87;
        int min = 1;
        int result = 0;

        ArrayList<String> ansList = new ArrayList<String>();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = 0; result == 0; i++) {

            ansList.add("A");

            arrayList = calcNumberOfUserAnswer(max, min, ansList.get(i));

            max = arrayList.get(0);
            min = arrayList.get(1);
            result = arrayList.get(2);

            Log.d("MAX", Integer.toString(max));
            Log.d("MIN", Integer.toString(min));
            Log.d("RESULT" + i, Integer.toString(result));

            //END TEST

        }

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
                Spinner spinnerColor = (Spinner) findViewById(R.id.spinner_color);
                Spinner spinnerBlood = (Spinner) findViewById(R.id.spinner_blood);
                Spinner spinnerWeapon = (Spinner) findViewById(R.id.spinner_weapon);
                Spinner spinnerPartner = (Spinner) findViewById(R.id.spinner_partner);

                String hairColor = (String) spinnerColor.getSelectedItem();
                String bloodType = (String) spinnerBlood.getSelectedItem();
                String partner=(String)spinnerPartner.getSelectedItem();
                String weapon=(String)spinnerWeapon.getSelectedItem();
                int hairColorId=(int)spinnerColor.getSelectedItemId();
                int bloodId=(int)spinnerBlood.getSelectedItemId();
                int weaponId=(int)spinnerWeapon.getSelectedItemId();
                int partnerId=(int)spinnerPartner.getSelectedItemId();
                int birthYear = datePicker.getYear();
                int birthMonth = datePicker.getMonth();
                int birthDate = datePicker.getDayOfMonth();

                Log.d("VALUE",hairColorId+":"+hairColor);
                Log.d("VALUE",bloodId+":"+bloodType );
                Log.d("VALUE",weaponId+":"+weapon);
                Log.d("VALUE",partnerId+":"+partner);
                Log.d("BIRTH", Integer.toString(birthYear) + "年" + Integer.toString(birthMonth) + "月" + Integer.toString(birthDate) + "日");
                Log.d("ID", getId());

                //http通信処理→resultでやる？
//                AsyncGetJSONObject json = new AsyncGetJSONObject();
//                json.execute("http://swapi.co/api/people/");  //SW API
//
//                AsyncGetJSONObject json2 = new AsyncGetJSONObject();
//                json2.execute("http://27.120.120.174/StarWars/Fortune.php?birthday="+birthYear+"/"+birthMonth+"/"+birthDate);  //占い API


                setNotification();

                Intent intent = new Intent(getApplication(), ResultActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    protected ArrayList<Integer> calcNumberOfUserAnswer(int max, int min, String ans) {

        Integer result = 0;

        //回答がA→最大値からマイナス差分/2
        //回答がB→最小値にプラス差分/2
        if (ans.equals("A")) {
            max = max - (max - min) / 2;
        } else {
            min = min + (max - min) / 2;
        }

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(max);
        arrayList.add(min);

        Integer difValue = max - min;


        //最大値と最小値の差分が1→結果取得
        if (difValue == 1 && ans.equals("A")) {
            result = min;
        } else if (difValue == 1 && ans.equals("B")) {
            result = max;
        }

        arrayList.add(result);

        return arrayList;
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

