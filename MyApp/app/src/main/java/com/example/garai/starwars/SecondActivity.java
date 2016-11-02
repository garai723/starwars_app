package com.example.garai.starwars;

import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedInputStream;
        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

public class SecondActivity extends AppCompatActivity {

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
        final Button button = (Button) findViewById(R.id.button_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editWeight = (EditText) findViewById(R.id.editWeight);
                String weight = editWeight.getText().toString();

                Log.d("VALUE", weight);

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                int index = spinner.getSelectedItemPosition();
                String hairColor = (String) spinner.getItemAtPosition(index);

                Log.d("VALUE", hairColor);

                AsyncGetJSONObject json = new AsyncGetJSONObject();
                json.execute("http://swapi.co/api/people/1/");

                Intent intent = new Intent(getApplication(), ResultActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

