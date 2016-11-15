package com.example.garai.starwars;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.menu_logo);
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
            case R.id.menu_theme:
                Intent intent = new Intent(getApplication(), ThemeActivity.class);
                intent.putExtra("INTENT", getIntent());

                startActivityForResult(intent,0);

                break;
            case R.id.menu_root:
                Intent rootIntent = new Intent(getApplication(), SettingActivity.class);
                startActivity(rootIntent);
                break;
            case R.id.menu_version:
                Intent versionIntent = new Intent(getApplication(), VesionActivity.class);
                startActivity(versionIntent);
                break;
            case android.R.id.home:
                // app icon in action bar clicked;
               finish();
            default:
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

                    textView.setTypeface(Typeface.createFromAsset(getAssets(),"Meiryo.ttf"));
                    textView.setText(charName);
                    String html = "【診断結果】<br>";
                    CharSequence source = Html.fromHtml(html);
                    textProfile.setText(source+profile);
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

        swapi.execute("http://27.120.120.174/StarWars/Index.php?uuid=" + getId());

    }


    protected void getUserTheme(final RelativeLayout layout) {

        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {

                try {

                    Log.d("RESULT", String.valueOf(result));

                    String theme = (String) result.get("user_theme");
                    globals.themeId = theme;

                    changeBackGround(layout);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        swapi.execute("http://27.120.120.174/StarWars/UserTheme.php?uuid=" + getId());
    }


    protected void deleteUserInfo() {
        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {


            }
        });

        swapi.execute("http://27.120.120.174/StarWars/DeleteUserType.php?uuid=" + getId());

    }

    protected void changeBackGround(RelativeLayout layout) {

        switch (globals.themeId) {
            case "1":
                layout.setBackgroundResource(R.drawable.background);
                break;
            case "2":
                layout.setBackgroundResource(R.drawable.background_green);
                break;
            case "3":
                layout.setBackgroundResource(R.drawable.background_purple);
        }
    }

    /**
     *
     */
    protected void setNotificationTime(final int... ints) {

        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {
                try {
                    Log.d("AAAAA", String.valueOf(result));

                    String hour = (String) result.get("notification_hour");
                    String minute = (String) result.get("notification_minute");

                    Log.d("TIME", hour);
                    Log.d("TIME", minute);

                    setNotification(hour, minute);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        swapi.execute("http://27.120.120.174/StarWars/Time.php?uuid=" + getId() + "&hour=" + ints[0] + "&minute=" + ints[1]);


    }

    /**
     * ローカルプッシュ通知をセットする
     */
    protected void setNotification(String... strings) {


        java.util.Calendar calendar = java.util.Calendar.getInstance();

        int year = calendar.get(java.util.Calendar.YEAR);         //年を取得
        int month = calendar.get(java.util.Calendar.MONTH);       //月を取得
        int date = calendar.get(java.util.Calendar.DATE);         //日を取得


        // 初回実行時間設定（過去の時間設定の場合即実行）
        calendar.set(java.util.Calendar.YEAR, year);
        calendar.set(java.util.Calendar.MONTH, month);
        calendar.set(java.util.Calendar.DATE, date + 1);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, Integer.parseInt(strings[0]));
        calendar.set(java.util.Calendar.MINUTE, Integer.parseInt(strings[1]));


        //処理の実行感覚
        long interval = 60 * 60 * 12 * 1000;

        Intent intent = new Intent(getApplicationContext(), Notifier.class);
        intent.putExtra("intentId", 2);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        // アラームをセットする
        AlarmManager am = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        //am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);　//単発処理
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pending); //繰り返し処理

        //確認用トースト（後で消去）
        Toast.makeText(getApplicationContext(), "通知を設定しました", Toast.LENGTH_SHORT).show();
    }


}



