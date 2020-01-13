package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.NoskheInfo;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyInfo;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyVisitList;
import com.example.salamatapp.ServiceLayer.Converters.Doctor;
import com.example.salamatapp.ServiceLayer.Converters.NoskheConverter;
import com.example.salamatapp.ServiceLayer.Converters.PharmacyConverter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class NoskheApiService {

    private static final String TAG = "noskheApiService";
    private Context context;
    private NoskheConverter _noskheconvert;
    private Doctor _doctorservice;

    public NoskheApiService(Context context){
        this.context=context;
    }

    public void getNoskheInfo(Integer visitid,final NoskheApiService.OnNoskheInfoReceived onNoskheInfoReceived){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetNoskheInfo?visitid="+visitid, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                _noskheconvert=new NoskheConverter();
                onNoskheInfoReceived.onReceived(_noskheconvert.ConverOneNoskhe(response));
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


    public void getNoskheList(Integer visitid,final NoskheApiService.OnNoskhelistReceived onNoskhelistReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetNoskheMedicins?visitid="+visitid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _noskheconvert=new NoskheConverter();
                Log.e("list",response.toString());
                onNoskhelistReceived.onReceived(_noskheconvert.ConverListNoskhe(response));
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

    public void getNoskheMedicineFinally(Integer visitid,final NoskheApiService.OnNoskhelistReceived onNoskhelistReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"NoskheWebService/GetNoskheMedicine?visitid="+visitid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _noskheconvert=new NoskheConverter();
                onNoskhelistReceived.onReceived(_noskheconvert.ConverListNoskhe(response));
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

    public void getNoskheMedicineNotFinally(Integer visitid,final NoskheApiService.OnNoskhelistReceived onNoskhelistReceived){

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"NoskheWebService/GetNoskheMedicineNotFinally?visitid="+visitid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("aray",response.toString());
                _noskheconvert=new NoskheConverter();
                onNoskhelistReceived.onReceived(_noskheconvert.ConverListNoskhe(response));
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

    public void getSearchMedicineList(String mobile,String text,final NoskheApiService.OnMedicineDraglistReceived onMedicineDraglistReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"NoskheWebService/SearchMedicine?mobile="+mobile+"&text="+text, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _doctorservice=new Doctor();
                onMedicineDraglistReceived.onReceived(_doctorservice.ConverDoctordrag(response));
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

    public void postaddmedicine(String text,String description,String mobile,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"NoskheWebService/AddMedicine?text="+text+"&mobile="+mobile+"&description="+description, new Response.Listener<String>() {
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

    public void postfinalymedicine(Integer visitid,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"NoskheWebService/FinalyMedicine?visitid="+visitid, new Response.Listener<String>() {
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

    public void postaddmedicinetonoskhe(String text,Integer count,Integer visitid,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"NoskheWebService/AddMedicineToNoskhe?text="+text+"&visitid="+visitid+"&count="+count, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("responce",response.toString());
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


    public void postdeletemedicinefromnoskhe(Integer id,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"NoskheWebService/DeleteMedicineFromNoskhe?id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("responce",response.toString());
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


    public void postFinallymedicine(Integer visitid,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"NoskheWebService/FinalyMedicine?visitid="+visitid, new Response.Listener<String>() {
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

    public void postCheckFinallymedicine(Integer visitid,final NoskheApiService.OnReturnvalueReceived onReturnvalueReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"NoskheWebService/CheckFinalyMedicine?visitid="+visitid, new Response.Listener<String>() {
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

    public interface OnNoskheInfoReceived{
        void onReceived(NoskheInfo info);
    }

    public interface OnNoskhelistReceived{
        void onReceived(List<NoskheList> noskhelist);
    }

    public interface OnMedicineDraglistReceived{
        void onReceived(List<DoctorDrag> noskhedrags);
    }

    public interface OnReturnvalueReceived{
        void onReceived(String checkvalue);
    }
}
