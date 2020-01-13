package com.example.salamatapp.DoctorManagment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorVisit_Adaptor;
import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessJudge_Adaptor;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisit;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessInfoActivity;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessJudgeListActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ManagmentDoctorVisitListActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;

    private EditText txtdate;
    private Button btndate;
    private PersianCalendar initDate;
    private PersianDatePickerDialog picker;
    private DoctorApiService _doctorapiservice;
    private RecyclerView doctorvisitlist;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_visit_list);

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();

        txtdate=(EditText)findViewById(R.id.txtselectdoctorlistdate);
        btndate=(Button)findViewById(R.id.btnselectdoctorlistdate);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        doctorvisitlist=(RecyclerView)findViewById(R.id.doctormanagmentvisitlistrecycle);
        initDate = new PersianCalendar();
        initDate.setPersianDate(1398, 5, 5);


        _doctorapiservice=new DoctorApiService(this);

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker = new PersianDatePickerDialog(ManagmentDoctorVisitListActivity.this)
                        .setPositiveButtonString("باشه")
                        .setNegativeButton("بیخیال")
                        .setTodayButton("امروز")
                        .setTodayButtonVisible(true)
                        .setInitDate(initDate)
                        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                        .setMinYear(1300)
                        .setActionTextColor(Color.GRAY)
                        .setListener(new Listener() {
                            @Override
                            public void onDateSelected(PersianCalendar persianCalendar) {
                                String mounth="";
                                String Day="";
                                if ((persianCalendar.getPersianMonth()+"").length()==1){
                                    Log.e("mounth",persianCalendar.getPersianMonth()+"");
                                    mounth="0"+persianCalendar.getPersianMonth();
                                }else {
                                    mounth=persianCalendar.getPersianMonth()+"";
                                }
                                if ((persianCalendar.getPersianDay()+"").length()==1){
                                    Log.e("day",persianCalendar.getPersianDay()+"");
                                    Day="0"+persianCalendar.getPersianDay();
                                }else {
                                    Day=persianCalendar.getPersianDay()+"";
                                }
                                txtdate.setText(persianCalendar.getPersianYear() + "/" + mounth + "/" + Day);

                                _doctorapiservice.getDoctorIllnessVisitListDate(useritem.getNumber().toString(),txtdate.getText().toString(), new DoctorApiService.OnDoctorIllnessVisitTimeReceived() {
                                    @Override
                                    public void onReceived(List<DoctorVisit> visittimeillnessdoctor) {
                                        DoctorVisit_Adaptor pa=new DoctorVisit_Adaptor(ManagmentDoctorVisitListActivity.this, visittimeillnessdoctor);
                                        doctorvisitlist.setLayoutManager(new LinearLayoutManager(ManagmentDoctorVisitListActivity.this,LinearLayoutManager.VERTICAL,false));
                                        doctorvisitlist.setAdapter(pa);
                                    }
                                });
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });

                picker.show();
            }
        });

        _doctorapiservice.getDoctorIllnessVisitList(useritem.getNumber().toString(), new DoctorApiService.OnDoctorIllnessVisitTimeReceived() {
            @Override
            public void onReceived(List<DoctorVisit> visittimeillnessdoctor) {
                DoctorVisit_Adaptor pa=new DoctorVisit_Adaptor(ManagmentDoctorVisitListActivity.this, visittimeillnessdoctor);
                doctorvisitlist.setLayoutManager(new LinearLayoutManager(ManagmentDoctorVisitListActivity.this,LinearLayoutManager.VERTICAL,false));
                doctorvisitlist.setAdapter(pa);

            }
        });

    }
}
