package com.example.salamatapp.IllnessManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessJudge_Adaptor;
import com.example.salamatapp.AdaptorLayer.ViolationAdaptor;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.DomainLayer.Violation;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.ViolationApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ListOfProtestActivity extends AppCompatActivity {

    RecyclerView rvprotest;
    ViolationApiService violationservice;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_protest);
        rvprotest=(RecyclerView)findViewById(R.id.recycleprotestlistandanswer);

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();


        violationservice=new ViolationApiService(this);

        violationservice.getViolations(useritem.getNumber().toString(), new ViolationApiService.OnListViolationReceived() {
            @Override
            public void onReceived(List<Violation> violations) {
                ViolationAdaptor pa=new ViolationAdaptor(ListOfProtestActivity.this, violations);
                rvprotest.setLayoutManager(new LinearLayoutManager(ListOfProtestActivity.this,LinearLayoutManager.VERTICAL,false));
                rvprotest.setAdapter(pa);
            }
        });
    }
}
