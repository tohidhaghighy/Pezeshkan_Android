package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Bime;
import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.ListBime;
import com.example.salamatapp.DomainLayer.ListCity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BimeConverter {

    public List<Bime> ConverBime(JSONArray response){
        List<Bime> bimes=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Bime bime=new Bime();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                bime.setId(jsonObject.getInt("Id"));
                bime.setName(jsonObject.getString("Name"));

                bimes.add(bime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bimes;
    }


    public ListBime ConverBimeString(JSONArray response){
        ListBime lc=new ListBime();
        String[] stbime_name=new String[response.length()];
        Integer[] stbime_id=new Integer[response.length()];
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                stbime_name[i]=jsonObject.getString("Name");
                stbime_id[i]=jsonObject.getInt("Id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        lc.setBimeid(stbime_id);
        lc.setBimename(stbime_name);
        return lc;
    }
}
