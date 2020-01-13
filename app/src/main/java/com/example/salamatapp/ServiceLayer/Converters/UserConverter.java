package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public List<User> ConverDoctortUser(JSONArray response){
        List<User> users=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            User user=new User();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                user.setName(jsonObject.getString("Name"));
                user.setId(jsonObject.getInt("Id"));
                user.setDescription(jsonObject.getString("Description"));
                user.setVisitCost(jsonObject.getString("Cost"));
                user.setTakhasos(jsonObject.getString("Takhasos"));
                user.setCity(jsonObject.getString("City"));
                user.setImage(jsonObject.getString("Image"));
                users.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public User ConverOneDoctortUser(JSONObject response){
            User user=new User();
            try {

                user.setName(response.getString("Name"));
                user.setId(response.getInt("Id"));
                user.setDescription(response.getString("Description"));
                user.setVisitCost(response.getString("Cost"));
                user.setTakhasos(response.getString("Takhasos"));
                user.setCity(response.getString("City"));
                user.setImage(response.getString("Image"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return user;
    }

    public List<User> ConverPharmacytUser(JSONArray response){
        List<User> users=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            User user=new User();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                user.setName(jsonObject.getString("Name"));
                user.setId(jsonObject.getInt("Id"));
                user.setDescription(jsonObject.getString("Address"));
                user.setCity(jsonObject.getString("City"));
                user.setImage(jsonObject.getString("Image"));
                users.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }


    public List<User> ConverJudgetUser(JSONArray response){
        List<User> users=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            User user=new User();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                user.setName(jsonObject.getString("Name"));
                user.setId(jsonObject.getInt("Id"));
                user.setDescription(jsonObject.getString("Description"));
                user.setImage(jsonObject.getString("Image"));
                user.setCity("");
                users.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
