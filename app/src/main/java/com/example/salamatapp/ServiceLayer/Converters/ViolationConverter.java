package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Bime;
import com.example.salamatapp.DomainLayer.Violation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViolationConverter {

    public List<Violation> ConverViolation(JSONArray response){
        List<Violation> violations=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Violation violation=new Violation();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                violation.setId(jsonObject.getInt("Id"));
                violation.setNumber(jsonObject.getString("Number"));
                violation.setDescriotion(jsonObject.getString("IllnessDescription"));
                violation.setAnswerAdmin(jsonObject.getString("AdminAnswer"));

                violations.add(violation);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return violations;
    }

}
