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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.BaseMessage;
import com.example.salamatapp.DomainLayer.Bime;
import com.example.salamatapp.DomainLayer.Chat;
import com.example.salamatapp.ServiceLayer.Converters.BimeConverter;
import com.example.salamatapp.ServiceLayer.Converters.ChatConverter;
import com.example.salamatapp.ServiceLayer.Upload.Uploadimage;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatApiService {

    private static final String TAG = "ChatApiService";
    private Context context;

    private ChatConverter _chatconvert;

    public ChatApiService(Context context){
        this.context=context;
    }

    public String Upload(Bitmap bitmap){
        ByteArrayOutputStream by=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,by);
        byte bytes[]=by.toByteArray();
        String strimg= Base64.encodeToString(bytes,Base64.DEFAULT);
        return  strimg;
    }


    public void getIllnessChats(Integer visitid,final ChatApiService.OnChatsReceived onChatsReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"AndroidChatroom/GetIllnessMassage?visitid="+visitid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _chatconvert=new ChatConverter();
                onChatsReceived.onReceived(_chatconvert.ConverChat(response));
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

    public void getjudgeChats(int visitid,final ChatApiService.OnChatsReceived onChatsReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, UrlService.Url+"AndroidChatroom/GetJudgeMassage?visitid="+visitid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                _chatconvert=new ChatConverter();
                Log.e("test",response.toString());
                onChatsReceived.onReceived(_chatconvert.ConverChatJudge(response));
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




    public void postillnessmassage(Integer visitid,String userid,String text,Boolean isuser,Integer type,final ChatApiService.OnSendMassageReceived onChatsReceived){
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"AndroidChatroom/PostNewIllnessMassage?visitid="+visitid+"&userid="+userid+"&text="+text+"&isuser="+isuser+"&type="+type, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onChatsReceived.onReceived(response);
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

    public void postjudgemassage(Integer visitid,String userid,String text,Boolean isuser,Integer type,final ChatApiService.OnSendMassageReceived onChatsReceived){
        Log.e(TAG, UrlService.Url+"AndroidChatroom/PostNewJudgeMassage?visitid="+visitid+"&userid="+userid+"&text="+text+"&isuser="+isuser+"&type="+type);
        StringRequest request=new StringRequest(Request.Method.POST, UrlService.Url+"AndroidChatroom/PostNewJudgeMassage?visitid="+visitid+"&userid="+userid+"&text="+text+"&isuser="+isuser+"&type="+type, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG,response);
                onChatsReceived.onReceived(response);
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

    public void sendimageillnessmassage(final Integer visitid, final Bitmap bitmapimage, final String userid, String text, final Boolean isuser, final ChatApiService.OnSendMassageReceived onChatsReceived){

        StringRequest stringrequest=new StringRequest(Request.Method.POST, UrlService.Url+"AndroidChatroom/PostNewIllnessMassage", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onChatsReceived.onReceived(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                //?visitid="+visitid+"&userid="+userid+"&text="+text+"&isuser="+isuser+"&type="+type
                HashMap<String,String> hashmap=new HashMap<String,String>();
                String textimage=Upload(bitmapimage);
                hashmap.put("visitid",visitid.toString());
                hashmap.put("userid",userid);
                hashmap.put("text",textimage);
                hashmap.put("isuser",isuser.toString());
                hashmap.put("type","1");
                return hashmap;
            }
        };

        stringrequest.setRetryPolicy(new DefaultRetryPolicy(180000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(stringrequest);
    }

    public void sendimagejudgesmassage(final Integer visitid, final Bitmap bitmapimage, final String userid, String text, final Boolean isuser, final ChatApiService.OnSendMassageReceived onChatsReceived){

        StringRequest stringrequest=new StringRequest(Request.Method.POST, UrlService.Url+"AndroidChatroom/PostNewJudgeMassage", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onChatsReceived.onReceived(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                //?visitid="+visitid+"&userid="+userid+"&text="+text+"&isuser="+isuser+"&type="+type
                HashMap<String,String> hashmap=new HashMap<String,String>();
                String textimage=Upload(bitmapimage);
                hashmap.put("visitid",visitid.toString());
                hashmap.put("userid",userid);
                hashmap.put("text",textimage);
                hashmap.put("isuser",isuser.toString());
                hashmap.put("type","1");
                return hashmap;
            }
        };

        stringrequest.setRetryPolicy(new DefaultRetryPolicy(180000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(stringrequest);
    }


    public interface OnChatsReceived{
        void onReceived(List<BaseMessage> chats);
    }

    public interface OnSendMassageReceived{
        void onReceived(String Sended);
    }

}
