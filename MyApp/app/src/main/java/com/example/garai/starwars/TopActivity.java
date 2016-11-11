package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopActivity extends AppMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        //TODO サーバー通信&キャラ絵表示

        setCharInfo(getId(),null,null,null,null);

        moveToFortune();

    }

    protected void moveToFortune(){
        Button button = (Button)findViewById(R.id.button_top);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FortuneActivity.class);
                startActivity(intent);
            }
        });
    }
}
