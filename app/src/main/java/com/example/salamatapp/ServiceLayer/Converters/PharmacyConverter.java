package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Judge.JudgeInfo;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyInfo;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyVisitList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PharmacyConverter {


    public PharmacyInfo ConverOnePharmacy(JSONObject response){
        PharmacyInfo pharmacy=new PharmacyInfo();
        try {

            pharmacy.setName(response.getString("Name"));
            pharmacy.setId(response.getInt("Id"));
            pharmacy.setDescription(response.getString("Description"));
            pharmacy.setImage(response.getString("Image"));
            pharmacy.setAddress(response.getString("Address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pharmacy;
    }

    public List<PharmacyVisitList> ConverVisitListPharmacy(JSONArray response){
        List<PharmacyVisitList> visits=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            PharmacyVisitList visittime=new PharmacyVisitList();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                visittime.setDoctorId(jsonObject.getInt("DoctorId"));
                visittime.setDoctorName(jsonObject.getString("DoctorName"));
                visittime.setVisitId(jsonObject.getInt("VisitId"));
                visittime.setIllnessId(jsonObject.getInt("IllnessId"));
                visittime.setIllnessName(jsonObject.getString("IllnessName"));
                visits.add(visittime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return visits;
    }


}
