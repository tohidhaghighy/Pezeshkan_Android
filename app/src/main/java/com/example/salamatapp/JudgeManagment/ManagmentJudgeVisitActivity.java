package com.example.salamatapp.JudgeManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.salamatapp.AdaptorLayer.JudgeManagment.JudgeVisit_Adaptor;
import com.example.salamatapp.AdaptorLayer.PharmacyManagment.PharmacyVisit_Adaptor;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyVisitActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.JudgeApiService;

import java.util.List;

public class ManagmentJudgeVisitActivity extends AppCompatActivity {

    private RecyclerView rv;
    private JudgeApiService judgeApiService;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_judge_visit);
        rv=(RecyclerView)findViewById(R.id.judgemanagmentvistlistrecycle);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        judgeApiService=new JudgeApiService(this);
        judgeApiService.getJudgeVisitList(new JudgeApiService.OnjudgeVisitListReceived() {
            @Override
            public void onReceived(List<JudgeVisitList> judgevisitlist) {
                JudgeVisit_Adaptor pa=new JudgeVisit_Adaptor(ManagmentJudgeVisitActivity.this, judgevisitlist);
                rv.setLayoutManager(new LinearLayoutManager(ManagmentJudgeVisitActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(pa);
            }
        });
    }
}
