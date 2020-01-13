package com.example.salamatapp.IllnessManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorZirmajmoeeActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.ZirmajmoeeApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;
import com.example.salamatapp.SmsResiveActivity;
import com.example.salamatapp.UploadDoctorFilesActivity;

public class Managment_Illness_ZirmajmoeeActivity extends AppCompatActivity {

    private Integer type;
    private EditText txtcode;
    private SaveUserItem useritem;
    Button btngo,btnaccept;
    ZirmajmoeeApiService zirmajmoeeapiservice;
    private UserSharePrefrenceManager usermanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment__illness__zirmajmoee);
        btngo=(Button)findViewById(R.id.btngowithoutcode);
        btnaccept=(Button)findViewById(R.id.btnacceptcode);
        txtcode=(EditText)findViewById(R.id.txtcode);

        usermanager=new UserSharePrefrenceManager(this);
        type=usermanager.getUser().getType();
        useritem=usermanager.getUser();


        zirmajmoeeapiservice=new ZirmajmoeeApiService(this);


        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Managment_Illness_ZirmajmoeeActivity.this, UploadDoctorFilesActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtcode.getText().length()>4){
                    zirmajmoeeapiservice.AddZirmajmoee(useritem.getNumber().toString(), useritem.getPassword().toString(), txtcode.getText().toString(), new ZirmajmoeeApiService.OnValueReceived() {
                        @Override
                        public void onReceived(String response) {

                            if (response.equals("true")){
                                Intent i = new Intent(Managment_Illness_ZirmajmoeeActivity.this, UploadDoctorFilesActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(Managment_Illness_ZirmajmoeeActivity.this, "کد وارده نادرست است",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(Managment_Illness_ZirmajmoeeActivity.this, "کد وارده نادرست است",
                            Toast.LENGTH_LONG).show();
                }



            }
        });


    }
}
