package com.example.salamatapp.IllnessManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorSetPassActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.NoskheManagmentPharmacyActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.ChangePasswordApiService;
import com.example.salamatapp.ServiceLayer.ViolationApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentIllnessProtestActivity extends AppCompatActivity {

    EditText txtcode,txtdescription;
    Button btnaddprotest,btnshowlistprotest;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    ViolationApiService violationservice;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_illness_protest);
        txtcode=(EditText)findViewById(R.id.txtprotestcode);
        txtdescription=(EditText)findViewById(R.id.txtprotestdes);
        btnaddprotest=(Button)findViewById(R.id.btnprotestitem);
        btnshowlistprotest=(Button)findViewById(R.id.btnlistprotestitem);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();
        violationservice=new ViolationApiService(this);


        btnaddprotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtcode.getText().toString().length()>0 && txtdescription.getText().toString().length()>0){

                    violationservice.AddViolation(useritem.getNumber().toString(), txtcode.getText().toString(), txtdescription.getText().toString(), new ChangePasswordApiService.OnChangePassReceived() {
                        @Override
                        public void onReceived(String response) {
                            response=response.toString();
                            if (response.contains("True")){
                                Toast.makeText(ManagmentIllnessProtestActivity.this, "با موفقیت درج شد",
                                        Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(ManagmentIllnessProtestActivity.this, "اطلاعات وارده صحیح نمیباشد مشکل ارتباط با سرور",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(ManagmentIllnessProtestActivity.this, "مقدار نباید خالی باشد",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        btnshowlistprotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagmentIllnessProtestActivity.this, ListOfProtestActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
