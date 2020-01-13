package com.example.salamatapp.DoctorManagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorDrag_Adaptor;
import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessJudge_Adaptor;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessJudgeListActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.RegisterMobilActivity;
import com.example.salamatapp.ServiceLayer.DoctorDragApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ManagmentDoctorDragActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private RecyclerView dragrecycle;
    private DoctorDragApiService _doctordragservice;
    private Button btnadddrag;
    private EditText txtname,txtdes;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_drag);

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();

        _doctordragservice=new DoctorDragApiService(this);
        dragrecycle=(RecyclerView)findViewById(R.id.doctormanagmentdraglistrecycle);
        btnadddrag=(Button)findViewById(R.id.btnadddragmanagment);
        txtname=(EditText)findViewById(R.id.txtnameofdragmanagment);
        txtdes=(EditText)findViewById(R.id.txtdescriptionofdragmanagment);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnadddrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtname.getText().toString()!="" && txtdes.getText().toString()!=""){

                    _doctordragservice.postadddrag(useritem.getNumber().toString(), txtname.getText().toString(), txtdes.getText().toString(), new DoctorDragApiService.OnDoctorDragReceived() {
                        @Override
                        public void onReceived(String checkadddelete) {
                                if (checkadddelete.equals("true")){
                                    loaddrag(useritem.getNumber().toString());
                                }
                                else {
                                    Toast.makeText(ManagmentDoctorDragActivity.this, "مشکل در ثبت اطلاعات",
                                            Toast.LENGTH_LONG).show();
                                }
                        }
                    });

                }else {
                    Toast.makeText(ManagmentDoctorDragActivity.this, "مقادیر نام و توضیحات نباید خالی باشد",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        loaddrag(useritem.getNumber().toString());
    }

    public  void loaddrag(String number){
        _doctordragservice.getDoctorDragList(number, new DoctorDragApiService.OnDoctorDragsReceived() {
            @Override
            public void onReceived(List<DoctorDrag> doctordrags) {
                DoctorDrag_Adaptor pa=new DoctorDrag_Adaptor(ManagmentDoctorDragActivity.this, doctordrags);
                dragrecycle.setLayoutManager(new LinearLayoutManager(ManagmentDoctorDragActivity.this,LinearLayoutManager.VERTICAL,false));
                dragrecycle.setAdapter(pa);
            }
        });
    }
}
