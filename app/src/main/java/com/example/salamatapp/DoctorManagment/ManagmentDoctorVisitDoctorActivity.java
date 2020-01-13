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
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorVisitTime_Adaptor;
import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorVisit_Adaptor;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisitSelect;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DoctorVisitApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ManagmentDoctorVisitDoctorActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private PersianCalendar initDate;
    private PersianDatePickerDialog picker;
    private RecyclerView doctorvisitlist;
    private EditText txtdate,txtdes,txtcount;
    private Button btnselectdate,btnaddvisit;
    private DoctorVisitApiService _doctorvisit;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_visit_doctor);
        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();

        txtdate=(EditText)findViewById(R.id.txtdatedoctorvisitmanagment);
        txtdes=(EditText)findViewById(R.id.txtdoctordescriptionmanagment);
        txtcount=(EditText)findViewById(R.id.txtillnesscountmanagment);

        btnselectdate=(Button)findViewById(R.id.btnselectdoctortimemanagment);
        btnaddvisit=(Button)findViewById(R.id.btnadddoctorvisitmanagment);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        doctorvisitlist=(RecyclerView)findViewById(R.id.recycledoctorvisittimemanagment);

        _doctorvisit=new DoctorVisitApiService(this);
        _doctorvisit.getDoctorvisittimeList(useritem.getNumber().toString(), new DoctorVisitApiService.OnVisitTimeReceived() {
            @Override
            public void onReceived(List<DoctorVisitSelect> doctorvisitselect) {
                DoctorVisitTime_Adaptor pa=new DoctorVisitTime_Adaptor(ManagmentDoctorVisitDoctorActivity.this, doctorvisitselect);
                doctorvisitlist.setLayoutManager(new LinearLayoutManager(ManagmentDoctorVisitDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                doctorvisitlist.setAdapter(pa);
            }
        });


        btnselectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new PersianDatePickerDialog(ManagmentDoctorVisitDoctorActivity.this)
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
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });

                picker.show();
            }
        });


        btnaddvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (txtdate.getText().toString().length()>0 && txtdes.getText().toString().length()>0 && txtcount.getText().toString().length()>0){

                        _doctorvisit.postaddvisitlist(txtdate.getText().toString(), txtdes.getText().toString(), Integer.parseInt(txtcount.getText().toString()), useritem.getNumber().toString(), new DoctorVisitApiService.OnStringResponceVisitTimeReceived() {
                            @Override
                            public void onReceived(String doctorvisitstring) {
                                _doctorvisit.getDoctorvisittimeList(useritem.getNumber().toString(), new DoctorVisitApiService.OnVisitTimeReceived() {
                                    @Override
                                    public void onReceived(List<DoctorVisitSelect> doctorvisitselect) {
                                        DoctorVisitTime_Adaptor pa=new DoctorVisitTime_Adaptor(ManagmentDoctorVisitDoctorActivity.this, doctorvisitselect);
                                        doctorvisitlist.setLayoutManager(new LinearLayoutManager(ManagmentDoctorVisitDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                                        doctorvisitlist.setAdapter(pa);
                                    }
                                });
                            }
                        });

                    }else{
                        Toast.makeText(ManagmentDoctorVisitDoctorActivity.this, "فیلد ها نباید خالی باشد",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });


    }
}
