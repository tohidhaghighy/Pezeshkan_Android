package com.example.salamatapp.IllnessManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessJudge_Adaptor;
import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessVisit_Adaptor;
import com.example.salamatapp.DomainLayer.Illness.IllnessJudge;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.IllnessApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ManagmentIllnessJudgeListActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private IllnessApiService _illnessservice;
    private RecyclerView judgerecycleview;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_illness_judge_list);
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
        judgerecycleview=(RecyclerView)findViewById(R.id.illnessmanagmentjudgelistrecycle);

        _illnessservice.getIllnessJudgeList(useritem.getNumber().toString(), new IllnessApiService.OnillnessJudgeListReceived() {
            @Override
            public void onReceived(List<IllnessJudge> illnessjudgelist) {


                Log.e("number",useritem.getNumber().toString());
                IllnessJudge_Adaptor pa=new IllnessJudge_Adaptor(ManagmentIllnessJudgeListActivity.this, illnessjudgelist);
                judgerecycleview.setLayoutManager(new LinearLayoutManager(ManagmentIllnessJudgeListActivity.this,LinearLayoutManager.VERTICAL,false));
                judgerecycleview.setAdapter(pa);
            }
        });

    }
}
