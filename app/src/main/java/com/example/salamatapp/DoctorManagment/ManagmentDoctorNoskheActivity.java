package com.example.salamatapp.DoctorManagment;

import android.content.Intent;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorNoskheSelection_Adaptor;
import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorNoskheWithDelete_Adaptor;
import com.example.salamatapp.AdaptorLayer.Doctoritem_Adaptor;
import com.example.salamatapp.AdaptorLayer.PharmacyJudgeAdaptor;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.NoskheManagmentIllnessActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.NoskheApiService;
import com.example.salamatapp.ServiceLayer.UserApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ManagmentDoctorNoskheActivity extends AppCompatActivity {

    private EditText edtnoskhetext;
    private Button btnadddrag,btncheckfinally;
    private RecyclerView rvselectionnoskhe,rvfinallynoskhe;
    private Intent i;
    private NoskheApiService _noskheapi;
    private Integer visitid;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private ImageView btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_noskhe);
        edtnoskhetext=(EditText)findViewById(R.id.txttextofsearchlistmanagment);
        btnadddrag=(Button)findViewById(R.id.btnadddragpharmacymanagment);
        rvselectionnoskhe=(RecyclerView)findViewById(R.id.doctormanagmentselectiondraglistrecycle);
        rvfinallynoskhe=(RecyclerView)findViewById(R.id.doctormanagmentfinallynoskhelistrecycle);
        _noskheapi=new NoskheApiService(this);
        btncheckfinally=(Button)findViewById(R.id.btnaddfinalynoskheandshownoskhetouser);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            visitid= 0;
        } else {
            visitid= extras.getInt("visitid");
        }

        _noskheapi.postCheckFinallymedicine(visitid, new NoskheApiService.OnReturnvalueReceived() {
            @Override
            public void onReceived(String checkvalue) {

                Log.e("tagerror",checkvalue.toString());
                if (checkvalue.toString().equals("true")){
                    Log.e("tagerror","true");
                    Intent i = new Intent(ManagmentDoctorNoskheActivity.this, NoskheManagmentIllnessActivity.class);
                    i.putExtra("visitid",visitid);
                    startActivity(i);
                    finish();

               }

            }
        });

        btncheckfinally.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _noskheapi.postFinallymedicine(visitid, new NoskheApiService.OnReturnvalueReceived() {
                    @Override
                    public void onReceived(String checkvalue) {
                        if (checkvalue.equals("true")){
                            Toast.makeText(ManagmentDoctorNoskheActivity.this, "با موفقیت نهایی شد !!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();



        _noskheapi.getNoskheMedicineNotFinally(visitid, new NoskheApiService.OnNoskhelistReceived() {
            @Override
            public void onReceived(List<NoskheList> noskhelist) {
                DoctorNoskheWithDelete_Adaptor noskhe=new DoctorNoskheWithDelete_Adaptor(ManagmentDoctorNoskheActivity.this, noskhelist,visitid);
                rvfinallynoskhe.setLayoutManager(new LinearLayoutManager(ManagmentDoctorNoskheActivity.this,LinearLayoutManager.VERTICAL,false));
                rvfinallynoskhe.setAdapter(noskhe);
            }
        });


        btnadddrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ManagmentDoctorNoskheActivity.this, ManagmentDoctorDragActivity.class);
                startActivity(i);
            }
        });


        edtnoskhetext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                  _noskheapi.getSearchMedicineList(useritem.getNumber().toString(), edtnoskhetext.getText().toString(), new NoskheApiService.OnMedicineDraglistReceived() {
                         @Override
                  public void onReceived(List<DoctorDrag> noskhedrags) {

                             DoctorNoskheSelection_Adaptor noskhe=new DoctorNoskheSelection_Adaptor(ManagmentDoctorNoskheActivity.this, noskhedrags,visitid);
                             rvselectionnoskhe.setLayoutManager(new LinearLayoutManager(ManagmentDoctorNoskheActivity.this,LinearLayoutManager.VERTICAL,false));
                             rvselectionnoskhe.setAdapter(noskhe);

                  }});
            }
        });


    }
}
