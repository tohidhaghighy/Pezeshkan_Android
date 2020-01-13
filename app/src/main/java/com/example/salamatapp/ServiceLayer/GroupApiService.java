package com.example.salamatapp.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.ListCity;
import com.example.salamatapp.DomainLayer.ListGroup;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ServiceLayer.Converters.CItyConverter;
import com.example.salamatapp.ServiceLayer.Converters.GroupConverter;
import com.example.salamatapp.ServiceLayer.Converters.UserConverter;

import org.json.JSONArray;

import java.util.List;

public class GroupApiService {
    private static final String TAG = "GroupApiService";
    private Context context;
    private GroupConverter _gc;


    public GroupApiService(Context context){
        this.context=context;
    }

    public void getDoctors(final GroupApiService.OnGroupsReceived onUsersReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetAllGroup", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _gc=new GroupConverter();
                onUsersReceived.onReceived(_gc.ConverGroup(response));
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

    public void getGroupsString(final GroupApiService.OnGroupsStringReceived onGroupsStringReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"DoctorWebService/GetAllGroup", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _gc=new GroupConverter();
                onGroupsStringReceived.onReceived(_gc.ConverGroupString(response));
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

    public interface OnGroupsReceived{
        void onReceived(List<Group> groups);
    }

    public interface OnGroupsStringReceived{
        void onReceived(ListGroup group);
    }
}
