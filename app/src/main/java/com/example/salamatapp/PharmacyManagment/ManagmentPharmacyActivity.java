package com.example.salamatapp.PharmacyManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorActivity;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorSetPassActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeActivity;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeInfoActivity;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentPharmacyActivity extends AppCompatActivity {

    private Button llbtnnoskhe,llbtnown,llbtnmain,llchangepass;
    private TextView txtname,txtnumber;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_pharmacy);
        llbtnnoskhe=(Button) findViewById(R.id.btnnoskhepharmacymanagment);
        llbtnown=(Button) findViewById(R.id.btnownpharmacymanagment);
        llbtnmain=(Button) findViewById(R.id.btnmainpharmacymanagment);
        llchangepass=(Button)findViewById(R.id.btnchangepasspharmacy);

        llchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentPharmacyActivity.this, ManagmentDoctorSetPassActivity.class);
                i.putExtra("type",4);
                startActivity(i);
            }
        });

        llbtnnoskhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentPharmacyActivity.this, ManagmentPharmacyVisitActivity.class);
                startActivity(i);
            }
        });

        llbtnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentPharmacyActivity.this, ManagmentPharmacyInfoActivity.class);
                startActivity(i);
            }
        });

        llbtnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentPharmacyActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
