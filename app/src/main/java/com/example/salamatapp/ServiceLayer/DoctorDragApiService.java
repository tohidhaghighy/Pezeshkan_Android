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
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisit;
import com.example.salamatapp.ServiceLayer.Converters.CItyConverter;
import com.example.salamatapp.ServiceLayer.Converters.Doctor;

import org.json.JSONArray;

import java.util.List;

public class DoctorDragApiService {

    private static final String TAG = "DragApiService";
    private Context context;

    private Doctor _dconvert;

    public DoctorDragApiService(Context context){
        this.context=context;
    }

    public void getDoctorDragList(String mobile,final DoctorDragApiService.OnDoctorDragsReceived onDoctorDragsReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetDragDoctorList?mobile="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _dconvert=new Doctor();
                onDoctorDragsReceived.onReceived(_dconvert.ConverDoctordrag(response));
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

    public void postadddrag(String mobile,String text,String description,final DoctorDragApiService.OnDoctorDragReceived onDoctorDragReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddDragDoctor?mobile="+mobile+"&name="+text+"&description="+description, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onDoctorDragReceived.onReceived(response);
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

    public void postdeletedrag(Integer id,final DoctorDragApiService.OnDoctorDragReceived onDoctorDragReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/DeleteDragDoctor?id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onDoctorDragReceived.onReceived(response);
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

    public interface OnDoctorDragsReceived{
        void onReceived(List<DoctorDrag> doctordrags);
    }


    public interface OnDoctorDragReceived{
        void onReceived(String checkadddelete);
    }
}
