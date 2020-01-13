package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ServiceLayer.Converters.UserConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserApiService {
    private static final String TAG = "UserApiService";
    private Context context;
    private UserConverter _uc;


    public UserApiService(Context context){
        this.context=context;
    }

    //Doctor function

    public void getDoctors(final OnUsersReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetAllDoctor", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverDoctortUser(response));
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

    public void getGroupCityDoctors(Integer groupid,Integer cityid,final OnUsersReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetCityGroupDoctor?groupid="+groupid+"&cityid="+cityid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverDoctortUser(response));
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

    public void getDoctorsWithGroup(int group,final OnUsersReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetGroupDoctor?groupid="+group, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverDoctortUser(response));
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


    public void getDoctorsWithCity(int city,final OnUsersReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetCityDoctor?cityid="+city, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverDoctortUser(response));
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

    public void getDoctorsSearchText(String text,final OnUsersReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetTextSearchDoctor?text"+text, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverDoctortUser(response));
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



    // Judge Function

    public void getJudges(final OnUsersReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"JudgeWebService/GetAllJudge", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverJudgetUser(response));
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


    // Pharmacy Function

    public void getPharmacys(final OnUsersReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"PharmacyWebService/GetAllPharmacy", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverPharmacytUser(response));
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




    //Inrerfaces

    public interface OnUsersReceived{
        void onReceived(List<User> users);
    }
}
