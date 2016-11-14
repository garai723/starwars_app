package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class TopActivity extends AppMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_top);

        changeBackGround(layout);

        setCharInfo(null, getId(), null, null, null, null);

        moveToFortune();

        restartFortune();

    }


    /**
     * 運勢を見るボタンが押された時
     */
    protected void moveToFortune() {
        Button button = (Button) findViewById(R.id.button_top);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), FortuneActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 再診断ボタンが押されたとき
     */
    protected void restartFortune() {
        Button button = (Button) findViewById(R.id.button_restart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteUserInfo();

            }
        });
    }
}
