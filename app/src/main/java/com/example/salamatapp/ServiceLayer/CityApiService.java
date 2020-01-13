package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.ListCity;
import com.example.salamatapp.ServiceLayer.Converters.CItyConverter;
import com.example.salamatapp.ServiceLayer.Converters.GroupConverter;

import org.json.JSONArray;

import java.util.List;

public class CityApiService {
    private static final String TAG = "CityApiService";
    private Context context;

    private CItyConverter _cconvert;

    public CityApiService(Context context){
        this.context=context;
    }

    public void getCities(final CityApiService.OnCitiesReceived onCitiesReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetCityState", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _cconvert=new CItyConverter();
                onCitiesReceived.onReceived(_cconvert.ConverCity(response));
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

    public void getCitiesString(final CityApiService.OnCitiesStringReceived onCitiesReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetCityState", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _cconvert=new CItyConverter();
                onCitiesReceived.onReceived(_cconvert.ConverCityString(response));
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

    public interface OnCitiesReceived{
        void onReceived(List<City> cities);
    }

    public interface OnCitiesStringReceived{
        void onReceived(ListCity city);
    }

}
