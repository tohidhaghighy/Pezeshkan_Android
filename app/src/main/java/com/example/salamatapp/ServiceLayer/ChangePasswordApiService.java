package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.ServiceLayer.Converters.BimeConverter;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordApiService {
    private static final String TAG = "ChangePassApiService";
    private Context context;


    public ChangePasswordApiService(Context context){
        this.context=context;
    }

    public void changepassword(final String mobile, final String password, final Integer type, final String newpassword, final ChangePasswordApiService.OnChangePassReceived onchangepassReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/ChangePassword", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("test",response);
                onchangepassReceived.onReceived(response);
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
                hashmap.put("newpassword",newpassword+"");
                hashmap.put("type",type+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public interface OnChangePassReceived{
        void onReceived(String response);
    }
}
