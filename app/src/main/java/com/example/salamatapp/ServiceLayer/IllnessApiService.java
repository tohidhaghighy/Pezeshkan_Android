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
import com.example.salamatapp.DomainLayer.Illness.Illness;
import com.example.salamatapp.DomainLayer.Illness.IllnessJudge;
import com.example.salamatapp.DomainLayer.Illness.IllnessPharmacyList;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.Question;
import com.example.salamatapp.ServiceLayer.Converters.CItyConverter;
import com.example.salamatapp.ServiceLayer.Converters.IllnessConverter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IllnessApiService {


    private static final String TAG = "illnessApiService";
    private Context context;
    private IllnessConverter _illnessconvert;

    public IllnessApiService(Context context){
        this.context=context;
    }

    public String Upload(Bitmap bitmap){
        ByteArrayOutputStream by=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,by);
        byte bytes[]=by.toByteArray();
        String strimg= Base64.encodeToString(bytes,Base64.DEFAULT);
        return  strimg;
    }


    public void getIllness(String mobile,final IllnessApiService.OnillnessReceived onIllnessReceived){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, UrlService.Url+"IllnessWebService/GetIllnessInfo?mobile="+mobile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                _illnessconvert=new IllnessConverter();
                onIllnessReceived.onReceived(_illnessconvert.ConverOneIllness(response));
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


    public void postUpdateIllness(final Integer id, final String name, final Integer suger, final Integer pressure, final Integer cityid, final Integer bimeid, final Integer weight, final Integer age, final String serialbime, final String date, final Bitmap image, final IllnessApiService.OnillnessimgReceived onillnessimgReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"IllnessWebService/UpdateIllnessInfo", new Response.Listener<String>() {
                @Override
            public void onResponse(String response) {
                    //Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    onillnessimgReceived.onReceived(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
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
                hashmap.put("id",id+"");
                hashmap.put("name",name+"");
                hashmap.put("sugar",suger+"");
                hashmap.put("cityid",cityid+"");
                hashmap.put("presure",pressure+"");
                hashmap.put("bimeid",bimeid+"");
                hashmap.put("weight",weight+"");
                hashmap.put("age",age+"");
                hashmap.put("serialbime",serialbime+"");
                hashmap.put("date",date+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public void getIllnessVisitList(String mobile,final IllnessApiService.OnillnessVisitListReceived onillnessVisitListReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"IllnessWebService/GetIllnessVisitList?mobile="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("answer",response.toString());
                _illnessconvert=new IllnessConverter();
                onillnessVisitListReceived.onReceived(_illnessconvert.ConverVisitListIllness(response));
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

    public void getIllnessJudgeList(String mobile,final IllnessApiService.OnillnessJudgeListReceived onillnessJudgeListReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"IllnessWebService/GetIllnessJudgeVisitList?mobile="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("array",response.toString());
                _illnessconvert=new IllnessConverter();
                onillnessJudgeListReceived.onReceived(_illnessconvert.ConverJudgeListIllness(response));
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


    public void getIllnessPharmacyList(String mobile,final IllnessApiService.OnillnessPharmacyListReceived onillnessPharmacyListReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"IllnessWebService/GetPharmacyMassageList?mobile="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _illnessconvert=new IllnessConverter();
                Log.e("aray",response.toString());
                onillnessPharmacyListReceived.onReceived(_illnessconvert.ConverPharmacyListIllness(response));
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

    public void getIllnessAnswerList(Integer visitid,final IllnessApiService.OnQuestionAnswerListReceived onQuestionAnswerListReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"IllnessWebService/GetQuestionList?visitid="+visitid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("array",response.toString());
                _illnessconvert=new IllnessConverter();
                onQuestionAnswerListReceived.onReceived(_illnessconvert.GetAllQuestionList(response));
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


    public void postUpdateAnswerQuestion(final Integer questionid, final String text, final IllnessApiService.OnillnessimgReceived onillnessimgReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"IllnessWebService/UpdateAnswerQuestion?questionid="+questionid+"&text="+text, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                onillnessimgReceived.onReceived(response);
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


    public void postAddAnswerQuestion(final Integer questionid,final Integer visitid, final String text, final IllnessApiService.OnillnessimgReceived onillnessimgReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"IllnessWebService/AddAnswerQuestion?questionid="+questionid+"&text="+text+"&visitid="+visitid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                onillnessimgReceived.onReceived(response);
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

    public void postUpdateAnswerQuestion(final Integer questionid,final Integer visitid, final String text, final IllnessApiService.OnillnessimgReceived onillnessimgReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"IllnessWebService/UpdateAnswerQuestionForIllness?questionid="+questionid+"&text="+text+"&visitid="+visitid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                onillnessimgReceived.onReceived(response);
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

    public interface OnillnessimgReceived{
        void onReceived(String doccheck);
    }

    public interface OnillnessReceived{
        void onReceived(Illness illnessinfo);
    }

    public interface OnillnessVisitListReceived{
        void onReceived(List<IllnessVisitList> illnessvisitlist);
    }

    public interface OnillnessJudgeListReceived{
        void onReceived(List<IllnessJudge> illnessjudgelist);
    }

    public interface OnillnessPharmacyListReceived{
        void onReceived(List<IllnessPharmacyList> illnesspharmacylist);
    }

    public interface OnQuestionAnswerListReceived{
        void onReceived(List<Question> QuestionList);
    }
}
