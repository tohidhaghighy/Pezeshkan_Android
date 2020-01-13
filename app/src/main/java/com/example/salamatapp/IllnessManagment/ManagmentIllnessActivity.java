package com.example.salamatapp.IllnessManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorSetPassActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentIllnessActivity extends AppCompatActivity {

    Button llvisittime,llpharmacy,lljudge,llown,llmain,llchangepass,llsabteshekaiat;
    Intent i;
    private TextView txtname,txtnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_illness);


        llvisittime=(Button) findViewById(R.id.btnvisittimeIllnessManagment);
        llpharmacy=(Button)findViewById(R.id.btnpharmacyillnessManagment);
        lljudge=(Button)findViewById(R.id.btnjudgeIllnessManagment);
        llmain=(Button)findViewById(R.id.btnMainPageIllnessManagment);
        llown=(Button)findViewById(R.id.btnownpageIllnessManagment);
        llchangepass=(Button)findViewById(R.id.btnchangepassillness);
        llsabteshekaiat=(Button)findViewById(R.id.btnsetprotest);

        llsabteshekaiat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentIllnessActivity.this, ManagmentIllnessProtestActivity.class);
                startActivity(i);
            }
        });

        llvisittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentIllnessActivity.this, ManagmentIllnessVisitListActivity.class);
                startActivity(i);
            }
        });

        llpharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentIllnessActivity.this, ManagmentIllnessPharmacyListActivity.class);
                startActivity(i);
            }
        });

        lljudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentIllnessActivity.this, ManagmentIllnessJudgeListActivity.class);
                startActivity(i);
            }
        });

        llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentIllnessActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        llchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentIllnessActivity.this, ManagmentDoctorSetPassActivity.class);
                i.putExtra("type",3);
                startActivity(i);
            }
        });

        llown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentIllnessActivity.this, ManagmentIllnessInfoActivity.class);
                startActivity(i);
            }
        });
    }
}
