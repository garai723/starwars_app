package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Listの作成
        ArrayList<String> list = new ArrayList<String>();
        // Listにデータを入れる
        list.add("ねずみ");
        list.add("うし");
        list.add("とら");
        list.add("うさぎ");

        // Adapterの作成
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_setting, list);

        // ListViewにAdapterを関連付ける
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }

//        sendSettingInfo();
//
//        back();



    /**
     * 設定ボタン押された時
     */
//    protected void sendSettingInfo() {
//        final Button setButton = (Button) findViewById(R.id.button_setting);
//        setButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                EditText editTime = (EditText) findViewById(R.id.edit_time);
//                String time = editTime.getText().toString();
//
//
//                //TODO サーバーへ時刻設定送る
//
//                if(time.equals("")){
//                    Toast.makeText(getApplicationContext(), "未入力の項目があります", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                //確認用トースト
//                Toast.makeText(getApplicationContext(), "通知時刻を変更しました", Toast.LENGTH_SHORT).show();
//
//                finish();
//            }
//        });
//    }
//
//
//    /**
//     * 戻るボタン押された時
//     */
//    protected void back() {
//        final Button setButton = (Button) findViewById(R.id.button_return);
//        setButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }

}
