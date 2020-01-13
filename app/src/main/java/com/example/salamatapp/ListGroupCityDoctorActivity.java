package com.example.salamatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.Doctoritem_Adaptor;
import com.example.salamatapp.AdaptorLayer.GroupItemWithCity_Adaptor;
import com.example.salamatapp.AdaptorLayer.GroupItem_Adaptor;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ServiceLayer.GroupApiService;
import com.example.salamatapp.ServiceLayer.UserApiService;

import java.util.List;

public class ListGroupCityDoctorActivity extends AppCompatActivity {

    private RecyclerView rv1,rvsearch;
    private GroupApiService _groupapi;
    private UserApiService _userapi;
    private Integer cityid;
    private TextView txtnameofcity;
    private String cityname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_group_city_doctor);

        rv1=(RecyclerView)findViewById(R.id.grouprecyclebenforcitysearch);
        rvsearch=(RecyclerView)findViewById(R.id.doctorcitygrouprecycleben);
        _groupapi=new GroupApiService(this);
        _userapi=new UserApiService(this);

        txtnameofcity=(TextView)findViewById(R.id.txtlistpezeshkancity);

        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            cityid= 0;
            cityname="";
        } else {
            cityid= extras.getInt("cityid");
            cityname= extras.getString("cityname");
        }

        txtnameofcity.setText(" لیست پزشکان "+cityname);

        _groupapi.getDoctors(new GroupApiService.OnGroupsReceived() {
            @Override
            public void onReceived(List<Group> groups) {
                GroupItemWithCity_Adaptor pa1=new GroupItemWithCity_Adaptor(ListGroupCityDoctorActivity.this, groups,cityid);
                rv1.setLayoutManager(new LinearLayoutManager(ListGroupCityDoctorActivity.this,LinearLayoutManager.HORIZONTAL,false));
                rv1.setAdapter(pa1);
            }
        });

        _userapi.getDoctorsWithCity(cityid,new UserApiService.OnUsersReceived() {
            @Override
            public void onReceived(List<User> users) {

                Doctoritem_Adaptor pa=new Doctoritem_Adaptor(ListGroupCityDoctorActivity.this, users);
                rvsearch.setLayoutManager(new LinearLayoutManager(ListGroupCityDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                rvsearch.setAdapter(pa);
            }
        });
    }
}
