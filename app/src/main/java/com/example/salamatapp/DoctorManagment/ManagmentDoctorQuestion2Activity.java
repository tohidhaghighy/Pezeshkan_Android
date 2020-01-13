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
import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorQuestion_Adaptor;
import com.example.salamatapp.DomainLayer.Doctor.DoctorQuestion;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DoctorQuestionApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ManagmentDoctorQuestion2Activity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;

    private RecyclerView questionrecycle;
    private DoctorQuestionApiService _questionservice;
    private EditText txttext;
    private Button btnadd;
    private ImageView btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_question2);

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();
        questionrecycle=(RecyclerView)findViewById(R.id.doctormanagmentquestionlistrecycle);
        _questionservice=new DoctorQuestionApiService(this);
        txttext=(EditText)findViewById(R.id.txtnameofquestionmanagment);
        btnadd=(Button)findViewById(R.id.btnaddquestionmanagment);

        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txttext.getText().toString() != "") {
                    _questionservice.postaddquestion(useritem.getNumber().toString(), txttext.getText().toString(), new DoctorQuestionApiService.OnDoctorQuestionReceived() {
                        @Override
                        public void onReceived(String checkadddelete) {
                            if (checkadddelete.equals("true")){
                                loadquestions(useritem.getNumber().toString());
                            }else {
                                Toast.makeText(ManagmentDoctorQuestion2Activity.this, "مشکل باارتباط با سرور",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }else {
                    Toast.makeText(ManagmentDoctorQuestion2Activity.this, "متن سوال نباید خالی باشد",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



        loadquestions(useritem.getNumber().toString());


    }

    public void loadquestions(String number){
        _questionservice.getDoctorQuestionList(number, new DoctorQuestionApiService.OnDoctorQuestionsReceived() {
            @Override
            public void onReceived(List<DoctorQuestion> doctorquestions) {
                DoctorQuestion_Adaptor pa=new DoctorQuestion_Adaptor(ManagmentDoctorQuestion2Activity.this, doctorquestions);
                questionrecycle.setLayoutManager(new LinearLayoutManager(ManagmentDoctorQuestion2Activity.this,LinearLayoutManager.VERTICAL,false));
                questionrecycle.setAdapter(pa);
            }
        });
    }
}
