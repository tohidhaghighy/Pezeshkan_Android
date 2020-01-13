package com.example.salamatapp.DoctorManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessActivity;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessInfoActivity;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentDoctorActivity extends AppCompatActivity {

    Button llbtnvisittime,llbtndaru,llbtnquestion,llbtnown,llbtnmain,llbtnvisitdoctor,llbtnzirmajmoee,llchangepass;
    private Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor);
        llbtnvisittime=(Button)findViewById(R.id.btnvisittimedoctorManagment);
        llbtndaru=(Button)findViewById(R.id.btndarudoctorManagment);
        llbtnquestion=(Button)findViewById(R.id.btnquestionsdoctorManagment);
        llbtnown=(Button)findViewById(R.id.btnowndoctorManagment);
        llbtnmain=(Button)findViewById(R.id.btnmaindoctorManagment);
        llbtnzirmajmoee=(Button)findViewById(R.id.btnzirmajmoeeManagment);
        llbtnvisitdoctor=(Button)findViewById(R.id.btnvisitManagment);
        llchangepass=(Button)findViewById(R.id.btnchangepassdoctor);

        llchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, ManagmentDoctorSetPassActivity.class);
                i.putExtra("type",1);
                startActivity(i);
            }
        });

        llbtnvisitdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, ManagmentDoctorVisitDoctorActivity.class);
                startActivity(i);
            }
        });

        llbtnvisittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, ManagmentDoctorVisitListActivity.class);
                startActivity(i);
            }
        });

        llbtndaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, ManagmentDoctorDragActivity.class);
                startActivity(i);
            }
        });

        llbtnquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, ManagmentDoctorQuestion2Activity.class);
                startActivity(i);
            }
        });

        llbtnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, ManagmentDoctorInfoActivity.class);
                startActivity(i);
            }
        });

        llbtnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        llbtnzirmajmoee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorActivity.this, ManagmentDoctorZirmajmoeeActivity.class);
                startActivity(i);
            }
        });
    }
}
