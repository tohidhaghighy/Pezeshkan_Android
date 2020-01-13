package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.NoskheInfo;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyInfo;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyVisitList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoskheConverter {

    public NoskheInfo ConverOneNoskhe(JSONObject response){
        NoskheInfo noskhe=new NoskheInfo();
        try {

            noskhe.setIllnessimage(response.getString("Illnessimage"));
            noskhe.setId(response.getInt("Id"));
            noskhe.setIllnessname(response.getString("Illnessname"));
            noskhe.setDoctorimage(response.getString("Doctorimage"));
            noskhe.setDoctorname(response.getString("Doctorname"));
            noskhe.setCodeBime(response.getString("CodeBime"));
            noskhe.setTypeBime(response.getString("TypeBime"));
            noskhe.setDatevisit(response.getString("Datevisit"));
            noskhe.setPeigiriCode("کد پیگیری : "+response.getString("PeigiriCode"));
            noskhe.setDoctorNezamPezeshki("نظام پزشکی : "+response.getString("DoctorNezamPezeshki"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return noskhe;
    }


    public List<NoskheList> ConverListNoskhe(JSONArray response){
        List<NoskheList> noskhelist=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            NoskheList noskhe=new NoskheList();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                noskhe.setId(jsonObject.getInt("Id"));
                noskhe.setName(jsonObject.getString("Name"));
                noskhe.setCount(jsonObject.getInt("Count"));
                noskhelist.add(noskhe);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return noskhelist;
    }

}
