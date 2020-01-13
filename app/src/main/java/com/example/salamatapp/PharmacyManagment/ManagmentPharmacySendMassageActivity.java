package com.example.salamatapp.PharmacyManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.RegisterMobilActivity;
import com.example.salamatapp.ServiceLayer.PharmacyApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class ManagmentPharmacySendMassageActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private EditText edittextmassage;
    private Button btnaddmassage;
    private Integer visitid=0;
    private PharmacyApiService _pharmacyapi;
    private Intent i;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_pharmacy_send_massage);
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


        _pharmacyapi=new PharmacyApiService(this);

        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            visitid= 0;
        } else {
            visitid= extras.getInt("visitid");
        }


        edittextmassage=(EditText)findViewById(R.id.txtmassageofpharmacymanagment);
        btnaddmassage=(Button)findViewById(R.id.btnaddmassageofpharmacymanagment);

        btnaddmassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittextmassage.getText().toString().length()>0){

                    Log.e("error",edittextmassage.getText().toString());
                    _pharmacyapi.postaddmassagepharmacy(useritem.getNumber().toString(), edittextmassage.getText().toString(), visitid, new PharmacyApiService.OnPharmacyStringReceived() {
                        @Override
                        public void onReceived(String checkIsok) {
                            Toast.makeText(ManagmentPharmacySendMassageActivity.this, "با موفقیت ارسال شد ",
                                    Toast.LENGTH_LONG).show();
                            i = new Intent(ManagmentPharmacySendMassageActivity.this, ManagmentPharmacyVisitActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
                }else {
                    Toast.makeText(ManagmentPharmacySendMassageActivity.this, "مقادیر نباید خالی باشد",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
