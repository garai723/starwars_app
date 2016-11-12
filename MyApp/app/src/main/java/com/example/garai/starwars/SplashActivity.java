package com.example.garai.starwars;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Handler;
        import android.provider.Settings;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Window;
        import android.widget.ImageView;
        import android.widget.TextView;

        import org.json.JSONException;
        import org.json.JSONObject;

public class SplashActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(), 2000);
    }

    class splashHandler implements Runnable {
        public void run() {

            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            SplashActivity.this.
                    finish();


        }
    }
}