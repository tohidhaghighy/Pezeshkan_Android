package com.example.salamatapp.ServiceLayer.Converters;

import android.util.Log;

import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.ListCity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CItyConverter {

    public List<City> ConverCity(JSONArray response){
        List<City> cities=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            City city=new City();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                city.setName(jsonObject.getString("Name"));
                city.setStateId(jsonObject.getInt("StateId"));
                city.setId(jsonObject.getInt("Id"));

                cities.add(city);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }


    public ListCity ConverCityString(JSONArray response){
        ListCity lc=new ListCity();
        String[] stcity_name=new String[response.length()];
        Integer[] stcity_id=new Integer[response.length()];
        Integer[] stcity_stateid=new Integer[response.length()];
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                stcity_name[i]=jsonObject.getString("Name");
                stcity_id[i]=jsonObject.getInt("Id");
                stcity_stateid[i]=jsonObject.getInt("StateId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        lc.setCityid(stcity_id);
        lc.setCityname(stcity_name);
        lc.setStateid(stcity_stateid);
        return lc;
    }

}
