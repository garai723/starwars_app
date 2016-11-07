package com.example.garai.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by garai on 2016/11/05.
 */

public class AppMenuActivity extends AppCompatActivity {


    protected String getId() {
        String id = Settings.Secure.getString(this.getContentResolver(), Settings.System.ANDROID_ID);
        return id;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplication(), SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void setCharInfo() {
        ImageView imageView = (ImageView) findViewById(R.id.image_character);
        TextView textView = (TextView) findViewById(R.id.text_name);

        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(textView, imageView);
        swapi.execute("http://swapi.co/api/people/1/");

    }

//    protected void setId() {
//        id = Settings.Secure.getString(this.getContentResolver(), Settings.System.ANDROID_ID);
//    }
//
//    protected String getId(){
//        return id;
//    }
}



