package com.example.salamatapp.IllnessManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessJudge_Adaptor;
import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessPharmacy_Adaptor;
import com.example.salamatapp.DomainLayer.Illness.IllnessPharmacyList;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.IllnessApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ManagmentIllnessPharmacyListActivity extends AppCompatActivity {


    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private IllnessApiService _illnessservice;
    private RecyclerView pharmacyrecycle;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_illness_pharmacy_list);
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

        _illnessservice=new IllnessApiService(this);
        pharmacyrecycle=(RecyclerView)findViewById(R.id.illnessmanagmentpharmacylistrecycle);
        _illnessservice.getIllnessPharmacyList(useritem.getNumber().toString(), new IllnessApiService.OnillnessPharmacyListReceived() {
            @Override
            public void onReceived(List<IllnessPharmacyList> illnesspharmacylist) {
                IllnessPharmacy_Adaptor pa=new IllnessPharmacy_Adaptor(ManagmentIllnessPharmacyListActivity.this, illnesspharmacylist);
                pharmacyrecycle.setLayoutManager(new LinearLayoutManager(ManagmentIllnessPharmacyListActivity.this,LinearLayoutManager.VERTICAL,false));
                pharmacyrecycle.setAdapter(pa);
            }
        });

    }
}
