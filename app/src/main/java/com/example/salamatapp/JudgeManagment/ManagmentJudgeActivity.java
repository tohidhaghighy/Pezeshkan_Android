package com.example.salamatapp.JudgeManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorActivity;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorSetPassActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyInfoActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentJudgeActivity extends AppCompatActivity {

    Button llbtnvisittime,llbtnown,llbtnmain,llchangepass;
    Intent i;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private TextView txtname,txtnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_judge);
        llbtnvisittime=(Button) findViewById(R.id.btnvisittimejudgemanagment);
        llbtnown=(Button)findViewById(R.id.btnownjudgemanagment);
        llbtnmain=(Button)findViewById(R.id.btnmainjudgemanagment);
        llchangepass=(Button)findViewById(R.id.btnchangepassjudge);


        llchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentJudgeActivity.this, ManagmentDoctorSetPassActivity.class);
                i.putExtra("type",2);
                startActivity(i);
            }
        });



        llbtnvisittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentJudgeActivity.this, ManagmentJudgeVisitActivity.class);
                startActivity(i);
            }
        });

        llbtnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentJudgeActivity.this, ManagmentJudgeInfoActivity.class);
                startActivity(i);
            }
        });

        llbtnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentJudgeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
