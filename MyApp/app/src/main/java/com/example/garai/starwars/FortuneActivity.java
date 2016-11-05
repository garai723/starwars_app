package com.example.garai.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FortuneActivity extends AppMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

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
}
