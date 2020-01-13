package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.Doctor.DoctorInfo;
import com.example.salamatapp.DomainLayer.Doctor.DoctorQuestion;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisit;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisitSelect;
import com.example.salamatapp.DomainLayer.Illness.Illness;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.DomainLayer.VisitTimeDoctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Doctor {


    public List<VisitTimeDoctor> ConverDoctortTimes(JSONArray response){
        List<VisitTimeDoctor> visits=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            VisitTimeDoctor visittime=new VisitTimeDoctor();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                visittime.setDate(jsonObject.getString("Date"));
                visittime.setId(jsonObject.getInt("Id"));
                visittime.setDescription(jsonObject.getString("Description"));
                visittime.setCountNobat(jsonObject.getString("CountNobat"));
                visits.add(visittime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return visits;
    }


    public DoctorInfo ConverDoctortInfo(JSONObject response){
        DoctorInfo doctorinfo=new DoctorInfo();
        try {

            doctorinfo.setName(response.getString("Name"));
            doctorinfo.setId(response.getInt("Id"));
            doctorinfo.setCost(response.getInt("Cost"));
            doctorinfo.setDescription(response.getString("Description"));
            doctorinfo.setImage(response.getString("Image"));
            doctorinfo.setNezamPezeshki(response.getString("NezamPezeshki"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctorinfo;

    }


    public List<DoctorVisit> ConverDoctorvisitTimes(JSONArray response){
        List<DoctorVisit> visits=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            DoctorVisit visittime=new DoctorVisit();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                visittime.setReserveDatetime(jsonObject.getString("ReserveDatetime"));
                visittime.setId(jsonObject.getInt("Id"));
                visittime.setTimeDayDoctorDes(jsonObject.getString("TimeDayDoctorDes"));
                visittime.setPeigiriCode(jsonObject.getString("PeigiriCode"));
                visittime.setIllnessname(jsonObject.getString("Illnessname"));
                visittime.setVisit(jsonObject.getBoolean("IsVisit"));
                visits.add(visittime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return visits;
    }

    public List<DoctorDrag> ConverDoctordrag(JSONArray response){
        List<DoctorDrag> drags=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            DoctorDrag drag=new DoctorDrag();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                drag.setName(jsonObject.getString("Name"));
                drag.setDescription(jsonObject.getString("Description"));
                drag.setId(jsonObject.getInt("Id"));
                drag.setDoctorId(jsonObject.getInt("DoctorId"));
                drags.add(drag);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return drags;
    }

    public List<DoctorQuestion> ConverDoctorquestion(JSONArray response){
        List<DoctorQuestion> allquestion=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            DoctorQuestion question=new DoctorQuestion();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                question.setText(jsonObject.getString("Text"));
                question.setId(jsonObject.getInt("Id"));
                allquestion.add(question);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return allquestion;
    }

    public List<DoctorVisitSelect> ConverDoctorvisitlist(JSONArray response){
        List<DoctorVisitSelect> allquestion=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            DoctorVisitSelect docvisit=new DoctorVisitSelect();
            try {

                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                docvisit.setDescription(jsonObject.getString("Description"));
                docvisit.setDate(jsonObject.getString("Date"));
                docvisit.setEnglishDate(jsonObject.getString("EnglishDate"));
                docvisit.setCount(jsonObject.getInt("CountNobat"));
                docvisit.setId(jsonObject.getInt("Id"));
                allquestion.add(docvisit);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return allquestion;
    }


}
