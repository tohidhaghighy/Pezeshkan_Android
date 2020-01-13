package com.example.salamatapp.IllnessManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.salamatapp.AdaptorLayer.Doctoritem_Adaptor;
import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessVisit_Adaptor;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.IllnessApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ManagmentIllnessVisitListActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private IllnessApiService _illnessservice;
    private RecyclerView visitrecycleview;
    private ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_illness_visit_list);
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
        visitrecycleview=(RecyclerView)findViewById(R.id.illnessmanagmentvisitlistrecycle);
        _illnessservice.getIllnessVisitList(useritem.getNumber().toString(), new IllnessApiService.OnillnessVisitListReceived() {
            @Override
            public void onReceived(List<IllnessVisitList> illnessvisitlist) {

                IllnessVisit_Adaptor pa=new IllnessVisit_Adaptor(ManagmentIllnessVisitListActivity.this, illnessvisitlist);
                visitrecycleview.setLayoutManager(new LinearLayoutManager(ManagmentIllnessVisitListActivity.this,LinearLayoutManager.VERTICAL,false));
                visitrecycleview.setAdapter(pa);
            }
        });

    }
}
