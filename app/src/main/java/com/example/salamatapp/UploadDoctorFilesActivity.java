package com.example.salamatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.ServiceLayer.Upload.Uploadimage;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.io.ByteArrayOutputStream;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

public class UploadDoctorFilesActivity extends AppCompatActivity {

    ImageView imgcodemelli,imgshenasname,imgnezampezeshki;
    Button btncodemelli,btnshenasname,btnnezampezeshki,btnuploadfile;
    final int request=1;
    private Uploadimage uploadimg;
    private DoctorApiService doctorapi;
    private SmoothProgressBar mProgressBar;

    Bitmap imgcodemellibitmap,imgshenasnamebitmap,imgnezampezeshkibitmap;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_doctor_files);
        usermanager=new UserSharePrefrenceManager(this);
        Cast();
        uploadimg=new Uploadimage(this);
        useritem=usermanager.getUser();
        mProgressBar=(SmoothProgressBar)findViewById(R.id.progressbardoctormadarek);
        mProgressBar.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(UploadDoctorFilesActivity.this)
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
       imgcodemelli=(ImageView)findViewById(R.id.imguploadmellicart);
        imgshenasname=(ImageView)findViewById(R.id.imguploadshenasname);
        imgnezampezeshki=(ImageView)findViewById(R.id.imguploadnezampezeshki);
        btncodemelli=(Button) findViewById(R.id.btnuploadmellicart);
        btnnezampezeshki=(Button) findViewById(R.id.btnuploadnezampezeshki);
        btnshenasname=(Button) findViewById(R.id.btnuploadshenasname);
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
        btnnezampezeshki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect(3);
            }
        });
        btnuploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.progressiveStart();
                uploadimg.volleyrequestDoctorFiles(useritem.getNumber().toString(),imgcodemellibitmap, imgshenasnamebitmap, imgnezampezeshkibitmap, new Uploadimage.OndocimgReceived() {
                    @Override
                    public void onReceived(String doccheck) {
                        Intent i = new Intent(UploadDoctorFilesActivity.this, ManagmentDoctorActivity.class);
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
                    imgnezampezeshkibitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    Log.e("heightbefore",imgnezampezeshkibitmap.getHeight()+"");
                    Log.e("witdhbefore",imgnezampezeshkibitmap.getWidth()+"");
                    imgnezampezeshkibitmap=getResizedBitmap(imgnezampezeshkibitmap,400,400);
                    Log.e("height",imgnezampezeshkibitmap.getHeight()+"");
                    Log.e("witdh",imgnezampezeshkibitmap.getWidth()+"");
                    imgnezampezeshki.setImageBitmap(imgnezampezeshkibitmap);
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
