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
import com.example.salamatapp.DomainLayer.Violation;
import com.example.salamatapp.ServiceLayer.Converters.BimeConverter;
import com.example.salamatapp.ServiceLayer.Converters.ViolationConverter;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViolationApiService {
    private static final String TAG = "ViolationApiService";
    private Context context;
    private ViolationConverter violationconvert;


    public ViolationApiService(Context context){
        this.context=context;
    }

    public void AddViolation(final String mobile, final String number, final String description, final ChangePasswordApiService.OnChangePassReceived onchangepassReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddViolationUser", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("sdfsf",response);
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
                hashmap.put("userid",mobile+"");
                hashmap.put("number",number +"");
                hashmap.put("description",description+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public void getViolations(final String mobile,final ViolationApiService.OnListViolationReceived onViolationsReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetViolationUser?userid="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("listofviolationb",response.toString());
                violationconvert=new ViolationConverter();
                onViolationsReceived.onReceived(violationconvert.ConverViolation(response));
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


    public interface OnChangePassReceived{
        void onReceived(String response);
    }

    public interface OnListViolationReceived{
        void onReceived(List<Violation> violations);
    }
}
