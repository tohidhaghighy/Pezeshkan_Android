package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.Doctor.DoctorQuestion;
import com.example.salamatapp.ServiceLayer.Converters.CItyConverter;
import com.example.salamatapp.ServiceLayer.Converters.Doctor;

import org.json.JSONArray;

import java.util.List;

public class DoctorQuestionApiService {

    private static final String TAG = "QuestionApiService";
    private Context context;

    private Doctor _dconvert;

    public DoctorQuestionApiService(Context context){
        this.context=context;
    }

    public void getDoctorQuestionList(String mobile,final DoctorQuestionApiService.OnDoctorQuestionsReceived onDoctorQuestionsReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetQuestionDoctorList?mobile="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _dconvert=new Doctor();
                onDoctorQuestionsReceived.onReceived(_dconvert.ConverDoctorquestion(response));
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

    public void postaddquestion(String mobile,String text,final DoctorQuestionApiService.OnDoctorQuestionReceived onDoctorQuestionReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddQuestionDoctor?mobile="+mobile+"&text="+text, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onDoctorQuestionReceived.onReceived(response);
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

    public void postdeletequestion(Integer id,final DoctorQuestionApiService.OnDoctorQuestionReceived onDoctorQuestionReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/DeleteQuestionDoctor?id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onDoctorQuestionReceived.onReceived(response);
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


    public interface OnDoctorQuestionsReceived{
        void onReceived(List<DoctorQuestion> doctorquestions);
    }


    public interface OnDoctorQuestionReceived{
        void onReceived(String checkadddelete);
    }

}
