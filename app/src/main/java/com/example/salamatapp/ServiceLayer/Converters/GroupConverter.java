package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.ListBime;
import com.example.salamatapp.DomainLayer.ListGroup;
import com.example.salamatapp.DomainLayer.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupConverter {
    public List<Group> ConverGroup(JSONArray response){
        List<Group> groups=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Group group=new Group();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                group.setName(jsonObject.getString("Name"));
                group.setId(jsonObject.getInt("Id"));

                groups.add(group);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return groups;
    }

    public ListGroup ConverGroupString(JSONArray response){
        ListGroup lc=new ListGroup();
        String[] stgroup_name=new String[response.length()];
        Integer[] stgroup_id=new Integer[response.length()];
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                stgroup_name[i]=jsonObject.getString("Name");
                stgroup_id[i]=jsonObject.getInt("Id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        lc.setGroupid(stgroup_id);
        lc.setGroupname(stgroup_name);
        return lc;
    }
}
