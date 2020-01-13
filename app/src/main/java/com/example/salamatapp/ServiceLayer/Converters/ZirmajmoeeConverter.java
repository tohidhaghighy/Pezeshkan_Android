package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Doctor.DoctorZir;
import com.example.salamatapp.DomainLayer.Violation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZirmajmoeeConverter {

    public List<DoctorZir> ConverDoctorZir(JSONArray response){
        List<DoctorZir> alldoctor=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            DoctorZir doctor=new DoctorZir();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                doctor.setId(jsonObject.getInt("Id"));
                doctor.setName(jsonObject.getString("Userid"));

                alldoctor.add(doctor);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return alldoctor;
    }

}
