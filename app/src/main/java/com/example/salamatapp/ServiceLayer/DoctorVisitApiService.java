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
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisitSelect;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.ServiceLayer.Converters.Doctor;

import org.json.JSONArray;

import java.util.List;

public class DoctorVisitApiService {
    private static final String TAG = "QuestionApiService";
    private Context context;

    private Doctor _dc;


    public DoctorVisitApiService(Context context){
        this.context=context;
    }


    public void getDoctorvisittimeList(String mobile,final DoctorVisitApiService.OnVisitTimeReceived onVisitTimeReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetVisitDoctorList?mobile="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                _dc=new Doctor();
                onVisitTimeReceived.onReceived(_dc.ConverDoctorvisitlist(response));

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

    public void postaddvisitlist(String date,String description,Integer count,String mobile,final DoctorVisitApiService.OnStringResponceVisitTimeReceived onStringResponceVisitTimeReceived){
        Log.e("list",UrlService.Url+"DoctorWebService/AddVisitDoctorList?date="+date+"&count="+count+"&description="+description+"&mobile="+mobile);

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddVisitDoctorList?date="+date+"&count="+count+"&description="+description+"&mobile="+mobile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("list",response.toString());
                onStringResponceVisitTimeReceived.onReceived(response);
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

    public void postdeletevisitlist(Integer id,final DoctorVisitApiService.OnStringResponceVisitTimeReceived onStringResponceVisitTimeReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/DeleteVisitDoctor?id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onStringResponceVisitTimeReceived.onReceived(response);
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

    public interface OnVisitTimeReceived{
        void onReceived(List<DoctorVisitSelect> doctorvisitselect);
    }

    public interface OnStringResponceVisitTimeReceived{
        void onReceived(String doctorvisitstring);
    }

}
