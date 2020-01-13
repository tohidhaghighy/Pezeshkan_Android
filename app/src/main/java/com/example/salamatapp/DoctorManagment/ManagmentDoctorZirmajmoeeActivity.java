package com.example.salamatapp.DoctorManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorVisit_Adaptor;
import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorZirmajmoee_Adaptor;
import com.example.salamatapp.DomainLayer.Doctor.DoctorZir;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DataFakeGenarator;
import com.example.salamatapp.ServiceLayer.ZirmajmoeeApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ManagmentDoctorZirmajmoeeActivity extends AppCompatActivity {


    private Integer type;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    TextView txtcode;
    RecyclerView rvzirmajmoeelist;
    ZirmajmoeeApiService zirmajmoeeservice;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_zirmajmoee);
        txtcode=(TextView)findViewById(R.id.txtcode);
        rvzirmajmoeelist=(RecyclerView)findViewById(R.id.rvdoctorzirmajmoee);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        usermanager=new UserSharePrefrenceManager(this);
        type=usermanager.getUser().getType();
        useritem=usermanager.getUser();

        zirmajmoeeservice=new ZirmajmoeeApiService(this);
        zirmajmoeeservice.getZirmajmoee(useritem.getNumber().toString(), useritem.getPassword().toString(), new ZirmajmoeeApiService.OnZirmajmoeeValueReceived() {
            @Override
            public void onReceived(List<DoctorZir> doctorzirs) {
                DoctorZir dc=new DoctorZir();
                dc=doctorzirs.get(0);
                Log.e("es",dc.getName().toString());
                txtcode.setText(" کد معرف شما : "+dc.getName().toString());
                doctorzirs.remove(0);

                DoctorZirmajmoee_Adaptor pa=new DoctorZirmajmoee_Adaptor(ManagmentDoctorZirmajmoeeActivity.this, doctorzirs);
                rvzirmajmoeelist.setLayoutManager(new LinearLayoutManager(ManagmentDoctorZirmajmoeeActivity.this,LinearLayoutManager.VERTICAL,false));
                rvzirmajmoeelist.setAdapter(pa);
            }
        });



    }
}
