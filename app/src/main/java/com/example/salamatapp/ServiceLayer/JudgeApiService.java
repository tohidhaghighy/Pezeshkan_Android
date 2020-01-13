package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.Judge.JudgeInfo;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ServiceLayer.Converters.Doctor;
import com.example.salamatapp.ServiceLayer.Converters.IllnessConverter;
import com.example.salamatapp.ServiceLayer.Converters.JudgeConverter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JudgeApiService {
    private static final String TAG = "judgeApiService";
    private Context context;
    private JudgeConverter _judgeconvert;

    public JudgeApiService(Context context){
        this.context=context;
    }

    public String Upload(Bitmap bitmap){
        ByteArrayOutputStream by=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,by);
        byte bytes[]=by.toByteArray();
        String strimg= Base64.encodeToString(bytes,Base64.DEFAULT);
        return  strimg;
    }


    public void getJudgeInfo(String mobile,final JudgeApiService.OnJudgeReceived onJudgeReceived){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, UrlService.Url+"JudgeWebService/GetJudgeInfo?mobile="+mobile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                _judgeconvert=new JudgeConverter();
                onJudgeReceived.onReceived(_judgeconvert.ConverOneJudge(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public void postjudge(final String mobile, final String name, final Integer cityid,final String description, final Bitmap image, final JudgeApiService.OnJudgeStringReceived onJudgeStringReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"JudgeWebService/UpdateJudgeInfo", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onJudgeStringReceived.onReceived(response);
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
                if (image!=null){
                    String strimage=Upload(image);
                    hashmap.put("image",strimage);
                }
                else{
                    hashmap.put("image","");
                }
                hashmap.put("mobile",mobile+"");
                hashmap.put("name",name +"");
                hashmap.put("description",description+"");
                hashmap.put("cityid",cityid+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void getJudgeVisitList(final JudgeApiService.OnjudgeVisitListReceived onjudgeVisitListReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"JudgeWebService/GetJudgeVisitList", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _judgeconvert=new JudgeConverter();
                onjudgeVisitListReceived.onReceived(_judgeconvert.ConverVisitListJudge(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }



    public interface OnJudgeReceived{
        void onReceived(JudgeInfo judge);
    }

    public interface OnJudgeStringReceived{
        void onReceived(String checkIsok);
    }

    public interface OnjudgeVisitListReceived{
        void onReceived(List<JudgeVisitList> judgevisitlist);
    }



}
