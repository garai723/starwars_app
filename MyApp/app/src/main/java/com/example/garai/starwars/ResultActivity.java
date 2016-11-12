package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends AppMenuActivity {

    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        globals=(Globals)this.getApplication();

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_result);

        changeBackGround(layout);

        //値受け取り
        Intent intent=getIntent();
        String bloodId=intent.getStringExtra("BLOOD");
        String weaponId=intent.getStringExtra("WEAPON");
        String partnerId=intent.getStringExtra("PARTNER");
        String birthday=intent.getStringExtra("BIRTHDAY");

        //UUID取得
        String uuid=getId();

        Log.d("GET", String.valueOf(bloodId));
        Log.d("GET", String.valueOf(weaponId));
        Log.d("GET", String.valueOf(partnerId));
        Log.d("GET",birthday);
        Log.d("UUID",uuid);

        setCharInfo(globals.themeId,uuid,bloodId,weaponId,birthday,partnerId);

        moveToFortune();

    }


    /**
     * 占うボタンがpされたとき
     */
    protected void moveToFortune() {
        Button returnButton = (Button) findViewById(R.id.button_return);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), FortuneActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

}