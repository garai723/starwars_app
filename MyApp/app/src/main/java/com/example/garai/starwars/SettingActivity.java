package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sendSettingInfo();

        back();
    }


    /**
     * 設定ボタン押された時
     */
    protected void sendSettingInfo() {
        final Button setButton = (Button) findViewById(R.id.button_setting);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO サーバーへ時刻設定送る

                //確認用トースト
                Toast.makeText(getApplicationContext(), "通知時刻を変更しました", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }


    /**
     * 戻るボタン押された時
     */
    protected void back() {
        final Button setButton = (Button) findViewById(R.id.button_return);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
