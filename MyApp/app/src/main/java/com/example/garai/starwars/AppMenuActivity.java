package com.example.garai.starwars;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by garai on 2016/11/05.
 */

public class AppMenuActivity extends AppCompatActivity {

    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globals = (Globals) this.getApplication();


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_menu);
    }


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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        switch (id) {
            case R.id.action_back:
                finish();
                break;
            case R.id.menu_theme:
                Intent intent = new Intent(getApplication(), ThemeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_root:

                //TODO　管理画面

                Log.d("ID", getId() + "&" + R.string.root);

                if (getId().equals("261c183004b3a8c7")) {
                    Intent rootIntent = new Intent(getApplication(), SettingActivity.class);
                    startActivity(rootIntent);
                    break;
                }
        }


        return super.onOptionsItemSelected(item);
    }


    protected void setCharInfo(String themeId, String... strings) {

        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {

                try {
                    Log.d("JSON", String.valueOf(result));


                    String charId = (String) result.get("character_id");
                    String charName = (String) result.get("user_type");
                    String profile = (String) result.get(("character_profile"));
                    String birthday = (String) result.get("character_birthday");

                    Log.d("API", charId);
                    Log.d("API", charName);
                    Log.d("API", birthday);

                    //リソースID取得
                    int res = getResources().getIdentifier("character_" + charId, "drawable", getPackageName());

                    Log.d("RES", String.valueOf(res));

                    final ImageView imageView = (ImageView) findViewById(R.id.image_character);
                    final TextView textView = (TextView) findViewById(R.id.text_name);
                    final TextView textProfile = (TextView) findViewById(R.id.text_profile);

                    textView.setText(charName);
                    textProfile.setText(profile);
                    imageView.setImageResource(res);

                    Log.d("SWAPI_NAME", String.valueOf(result));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //Index.phpに値送る
        swapi.execute("http://27.120.120.174/StarWars/Index.php?os_type=Android&uuid=" + strings[0] + "&character_blood=" + strings[1] + "+&character_weapon=" + strings[2] + "&user_birthday=" + strings[3] + "&character_partner=" + strings[4] + "&user_theme=" + themeId);

    }


    protected void checkUserInfo() {
        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {

                try {
                    String id = (String) result.get("uuid");

                    if (id.equals("error")) {
                        Intent intent = new Intent(getApplication(), SecondActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplication(), TopActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        String uuid = getId();

        swapi.execute("http://27.120.120.174/StarWars/Index.php?uuid=" + getId());

    }


    protected void getUserTheme(final RelativeLayout layout) {
        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {

                try {
                    String theme = (String) result.get("user_theme");
                    globals.themeId = theme;

                    changeBackGround(layout);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        swapi.execute("http://27.120.120.174/StarWars/Index.php?uuid=" + getId());

    }

    protected void changeBackGround(RelativeLayout layout) {

        switch (globals.themeId) {
            case "1":
                layout.setBackgroundResource(R.drawable.background);
                break;
            case "2":
                layout.setBackgroundResource(android.R.color.background_dark);
                break;
            case "3":
                layout.setBackgroundResource(android.R.color.holo_orange_light);
        }
    }
}



