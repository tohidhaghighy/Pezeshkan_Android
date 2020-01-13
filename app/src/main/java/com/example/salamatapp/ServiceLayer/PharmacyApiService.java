package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.Judge.JudgeInfo;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyInfo;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyVisitList;
import com.example.salamatapp.ServiceLayer.Converters.JudgeConverter;
import com.example.salamatapp.ServiceLayer.Converters.PharmacyConverter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PharmacyApiService {
    private static final String TAG = "pharmacyApiService";
    private Context context;
    private PharmacyConverter _pharmacyconvert;

    public PharmacyApiService(Context context){
        this.context=context;
    }

    public String Upload(Bitmap bitmap){
        ByteArrayOutputStream by=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,by);
        byte bytes[]=by.toByteArray();
        String strimg= Base64.encodeToString(bytes,Base64.DEFAULT);
        return  strimg;
    }

    public void getPharmacyInfo(String mobile,final PharmacyApiService.OnPharmacyReceived onPharmacyReceived){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, UrlService.Url+"PharmacyWebService/GetPharmacyInfo?mobile="+mobile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                _pharmacyconvert=new PharmacyConverter();
                onPharmacyReceived.onReceived(_pharmacyconvert.ConverOnePharmacy(response));
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


    public void postpharmacy(final String mobile, final String name, final Integer cityid,final String description,final String address, final Bitmap image, final PharmacyApiService.OnPharmacyStringReceived onPharmacyStringReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"PharmacyWebService/UpdatePharmacyInfo", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onPharmacyStringReceived.onReceived(response);
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
                if (image!=null){
                    String strimage=Upload(image);
                    hashmap.put("image",strimage);
                }
                else{
                    hashmap.put("image","");
                }
                hashmap.put("mobile",mobile+"");
                hashmap.put("address",address+"");
                hashmap.put("name",name +"");
                hashmap.put("description",description+"");
                hashmap.put("cityid",cityid+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public void getPharmacyVisitList(final PharmacyApiService.OnpharmacyVisitListReceived onpharmacyVisitListReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"PharmacyWebService/GetAllPharmacyNoskhe", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _pharmacyconvert=new PharmacyConverter();
                onpharmacyVisitListReceived.onReceived(_pharmacyconvert.ConverVisitListPharmacy(response));
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


    public void postaddmassagepharmacy(final String mobile, final String massage, final Integer visitid, final PharmacyApiService.OnPharmacyStringReceived onPharmacyStringReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"PharmacyWebService/AddPharmacyMassage?mobile="+mobile+"&visitid="+visitid+"&massage="+massage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onPharmacyStringReceived.onReceived(response);
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

    public void postaddnoskhetopharmacy(final Integer visitid, final PharmacyApiService.OnPharmacyStringReceived onPharmacyStringReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"PharmacyWebService/SendToPharmacyNoskhe?visitid="+visitid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onPharmacyStringReceived.onReceived(response);
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

    public interface OnPharmacyReceived{
        void onReceived(PharmacyInfo pharmacy);
    }

    public interface OnPharmacyStringReceived{
        void onReceived(String checkIsok);
    }

    public interface OnpharmacyVisitListReceived{
        void onReceived(List<PharmacyVisitList> pharmacyvisitlist);
    }


}
