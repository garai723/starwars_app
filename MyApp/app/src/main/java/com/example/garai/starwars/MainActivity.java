package com.example.garai.starwars;

        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import java.util.Calendar;

        import static com.example.garai.starwars.R.id.button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveInputPage();

        sendNotification();
    }


    /**
     * startボタンが押されたとき
     */
    protected void moveInputPage() {
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), NotifyActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void sendNotification() {

        Calendar cal = Calendar.getInstance();       //カレンダーを取得

        int iYear = cal.get(Calendar.YEAR);         //年を取得
        int iMonth = cal.get(Calendar.MONTH);       //月を取得
        int iDate = cal.get(Calendar.DATE);         //日を取得
        int iHour = cal.get(Calendar.HOUR);         //時を取得
        int iMinute = cal.get(Calendar.MINUTE);    //分を取得


        String strDay = iYear + "年" + iMonth + "月" + iDate + "日";     //日付を表示形式で設定
        String strTime = iHour + "時" + iMinute + "分"; //時刻を表示形式で設

        String strDateTime = strDay + strTime;

        Log.d("TIME",strDateTime);

        if (strDateTime == "2016年10月3日7時23分") {

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("key", R.string.app_name);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);


            NotificationManager nMgr = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);

            Notification notify = new Notification.Builder(this)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.top_bg)
                    .setTicker("ステータスバーに表示する文言")
                    .setContentTitle("This is Time Notify")
                    .setContentText("Notificationの説明")
                    .setContentIntent(contentIntent)
                    .getNotification();

            nMgr.notify(R.string.app_name, notify);

        }

    }
}
