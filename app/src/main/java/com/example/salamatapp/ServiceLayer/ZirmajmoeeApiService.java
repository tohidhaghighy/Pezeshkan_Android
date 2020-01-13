package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.Doctor.DoctorZir;
import com.example.salamatapp.ServiceLayer.Converters.UserConverter;
import com.example.salamatapp.ServiceLayer.Converters.ViolationConverter;
import com.example.salamatapp.ServiceLayer.Converters.ZirmajmoeeConverter;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZirmajmoeeApiService {
    private static final String TAG = "ZirmajmoeeApiService";
    private Context context;
    private ZirmajmoeeConverter _zc;


    public ZirmajmoeeApiService(Context context){
        this.context=context;
    }

    public void AddZirmajmoee(final String mobile, final String password, final String code, final ZirmajmoeeApiService.OnValueReceived onValueReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddZirmajmoeeInfo", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("sdfsf",response);
                onValueReceived.onReceived(response);
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
                hashmap.put("mobile",mobile+"");
                hashmap.put("password",password +"");
                hashmap.put("code",code+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }



    public void getZirmajmoee(final String mobile,final String password,final ZirmajmoeeApiService.OnZirmajmoeeValueReceived onZirmajmoeeValueReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetZirmajmoeeInfo?mobile="+mobile+"&password="+password, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("listofviolationb",response.toString());
                _zc=new ZirmajmoeeConverter();
                onZirmajmoeeValueReceived.onReceived(_zc.ConverDoctorZir(response));
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

    public interface OnValueReceived{
        void onReceived(String response);
    }

    public interface OnZirmajmoeeValueReceived{
        void onReceived(List<DoctorZir> doctorzirs);
    }
}
