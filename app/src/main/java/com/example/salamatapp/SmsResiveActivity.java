package com.example.salamatapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.IllnessManagment.Managment_Illness_ZirmajmoeeActivity;
import com.example.salamatapp.NurseManagment.ManagmentNurseActivity;
import com.example.salamatapp.ServiceLayer.SmsApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class SmsResiveActivity extends AppCompatActivity {

   Button btnrepeatsend,btnacceptcode;
    private UserSharePrefrenceManager usermanager;
    private Integer type;
    private EditText txtcode;
    private SaveUserItem useritem;
    private SmsApiService smsservice;
    private ImageView btnback;
    Handler handler;
    private Runnable mRunnable;
    private TextView txttimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_resive);

        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        txttimer=(TextView)findViewById(R.id.txttimer);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        usermanager=new UserSharePrefrenceManager(this);
        type=usermanager.getUser().getType();
        useritem=usermanager.getUser();

        smsservice=new SmsApiService(this);
        smsservice.Sendactivesms(useritem.getNumber().toString(), useritem.getPassword().toString(), type+"", new SmsApiService.OnValueReceived() {
            @Override
            public void onReceived(String response) {
                Toast.makeText(SmsResiveActivity.this, "با موفقیت ارسال شد",
                        Toast.LENGTH_LONG).show();
            }
        });

        btnrepeatsend=(Button)findViewById(R.id.btnrepeatsendcode);
        btnacceptcode=(Button)findViewById(R.id.btnacceptcode);
        txtcode=(EditText)findViewById(R.id.txtcode);


        btnacceptcode.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                smsservice.Checksendsmscodeforactive(useritem.getNumber().toString(), useritem.getPassword().toString(), type+"", txtcode.getText().toString(), new SmsApiService.OnValueReceived() {
                    @Override
                    public void onReceived(String response) {
                        if (response.contains("true")){
                            Log.e("name",type+"");
                            if (type==1){
                                Intent i = new Intent(SmsResiveActivity.this, Managment_Illness_ZirmajmoeeActivity.class);
                                startActivity(i);
                                finish();
                            }else if(type==2){
                                Intent i = new Intent(SmsResiveActivity.this, UploadJudgeFilesActivity.class);
                                startActivity(i);
                                finish();
                            }else if(type==3){
                                Intent i = new Intent(SmsResiveActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }else if (type==4){
                                Intent i = new Intent(SmsResiveActivity.this, UploadPharmacyFilesActivity.class);
                                startActivity(i);
                                finish();
                            }else if(type==5){
                                Intent i = new Intent(SmsResiveActivity.this, ManagmentNurseActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                });

            }
        });

        btnrepeatsend.setVisibility(View.INVISIBLE);
        btnrepeatsend.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                smsservice.Sendactivesms(useritem.getNumber().toString(), useritem.getPassword().toString(), type+"", new SmsApiService.OnValueReceived() {
                    @Override
                    public void onReceived(String response) {
                        Toast.makeText(SmsResiveActivity.this, "با موفقیت ارسال شد",
                                Toast.LENGTH_LONG).show();
                    }
                });
                btnrepeatsend.setVisibility(View.INVISIBLE);
            }
        });


        mRunnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 20000);
                btnrepeatsend.setVisibility(View.VISIBLE);
            }
        };
        handler = new Handler();
        handler.post(mRunnable);
    }
}
