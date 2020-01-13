package com.example.salamatapp.ServiceLayer.Converters;

import android.util.Log;

import com.example.salamatapp.DomainLayer.BaseMessage;
import com.example.salamatapp.DomainLayer.Bime;
import com.example.salamatapp.DomainLayer.Chat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatConverter {
    public List<BaseMessage> ConverChat(JSONArray response){
        Log.e("chat ",response.toString());
        List<BaseMessage> chats=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            BaseMessage chat=new BaseMessage();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                chat.setId(jsonObject.getInt("Id"));
                chat.setText(jsonObject.getString("Text"));
                chat.setType(jsonObject.getInt("TypeMassage"));
                Boolean isuser=jsonObject.getBoolean("UserDoctor");
                if (isuser==true){
                    chat.setSenderReciver(1);
                }else {
                    chat.setSenderReciver(0);
                }
                chats.add(chat);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return chats;
    }

    public List<BaseMessage> ConverChatJudge(JSONArray response){
        Log.e("chat judge",response.toString());
        List<BaseMessage> chats=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            BaseMessage chat=new BaseMessage();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                chat.setId(jsonObject.getInt("Id"));
                chat.setText(jsonObject.getString("Text"));
                chat.setType(jsonObject.getInt("TypeMassage"));
                Boolean isuser=jsonObject.getBoolean("UserJudge");
                if (isuser==true){
                    chat.setSenderReciver(1);
                }else {
                    chat.setSenderReciver(0);
                }
                chats.add(chat);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return chats;
    }


}
