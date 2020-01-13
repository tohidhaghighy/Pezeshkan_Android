package com.example.salamatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeActivity;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.ServiceLayer.Upload.Uploadimage;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

public class UploadJudgeFilesActivity extends AppCompatActivity {

    ImageView imgcodemelli,imgshenasname,imgnemadrak;
    Button btncodemelli,btnshenasname,btnmadrak,btnuploadfile;
    final int request=1;
    private Uploadimage uploadimg;
    private DoctorApiService doctorapi;
    Bitmap imgcodemellibitmap,imgshenasnamebitmap,imgmadrakbitmap;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private SmoothProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_judge_files);
        useritem=new SaveUserItem();
        usermanager=new UserSharePrefrenceManager(this);
        useritem=usermanager.getUser();
        Cast();
        uploadimg=new Uploadimage(this);

        mProgressBar=(SmoothProgressBar)findViewById(R.id.progressbarjudgemadarek);
        mProgressBar.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(UploadJudgeFilesActivity.this)
                .color(R.color.colorAccent)
                .interpolator(new DecelerateInterpolator())
                .sectionsCount(8)
                .separatorLength(8)       //You should use Resources#getDimension
                .progressiveStartSpeed(2)
                .progressiveStopSpeed(3.4f)
                .reversed(true)
                .mirrorMode(true)
                .progressiveStart(false)
                .build());
        mProgressBar.progressiveStart();
        mProgressBar.progressiveStop();
        SaveUserItem uu=new SaveUserItem();
        uu.setType(useritem.getType());
        uu.setIs_Active(true);
        uu.setNumber(useritem.getNumber());
        uu.setPassword(useritem.getPassword());
        uu.setName(useritem.getName());
        uu.setFirstTime(0);
        usermanager.saveUser(uu);
    }


    void Cast(){
        imgcodemelli=(ImageView)findViewById(R.id.imguploadmellicartjudge);
        imgshenasname=(ImageView)findViewById(R.id.imguploadshenasnamejudge);
        imgnemadrak=(ImageView)findViewById(R.id.imguploadmadrakjudge);
        btncodemelli=(Button) findViewById(R.id.btnuploadmellicartjudge);
        btnshenasname=(Button) findViewById(R.id.btnuploadshenasnamejudge);
        btnmadrak=(Button) findViewById(R.id.btnuploadmadarekjudge);
        btnuploadfile=(Button)findViewById(R.id.btnuploadfiledoctor);
        btncodemelli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect(1);
            }
        });
        btnshenasname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect(2);
            }
        });
        btnmadrak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect(3);
            }
        });
        btnuploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.progressiveStart();
                uploadimg.volleyrequestJudgeFiles(useritem.getNumber().toString(),imgcodemellibitmap, imgshenasnamebitmap, imgmadrakbitmap, new Uploadimage.OndocimgReceived() {
                    @Override
                    public void onReceived(String doccheck) {

                        Intent i = new Intent(UploadJudgeFilesActivity.this, ManagmentJudgeActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(resultCode,requestCode,data);
        if (resultCode==RESULT_OK && data!=null){

            Uri uri=data.getData();
            try{

                if (requestCode==1){
                    imgcodemellibitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    imgcodemellibitmap=getResizedBitmap(imgcodemellibitmap,400,400);
                    imgcodemelli.setImageBitmap(imgcodemellibitmap);
                }else if(requestCode==2){
                    imgshenasnamebitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    imgshenasnamebitmap=getResizedBitmap(imgshenasnamebitmap,400,400);
                    imgshenasname.setImageBitmap(imgshenasnamebitmap);
                }else if(requestCode==3){
                    imgmadrakbitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    imgmadrakbitmap=getResizedBitmap(imgmadrakbitmap,400,400);
                    imgnemadrak.setImageBitmap(imgmadrakbitmap);
                }

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
