package com.example.salamatapp.ServiceLayer.Upload;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.ServiceLayer.UrlService;
import com.example.salamatapp.UploadDoctorFilesActivity;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Uploadimage {

    Context context;
    public Uploadimage(Context context){
        this.context=context;
    }


    public String Upload(Bitmap bitmap){
        ByteArrayOutputStream by=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,by);
        byte bytes[]=by.toByteArray();
        String strimg= Base64.encodeToString(bytes,Base64.DEFAULT);
        return  strimg;
    }


    public void volleyrequestDoctorFiles(final String Number,final Bitmap bitmapmellicart,final Bitmap bitmapshenasname,final Bitmap bitmapnezampezeshki,final Uploadimage.OndocimgReceived onDoctorImageReceived){

        StringRequest stringrequest=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/SaveImageDoctor", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onDoctorImageReceived.onReceived(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> hashmap=new HashMap<String,String>();
                String strimgmellicart=Upload(bitmapmellicart);
                String strimgshenasname=Upload(bitmapshenasname);
                String strimgnezampezeshki=Upload(bitmapnezampezeshki);
                hashmap.put("Number",Number);
                hashmap.put("ImgStrMelliCart",strimgmellicart);
                hashmap.put("ImgStrShenasname",strimgshenasname);
                hashmap.put("ImgStrNezamPezeshki",strimgnezampezeshki);
                return hashmap;
            }
        };

        stringrequest.setRetryPolicy(new DefaultRetryPolicy(180000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(stringrequest);
    }

    public void volleyrequestJudgeFiles(final String Number,final Bitmap bitmapmellicart,final Bitmap bitmapshenasname,final Bitmap bitmapmadrak,final Uploadimage.OndocimgReceived onDoctorImageReceived){

        StringRequest stringrequest=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/SaveImageJudge", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onDoctorImageReceived.onReceived(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> hashmap=new HashMap<String,String>();
                String strimgmellicart=Upload(bitmapmellicart);
                String strimgshenasname=Upload(bitmapshenasname);
                String strimgmadrak=Upload(bitmapmadrak);
                hashmap.put("Number",Number);
                hashmap.put("ImgStrMelliCart",strimgmellicart);
                hashmap.put("ImgStrShenasname",strimgshenasname);
                hashmap.put("ImgStrMadrak",strimgmadrak);
                return hashmap;
            }
        };

        stringrequest.setRetryPolicy(new DefaultRetryPolicy(180000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(stringrequest);
    }

    public void volleyrequestPharmacyFiles(final String Number,final Bitmap bitmapmellicart,final Bitmap bitmapshenasname,final Bitmap bitmapparvane,final Bitmap bitmapnezampezeshki,final Uploadimage.OndocimgReceived onDoctorImageReceived){

        StringRequest stringrequest=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/SaveImagePharmacy", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onDoctorImageReceived.onReceived(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> hashmap=new HashMap<String,String>();
                String strimgmellicart=Upload(bitmapmellicart);
                String strimgshenasname=Upload(bitmapshenasname);
                String strimgparvane=Upload(bitmapparvane);
                String strimgnezampezeshki=Upload(bitmapnezampezeshki);
                hashmap.put("Number",Number);
                hashmap.put("ImgStrMelliCart",strimgmellicart);
                hashmap.put("ImgStrShenasname",strimgshenasname);
                hashmap.put("ImgStrParvane",strimgparvane);
                hashmap.put("ImgStrNezamPezeshki",strimgnezampezeshki);
                return hashmap;
            }
        };

        stringrequest.setRetryPolicy(new DefaultRetryPolicy(180000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(stringrequest);
    }



    //Inrerfaces

    public interface OndocimgReceived{
        void onReceived(String doccheck);
    }
}
