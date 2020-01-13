package com.example.salamatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TypePersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_person);
        Button btnillness=(Button)findViewById(R.id.btnillnessinsert);
        btnillness.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(TypePersonActivity.this, RegisterMobilActivity.class);
                i.putExtra("type",3);
                startActivity(i);
                finish();
            }
        });
        Button btndoctor=(Button)findViewById(R.id.btndoctorinsert);
        btndoctor.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(TypePersonActivity.this, RegisterMobilActivity.class);
                i.putExtra("type",1);
                startActivity(i);
                finish();
            }
        });

        Button btnjudge=(Button)findViewById(R.id.btnjudgeinsert);
        btnjudge.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(TypePersonActivity.this, RegisterMobilActivity.class);
                i.putExtra("type",2);
                startActivity(i);
                finish();
            }
        });

        Button btnpharmacy=(Button)findViewById(R.id.btnpharmacyinsert);
        btnpharmacy.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(TypePersonActivity.this, RegisterMobilActivity.class);
                i.putExtra("type",4);
                startActivity(i);
                finish();
            }
        });
        Button btnnears=(Button)findViewById(R.id.btnnirseinsert);
        btnnears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TypePersonActivity.this, RegisterMobilActivity.class);
                i.putExtra("type",5);
                startActivity(i);
                finish();
            }
        });

    }
}
