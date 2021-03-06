package com.example.garai.starwars;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.id.empty;

public class SecondActivity extends AppMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_second);

        changeBackGround(layout);

        moveResult();

    }


    /**
     * resultボタンが押されたとき
     */
    protected void moveResult() {


        final Button button = (Button) findViewById(R.id.button_result);
        button.setTypeface(Typeface.createFromAsset(getAssets(),"Meiryo.ttf"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SecondActivity.this,R.style.MyDialog);
                alertDialog.setTitle("入力した内容で診断しますか？");
                alertDialog.setMessage("入力した内容で診断しますか？");
                alertDialog.setPositiveButton("診断する", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent1 = getIntent();
                        int deleteFlg=intent1.getIntExtra("delete_flg",0);

                        //再診断時過去データ消去
                        if(deleteFlg==1){
                            deleteUserInfo();
                        }


                        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker2);

                        RadioGroup radioBlood = (RadioGroup) findViewById(R.id.radio_blood);
                        RadioGroup radioWeapon = (RadioGroup) findViewById(R.id.radio_weapon);
                        RadioGroup radioPartner = (RadioGroup) findViewById(R.id.radio_partner);
                        RadioGroup radioHair = (RadioGroup) findViewById(R.id.radio_hair);

                        int bloodId = radioBlood.getCheckedRadioButtonId();
                        int weaponId = radioWeapon.getCheckedRadioButtonId();
                        int partnerId = radioPartner.getCheckedRadioButtonId();
                        int hairColorId = radioHair.getCheckedRadioButtonId();


                        RadioButton bloodButton = (RadioButton) findViewById(bloodId);
                        RadioButton weaponButton = (RadioButton) findViewById(weaponId);
                        RadioButton partnerButton = (RadioButton) findViewById(partnerId);
                        RadioButton hairButton = (RadioButton) findViewById(hairColorId);


                        String blood = (String) bloodButton.getText();
                        String weapon = (String) weaponButton.getText();
                        String partner = (String) partnerButton.getText();
                        String hairColor = (String) hairButton.getText();


                        int birthYear = datePicker.getYear();
                        int birthMonth = datePicker.getMonth();
                        int birthDate = datePicker.getDayOfMonth();

                        Log.d("VALUE", blood);
                        Log.d("VALUE", weapon);
                        Log.d("VALUE", partner);
                        Log.d("VALUE", hairColor);
                        Log.d("BIRTH", Integer.toString(birthYear) + "年" + Integer.toString(birthMonth) + "月" + Integer.toString(birthDate) + "日");
                        Log.d("ID", getId());

                        setNotificationTime(9,0);
                        Intent intent = new Intent(getApplication(), ResultActivity.class);
                        intent.putExtra("BLOOD", blood);
                        intent.putExtra("WEAPON", weapon);
                        intent.putExtra("PARTNER", partner);
                        intent.putExtra("BIRTHDAY", birthYear + "-" + birthMonth + "-" + birthDate);

                        startActivity(intent);
                        finish();
                    }
                });

                alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.create().show();

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_second);
        changeBackGround(layout);

    }

}

