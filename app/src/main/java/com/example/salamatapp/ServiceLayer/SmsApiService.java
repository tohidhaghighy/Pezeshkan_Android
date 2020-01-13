package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.ServiceLayer.Converters.UserConverter;

import java.util.HashMap;
import java.util.Map;

public class SmsApiService {
    private static final String TAG = "SmsApiService";
    private Context context;
    private UserConverter _uc;


    public SmsApiService(Context context){
        this.context=context;
    }

    public void Sendactivesms(final String mobile, final String password, final String type, final SmsApiService.OnValueReceived onValueReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/SendActiveSms", new Response.Listener<String>() {
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
                hashmap.put("type",type+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void Checksendsmscodeforactive(final String mobile, final String password, final String type,final String code, final SmsApiService.OnValueReceived onValueReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/CheckSendCodeActiveSms", new Response.Listener<String>() {
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
                hashmap.put("type",type+"");
                hashmap.put("code",code+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }



    public interface OnValueReceived{
        void onReceived(String response);
    }
}
