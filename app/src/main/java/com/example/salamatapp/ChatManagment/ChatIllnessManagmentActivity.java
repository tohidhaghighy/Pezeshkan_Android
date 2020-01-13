package com.example.salamatapp.ChatManagment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
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

import com.devlomi.record_view.OnBasketAnimationEnd;
import com.devlomi.record_view.OnRecordClickListener;
import com.devlomi.record_view.OnRecordListener;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;
import com.example.salamatapp.AdaptorLayer.DoctorManagment.DoctorDrag_Adaptor;
import com.example.salamatapp.AdaptorLayer.MassageChat_Adaptor;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorDragActivity;
import com.example.salamatapp.DomainLayer.BaseMessage;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.LoginActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.ChatApiService;
import com.example.salamatapp.ServiceLayer.Converters.MassageConverter;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;
import com.example.salamatapp.SplashActivity;
import com.example.salamatapp.bottomActivity;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatIllnessManagmentActivity extends AppCompatActivity {

    RecyclerView chatrecycle;
    EditText txtsendmassage;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private ImageView btnselectimage,btnsendmassage;
    private ImageButton btnback;
    Bitmap imgselectedimage;
    ChatApiService chatapi;
    int visitid=0;
    Boolean isuser=true;
    Handler handler;
    private Runnable mRunnable;
    int lengthupdate=0;

    private RecordButton recordButton = null;
    private MediaRecorder recorder = null;
    private RecordView recordView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_illness_managment);

        recordView = (RecordView) findViewById(R.id.record_view);
        recordButton = (RecordButton) findViewById(R.id.record_button);

        recorer_button();

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
                if (txtsendmassage.getText().length()>0){
                    chatapi.postillnessmassage(visitid, useritem.getNumber().toString(), txtsendmassage.getText().toString(), isuser, 0, new ChatApiService.OnSendMassageReceived() {
                        @Override
                        public void onReceived(String Sended) {
                            txtsendmassage.setText("");
                            showmassage();
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

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();


        chatrecycle=(RecyclerView)findViewById(R.id.chatroommanagmentlayout);






        mRunnable = new Runnable() {
            @Override
            public void run() {

                showmassage();
                handler.postDelayed(this, 10000);
            }
        };
        handler = new Handler();
        handler.post(mRunnable);

    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(mRunnable);
        super.onBackPressed();
        finish();
    }

    void showmassage(){
        chatapi.getIllnessChats(visitid, new ChatApiService.OnChatsReceived() {
            @Override
            public void onReceived(List<BaseMessage> chats) {
                if (lengthupdate<chats.size()){
                    MassageChat_Adaptor pa=new MassageChat_Adaptor(ChatIllnessManagmentActivity.this, chats);
                    chatrecycle.setLayoutManager(new LinearLayoutManager(ChatIllnessManagmentActivity.this,LinearLayoutManager.VERTICAL,true));
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
                    //Toast.makeText(ChatIllnessManagmentActivity.this,isuser.toString(),Toast.LENGTH_LONG).show();
                    chatapi.sendimageillnessmassage(visitid, imgselectedimage, useritem.getNumber().toString(), "", isuser, new ChatApiService.OnSendMassageReceived() {
                        @Override
                        public void onReceived(String Sended) {
                            if (Sended.contains("true")){
                                showmassage();
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



    void recorer_button(){
        recordButton.setRecordView(recordView);
        recordView.setOnRecordListener(new OnRecordListener() {
            @Override
            public void onStart() {
                //Start Recording..
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource("http://dls.tabanmusic.com/music/1398/09/30/Mehraad-Jam-Gole-Shaghayegh-128.mp3");
                    mediaPlayer.prepareAsync();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("RecordView", "onStart");
            }

            @Override
            public void onCancel() {
                //On Swipe To Cancel

                Log.d("RecordView", "onCancel");

            }

            @Override
            public void onFinish(long recordTime) {
                //Stop Recording..


            }

            @Override
            public void onLessThanSecond() {
                //When the record time is less than One Second
                Log.d("RecordView", "onLessThanSecond");
            }
        });


        recordButton.setOnRecordClickListener(new OnRecordClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChatIllnessManagmentActivity.this, "RECORD BUTTON CLICKED", Toast.LENGTH_SHORT).show();
                Log.d("RecordButton","RECORD BUTTON CLICKED");
            }
        });

        recordView.setOnBasketAnimationEndListener(new OnBasketAnimationEnd() {
            @Override
            public void onAnimationEnd() {
                Log.d("RecordView", "Basket Animation Finished");
            }
        });


        recordView.setSmallMicColor(Color.parseColor("#c2185b"));

        recordView.setSlideToCancelText("حذف کن");

        //disable Sounds
        recordView.setSoundEnabled(false);

        //prevent recording under one Second (it's false by default)
        recordView.setLessThanSecondAllowed(false);

        //set Custom sounds onRecord
        //you can pass 0 if you don't want to play sound in certain state
        recordView.setCustomSounds(R.raw.record_start,R.raw.record_finished,0);

        //change slide To Cancel Text Color
        recordView.setSlideToCancelTextColor(Color.parseColor("#ff0000"));
        //change slide To Cancel Arrow Color
        recordView.setSlideToCancelArrowColor(Color.parseColor("#ff0000"));
        //change Counter Time (Chronometer) color
        recordView.setCounterTimeColor(Color.parseColor("#ff0000"));
    }
}
