package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.Bime;
import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.ListBime;
import com.example.salamatapp.DomainLayer.ListCity;
import com.example.salamatapp.ServiceLayer.Converters.BimeConverter;
import com.example.salamatapp.ServiceLayer.Converters.CItyConverter;

import org.json.JSONArray;

import java.util.List;

public class BimeApiService {
    private static final String TAG = "BimeApiService";
    private Context context;

    private BimeConverter _bconvert;

    public BimeApiService(Context context){
        this.context=context;
    }

    public void getBimes(final BimeApiService.OnBimesReceived onBimesReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetBimelist", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _bconvert=new BimeConverter();
                onBimesReceived.onReceived(_bconvert.ConverBime(response));
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

    public void getBimesString(final BimeApiService.OnBimesStringReceived onBimesStringReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetBimelist", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _bconvert=new BimeConverter();
                onBimesStringReceived.onReceived(_bconvert.ConverBimeString(response));
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

    public interface OnBimesReceived{
        void onReceived(List<Bime> bimes);
    }

    public interface OnBimesStringReceived{
        void onReceived(ListBime bime);
    }
}
