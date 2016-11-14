package com.example.garai.starwars;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class FortuneActivity extends AppMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_fortune);

        changeBackGround(layout);

        setFortuneResult();


    }


    protected void setFortuneResult() {


        AsyncGetSWAPIResult swapi = new AsyncGetSWAPIResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

            public void postExecute(JSONObject result) {
                try {
                    Log.d("JSON", String.valueOf(result));


                    String charId = (String) result.get("character_id");
                    String charName = (String) result.get("user_type");
                    String birthday = (String) result.get("character_birthday");

                    Log.d("API", charId);
                    Log.d("API", charName);
                    Log.d("API", birthday);

                    AsyncGetFortuneResult json = new AsyncGetFortuneResult(new AsyncGetSWAPIResult.AsyncTaskCallback() {

                        public void postExecute(JSONObject result) {
                            try {
                                Log.d("JSON", result.toString(4));

                                String fortune = (String) result.get("content");
                                int moneyLevel = (int) result.get("money");
                                int jobLevel = (int) result.get("job");
                                int loveLevel = (int) result.get("love");
                                int totalLevel = (int) result.get("total");

                                TextView textView = (TextView) findViewById(R.id.text_fortune);
                                RatingBar moneyRating = (RatingBar) findViewById(R.id.rating_money);
                                RatingBar jobRating = (RatingBar) findViewById(R.id.rating_job);
                                RatingBar loveRating = (RatingBar) findViewById(R.id.rating_love);
                                RatingBar totalRating = (RatingBar) findViewById(R.id.rating_total);

                                moneyRating.setMax(5);
                                jobRating.setMax(5);
                                loveRating.setMax(5);
                                totalRating.setMax(5);

                                textView.setText(fortune);
                                moneyRating.setRating(moneyLevel);
                                jobRating.setRating(jobLevel);
                                loveRating.setRating(loveLevel);
                                totalRating.setRating(totalLevel);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                    json.execute("http://27.120.120.174/StarWars/Fortune.php?birthday="+birthday);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //Index.phpに値送る
        swapi.execute("http://27.120.120.174/StarWars/Index.php?uuid=" + getId());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_fortune);
        changeBackGround(layout);

    }

}
