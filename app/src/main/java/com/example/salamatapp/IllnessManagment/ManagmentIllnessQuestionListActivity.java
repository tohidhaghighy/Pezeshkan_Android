package com.example.salamatapp.IllnessManagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessPharmacy_Adaptor;
import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessQuestion_Adaptor;
import com.example.salamatapp.ChatManagment.ChatIllnessManagmentActivity;
import com.example.salamatapp.DomainLayer.Question;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.IllnessApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;
import com.example.salamatapp.bottomActivity;

import java.util.List;

public class ManagmentIllnessQuestionListActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private Button btngotochat;
    private Integer visitid;
    private IllnessApiService _illnessapi;
    private RecyclerView questionanswerrecycleview;
    private ImageView btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_illness_question_list);
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

        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            visitid= 0;
        } else {
            visitid= extras.getInt("visitid");
        }

        btngotochat=(Button)findViewById(R.id.btnstartchatroomfromquestion);
        questionanswerrecycleview=(RecyclerView)findViewById(R.id.recyclequestionlistandanswer);

        _illnessapi=new IllnessApiService(this);

        _illnessapi.getIllnessAnswerList(visitid, new IllnessApiService.OnQuestionAnswerListReceived() {
            @Override
            public void onReceived(List<Question> QuestionList) {
                IllnessQuestion_Adaptor pa=new IllnessQuestion_Adaptor(ManagmentIllnessQuestionListActivity.this, QuestionList,visitid);
                questionanswerrecycleview.setLayoutManager(new LinearLayoutManager(ManagmentIllnessQuestionListActivity.this,LinearLayoutManager.VERTICAL,false));
                questionanswerrecycleview.setAdapter(pa);
            }
        });

        btngotochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagmentIllnessQuestionListActivity.this, ChatIllnessManagmentActivity.class);
                i.putExtra("visitid",visitid);
                i.putExtra("roleid","true");
                startActivity(i);
            }
        });

    }
}
