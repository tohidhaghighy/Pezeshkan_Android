package com.example.salamatapp.NurseManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeActivity;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeInfoActivity;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeVisitActivity;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentNurseActivity extends AppCompatActivity {

    Button llbtnvisittime,llbtnown,llbtnmain;
    Intent i;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private TextView txtname,txtnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_nurse);

        llbtnvisittime=(Button) findViewById(R.id.btnvisittimejudgemanagment);
        llbtnown=(Button)findViewById(R.id.btnownjudgemanagment);
        llbtnmain=(Button)findViewById(R.id.btnmainjudgemanagment);


        llbtnvisittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManagmentNurseActivity.this,"در حال تکمیل",Toast.LENGTH_SHORT).show();
            }
        });

        llbtnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManagmentNurseActivity.this,"در حال تکمیل",Toast.LENGTH_SHORT).show();
            }
        });

        llbtnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentNurseActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
