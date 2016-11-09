package com.example.garai.starwars;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
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


    protected void setCharInfo(int blood, int weapon, int partner, String uuid, String birthday) {



        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {
                try {
                    Log.d("JSON", String.valueOf(result));

                    //TODO キー名変


                    String charId = (String) result.get("character_id");
                    String charName = (String) result.get("user_type");
                    String birthday = (String) result.get("character_birthday");

                    Log.d("API", charId);
                    Log.d("API", charName);
                    Log.d("API", birthday);

                    //リソースID取得
                    int res = getResources().getIdentifier("character_"+charId, "drawable", getPackageName());

                    Log.d("RES", String.valueOf(res));

                    final ImageView imageView = (ImageView) findViewById(R.id.image_character);
                    final TextView textView = (TextView) findViewById(R.id.text_name);

                    textView.setText(charName);
                    imageView.setImageResource(res);

                    Log.d("SWAPI_NAME", String.valueOf(result));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //Index.phpに値送る
        swapi.execute("http://27.120.120.174/StarWars/Index.php?os_type=Android&uuid=" + uuid + "&character_blood=" + blood + "+&character_weapon=" + weapon + "&user_birthday=" + birthday + "&character_partner=" + partner);

    }

//    protected void setId() {
//        id = Settings.Secure.getString(this.getContentResolver(), Settings.System.ANDROID_ID);
//    }
//
//    protected String getId(){
//        return id;
//    }
}



