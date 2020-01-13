package com.example.salamatapp.DoctorManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessProtestActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.ChangePasswordApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentDoctorSetPassActivity extends AppCompatActivity {

    EditText txtoldpass,txtnewpass,txtrenewpass;
    Button btnchangepass;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    ChangePasswordApiService changepass;
    Integer type=0;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_set_pass);

        txtoldpass=(EditText)findViewById(R.id.txtoldpassset);
        txtnewpass=(EditText)findViewById(R.id.txtnewpass);
        txtrenewpass=(EditText)findViewById(R.id.txtrenewpass);
        btnchangepass=(Button)findViewById(R.id.btnchangepass);
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

        changepass=new ChangePasswordApiService(this);


        Bundle extras = getIntent().getExtras();


        if(extras == null) {
            type= 0;
        } else {
            type= extras.getInt("type");
        }

        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (txtnewpass.getText().toString().length()>0 && txtoldpass.getText().toString().length()>0 && txtrenewpass.getText().toString().length()>0){
                        if (txtnewpass.getText().toString().equals(txtrenewpass.getText().toString())){

                            changepass.changepassword(useritem.getNumber().toString(), txtoldpass.getText().toString(), type, txtnewpass.getText().toString(), new ChangePasswordApiService.OnChangePassReceived() {
                                @Override
                                public void onReceived(String response) {
                                    if (response.contains("true")){
                                        Toast.makeText(ManagmentDoctorSetPassActivity.this, "با موفقیت تغییر کرد",
                                                Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(ManagmentDoctorSetPassActivity.this, "اطلاعات وارده صحیح نمیباشد",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(ManagmentDoctorSetPassActivity.this, "مقادیر پسورد و تکرار پسورد برابر نیست",
                                    Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(ManagmentDoctorSetPassActivity.this, "مقادیر نباید خالی باشد",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}
