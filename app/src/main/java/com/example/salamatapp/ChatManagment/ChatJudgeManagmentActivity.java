package com.example.salamatapp.ChatManagment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.MassageChat_Adaptor;
import com.example.salamatapp.DomainLayer.BaseMessage;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.ChatApiService;
import com.example.salamatapp.ServiceLayer.Converters.MassageConverter;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ChatJudgeManagmentActivity extends AppCompatActivity {

    RecyclerView chatrecycle;
    EditText txtsendmassage;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private ImageView btnselectimage,btnsendmassage;
    private ImageButton btnback;
    ChatApiService chatapi;
    int visitid=0;
    Boolean isuser=true;
    Bitmap imgselectedimage;
    Handler handler;
    private Runnable mRunnable;
    Integer lengthupdate=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_judge_managment);

        btnselectimage=(ImageView)findViewById(R.id.btnselectimage);
        btnsendmassage=(ImageView)findViewById(R.id.btnsendmassage);
        btnback=(ImageButton)findViewById(R.id.btnback);
        txtsendmassage=(EditText)findViewById(R.id.txtsendmassage);

        chatapi=new ChatApiService(this);

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();


        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            visitid= 0;
        } else {
            visitid= extras.getInt("visitid");
            if (extras.getString("roleid").contains("false")){
                isuser=false;
            }
        }

        btnselectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect(0);
            }
        });
        btnsendmassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Tas","test");

                if (txtsendmassage.getText().length()>0){
                    chatapi.postjudgemassage(visitid, useritem.getNumber().toString(), txtsendmassage.getText().toString(), isuser, 0, new ChatApiService.OnSendMassageReceived() {
                        @Override
                        public void onReceived(String Sended) {
                            txtsendmassage.setText("");
                            showallmassage();
                        }
                    });
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(mRunnable);
                finish();
            }
        });

        chatrecycle=(RecyclerView)findViewById(R.id.chatroommanagmentlayout);

        showallmassage();



        mRunnable = new Runnable() {
            @Override
            public void run() {
                showallmassage();
                handler.postDelayed(this, 10000);
            }
        };
        handler = new Handler();
        handler.post(mRunnable);



    }

    void showallmassage(){
        chatapi.getjudgeChats(visitid, new ChatApiService.OnChatsReceived() {
            @Override
            public void onReceived(List<BaseMessage> chats) {
                if (lengthupdate<chats.size()){
                    MassageChat_Adaptor pa=new MassageChat_Adaptor(ChatJudgeManagmentActivity.this, chats);
                    chatrecycle.setLayoutManager(new LinearLayoutManager(ChatJudgeManagmentActivity.this,LinearLayoutManager.VERTICAL,true));
                    chatrecycle.setAdapter(pa);
                    lengthupdate=chats.size();
                }

            }
        });
    }

    void Imageselect(int type){

        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,type);
    }



    @Override
    public void onBackPressed() {
        handler.removeCallbacks(mRunnable);
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(resultCode,requestCode,data);
        if (resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try{
                imgselectedimage= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgselectedimage=getResizedBitmap(imgselectedimage,400,400);
                chatapi.sendimagejudgesmassage(visitid, imgselectedimage, useritem.getNumber().toString(), "", isuser, new ChatApiService.OnSendMassageReceived() {
                    @Override
                    public void onReceived(String Sended) {
                        if (Sended.contains("true")){
                            showallmassage();
                        }
                    }
                });

            }catch (Exception e){

                e.printStackTrace();
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
