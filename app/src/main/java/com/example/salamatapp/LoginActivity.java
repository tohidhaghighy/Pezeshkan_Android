package com.example.salamatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.salamatapp.CommonLayer.PersianCalender;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeActivity;
import com.example.salamatapp.NurseManagment.ManagmentNurseActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyActivity;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

public class LoginActivity extends AppCompatActivity {
    private SmoothProgressBar mProgressBar;
    private UserSharePrefrenceManager usermanager;
    private DoctorApiService _doctorapi;
    private String[] type_name={"بیمار","دکتر","کارشناس آزمایشگاه","داروخانه","پرستار"};
    private Integer[] type_id={3,1,2,4,5};
    private Spinner spn;
    private Integer usertype=1;
    private EditText txtnum,txtpass;
    private Button btnlogin,btnregister;
    PersianCalender pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usermanager=new UserSharePrefrenceManager(this);
        _doctorapi=new DoctorApiService(this);
        //check internet
        pc=new PersianCalender();
        final SaveUserItem useritem=usermanager.getUser();

        spn=(Spinner)findViewById(R.id.spineerlogin);
        ArrayAdapter aa = new ArrayAdapter(LoginActivity.this,android.R.layout.simple_spinner_item,type_name);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(aa);

        txtnum=(EditText)findViewById(R.id.txtmobileloginofapplication);
        txtpass=(EditText)findViewById(R.id.txtpassloginofapplication);
        btnlogin=(Button)findViewById(R.id.btnlogintoapplication);
        btnregister=(Button)findViewById(R.id.btnregisternewuser);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, TypePersonActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"لطفا منتظر بمانید",Toast.LENGTH_LONG).show();
                if (txtnum.getText().toString().length()>0 && txtpass.getText().toString().length()>0){
                    final String changeuserformat=pc.PersianToEnglish(txtnum.getText().toString());
                    _doctorapi.postcheckuser(changeuserformat, txtpass.getText().toString(), usertype, new DoctorApiService.OnDoctorLoginReceived() {
                        @Override
                        public void onReceived(String checklogin) {
                            if (checklogin.contains("true")){
                                Log.e("e",checklogin);
                                SaveUserItem user=new SaveUserItem();
                                user.setName("");
                                user.setPassword(txtpass.getText().toString());
                                user.setNumber(changeuserformat);
                                user.setIs_Active(false);
                                user.setType(usertype);
                                user.setFirstTime(0);
                                usermanager.saveUser(user);
                                final Intent i;
                                if (usertype==1){
                                    i= new Intent(LoginActivity.this, ManagmentDoctorActivity.class);
                                    startActivity(i);
                                }else if (usertype==2){
                                    i= new Intent(LoginActivity.this, ManagmentJudgeActivity.class);
                                    startActivity(i);
                                }else if (usertype==4){
                                    i= new Intent(LoginActivity.this, ManagmentPharmacyActivity.class);
                                    startActivity(i);
                                }else if (usertype==5){
                                    i= new Intent(LoginActivity.this, ManagmentNurseActivity.class);
                                    startActivity(i);
                                }else if (usertype==3){
                                    i= new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i);
                                }


                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"پسورد نادرست می باشد",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usertype=type_id[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mProgressBar=(SmoothProgressBar)findViewById(R.id.progressbarlogin);
        mProgressBar.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(LoginActivity.this)
                .color(R.color.colorAccent)
                .interpolator(new DecelerateInterpolator())
                .sectionsCount(8)
                .separatorLength(8)       //You should use Resources#getDimension
                .progressiveStartSpeed(2)
                .progressiveStopSpeed(3.4f)
                .reversed(true)
                .mirrorMode(true)
                .progressiveStart(true)
                .build());

        mProgressBar.progressiveStart();



        //SaveUserItem uu=new SaveUserItem();
        //uu.setType(0);
        //uu.setIs_Active(false);
        //uu.setNumber("");
        //uu.setPassword("");
        //uu.setFirstTime(0);
        //usermanager.saveUser(uu);
        //if (useritem.isIs_Active()==false && useritem.getNumber().length()==0)
        //{
        //    Intent i = new Intent(LoginActivity.this, TypePersonActivity.class);
          //  startActivity(i);
         //   finish();
        //}
        if(useritem.isIs_Active()==false && !useritem.getNumber().equals("")){
            Intent i = new Intent(LoginActivity.this, SmsResiveActivity.class);
            startActivity(i);
            finish();
        }else if(useritem.isIs_Active()){

            _doctorapi.postcheckuser(useritem.getNumber(), useritem.getPassword(), useritem.getType(), new DoctorApiService.OnDoctorLoginReceived() {
                @Override
                public void onReceived(String checklogin) {
                    Log.e("check",useritem.getType().toString());
                    Integer ins=useritem.getType();
                    final Intent i;
                    if (ins==1){
                        i= new Intent(LoginActivity.this, ManagmentDoctorActivity.class);
                        startActivity(i);
                    }else if (ins==2){
                        i= new Intent(LoginActivity.this, ManagmentJudgeActivity.class);
                        startActivity(i);
                    }else if (ins==4){
                        i= new Intent(LoginActivity.this, ManagmentPharmacyActivity.class);
                        startActivity(i);
                    }else if (ins==5){
                        i= new Intent(LoginActivity.this, ManagmentNurseActivity.class);
                        startActivity(i);
                    }else if (ins==3){
                        i= new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }


                    finish();

                }
            });
        }
        mProgressBar.progressiveStop();
    }
}
