package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.ServiceLayer.Converters.PharmacyConverter;

public class ReserveApiService {
    private static final String TAG = "reserveApiService";
    private Context context;

    public ReserveApiService(Context context){
        this.context=context;
    }


    public void postaddreservejudge(String number,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddVisitJudge?number="+number+"&cost=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onReturnvalueReceived.onReceived(response);
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

    public void postaddreservedoctor(String number,Integer timeid,Integer doctorid,String date,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddVisitDoctorItem?timeid="+timeid+"&number="+number+"&doctorid="+doctorid+"&date="+date, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("error",response);
                onReturnvalueReceived.onReceived(response);
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



    public void getjudgecost(final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){
        StringRequest request=new StringRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetJudgeCost", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onReturnvalueReceived.onReceived(response);
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

    public interface OnReturnvalueReceived{
        void onReceived(String checkvalue);
    }

}
