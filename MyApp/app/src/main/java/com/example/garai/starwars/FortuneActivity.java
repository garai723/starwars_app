package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FortuneActivity extends AppMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        //TODO サーバー通信&キャラ運勢表示

        setFortuneResult();

        back();

    }

    protected void back() {

        Button button = (Button) findViewById(R.id.button_fortune);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TopActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

    protected void setFortuneResult(){

        TextView textView = (TextView)findViewById(R.id.text_fortune);

        AsyncGetFortuneResult json = new AsyncGetFortuneResult(textView);
        json.execute("http://27.120.120.174/StarWars/Fortune.php");


    }

}
