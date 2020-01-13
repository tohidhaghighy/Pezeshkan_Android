package com.example.salamatapp.PharmacyManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessJudge_Adaptor;
import com.example.salamatapp.AdaptorLayer.JudgeManagment.JudgeVisit_Adaptor;
import com.example.salamatapp.AdaptorLayer.PharmacyManagment.PharmacyVisit_Adaptor;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyVisitList;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessJudgeListActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.PharmacyApiService;

import java.util.List;

public class ManagmentPharmacyVisitActivity extends AppCompatActivity {

    private RecyclerView rv;
    private PharmacyApiService pharmacyApiService;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_pharmacy_visit);
        rv=(RecyclerView)findViewById(R.id.pharmacymanagmentvistlistrecycle);
        pharmacyApiService=new PharmacyApiService(this);
        pharmacyApiService.getPharmacyVisitList(new PharmacyApiService.OnpharmacyVisitListReceived() {
            @Override
            public void onReceived(List<PharmacyVisitList> pharmacyvisitlist) {
                PharmacyVisit_Adaptor pa=new PharmacyVisit_Adaptor(ManagmentPharmacyVisitActivity.this, pharmacyvisitlist);
                rv.setLayoutManager(new LinearLayoutManager(ManagmentPharmacyVisitActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(pa);
            }
        });

        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
