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
import com.example.salamatapp.DomainLayer.Doctor.DoctorInfo;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisit;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.DomainLayer.VisitTimeDoctor;
import com.example.salamatapp.ServiceLayer.Converters.Doctor;
import com.example.salamatapp.ServiceLayer.Converters.IllnessConverter;
import com.example.salamatapp.ServiceLayer.Converters.UserConverter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorApiService {
    private static final String TAG = "UserApiService";
    private Context context;
    private UserConverter _uc;
    private Doctor _dc;

    public DoctorApiService(Context context){
        this.context=context;
    }


    public String Upload(Bitmap bitmap){
        ByteArrayOutputStream by=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,by);
        byte bytes[]=by.toByteArray();
        String strimg= Base64.encodeToString(bytes,Base64.DEFAULT);
        return  strimg;
    }

    //Doctor function

    public void getDoctor(int id,final DoctorApiService.OnDoctorReceived onUsersReceived){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetDoctor?id="+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                _uc=new UserConverter();
                onUsersReceived.onReceived(_uc.ConverOneDoctortUser(response));
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


    public void postcheckuser(String mobile,String password,Integer type,final DoctorApiService.OnDoctorLoginReceived onLoginUsersReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/CheckUserWithSms?mobile="+mobile+"&password="+password+"&type="+type, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoginUsersReceived.onReceived(response);
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

    public void postuser(String name,String mobile,String password,Integer type,Integer cityid,final DoctorApiService.OnDoctorLoginReceived onLoginUsersReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/AddUserWithSms?name="+name+"&mobile="+mobile+"&cityid="+cityid+"&password="+password+"&type="+type, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoginUsersReceived.onReceived(response);
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

    public void getDoctorTimes(int id,final DoctorApiService.OnDoctorTimeReceived onDoctorTimeReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetVisitTimeDoctor?id="+id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _dc=new Doctor();
                onDoctorTimeReceived.onReceived(_dc.ConverDoctortTimes(response));
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


    public void getDoctorWithDoctor(String mobile,final DoctorApiService.OnDoctorItemReceived onDoctorItemReceived){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetDoctorInfo?mobile="+mobile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                _dc=new Doctor();
                onDoctorItemReceived.onReceived(_dc.ConverDoctortInfo(response));
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


    public void postdoctor(final Integer id, final String name, final Integer cityid,final Integer groupid,final String description,final Integer cost,final String nezampezeshki, final Bitmap image, final DoctorApiService.OnDoctorLoginReceived onLoginUsersReceived){

        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"DoctorWebService/UpdateDoctorInfo", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onLoginUsersReceived.onReceived(response);
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
                hashmap.put("id",id+"");
                hashmap.put("name",name +"");
                hashmap.put("description",description+"");
                hashmap.put("cityid",cityid+"");
                hashmap.put("groupid",groupid+"");
                hashmap.put("cost",cost+"");
                hashmap.put("nezampezeshki",nezampezeshki+"");
                return hashmap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }



    public void getDoctorIllnessVisitList(String mobile,final DoctorApiService.OnDoctorIllnessVisitTimeReceived onDoctorIllnessVisitTimeReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetDoctorVisitList?mobile="+mobile, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _dc=new Doctor();
                Log.e("array",response.toString());
                onDoctorIllnessVisitTimeReceived.onReceived(_dc.ConverDoctorvisitTimes(response));
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


    public void getDoctorIllnessVisitListDate(String mobile,String date,final DoctorApiService.OnDoctorIllnessVisitTimeReceived onDoctorIllnessVisitTimeReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetDoctorVisitListWithDateFinishToStart?mobile="+mobile+"&date="+date, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _dc=new Doctor();
                Log.e("array",response.toString());
                onDoctorIllnessVisitTimeReceived.onReceived(_dc.ConverDoctorvisitTimes(response));
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


    //Inrerfaces

    public interface OnDoctorReceived{
        void onReceived(User user);
    }

    public interface OnDoctorTimeReceived{
        void onReceived(List<VisitTimeDoctor> visittimedoctor);
    }

    public interface OnDoctorLoginReceived{
        void onReceived(String checklogin);
    }

    public interface OnDoctorItemReceived{
        void onReceived(DoctorInfo doctorinfo);
    }

    public interface OnDoctorIllnessVisitTimeReceived{
        void onReceived(List<DoctorVisit> visittimeillnessdoctor);
    }

}
