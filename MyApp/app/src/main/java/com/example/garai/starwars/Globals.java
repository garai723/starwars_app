package com.example.garai.starwars;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by garai on 2016/11/12.
 */

public class Globals extends Application {

    String themeId = "1";

    public int UpdateTheme(String uuid, String theme  ) {

        themeId = theme;

        AsyncGetSWAPIResult asyncGetSWAPIResult = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {
            @Override
            public void postExecute(JSONObject result) {

                try {
                    Log.d("THEME", String.valueOf(result.get("user_theme")));

                    themeId= (String) result.get("user_theme");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "テーマを変更しました", Toast.LENGTH_SHORT).show();
            }
        });

        asyncGetSWAPIResult.execute("http://27.120.120.174/StarWars/UserTheme.php?uuid=" + uuid + "&user_theme=" + themeId);

        return 1;
    }



}
