package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class ThemeActivity extends AppMenuActivity {

    Globals globals;
    Intent beforeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);


        globals=(Globals)this.getApplication();

        String[] strings = {"デフォルト", "ブラック", "イエロー"};

        ListView listView = (ListView) findViewById(R.id.list_theme);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);

        listView.setAdapter(adapter);

        // アイテムクリック時ののイベントを追加
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view, int pos, long id) {

                // 選択アイテムを取得
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(pos);

                Log.d("LIST",item);

                String  itemId;

                switch (item){
                    case "ブラック":
                        itemId="2";
                        break;
                    case "イエロー":
                        itemId="3";
                        break;
                    default:
                        itemId="1";
                }


                globals.UpdateTheme(getId(),itemId);
                finish();

            }
        });

    }
}
