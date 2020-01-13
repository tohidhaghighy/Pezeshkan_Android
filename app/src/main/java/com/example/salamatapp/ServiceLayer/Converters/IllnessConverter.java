package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Illness.Illness;
import com.example.salamatapp.DomainLayer.Illness.IllnessJudge;
import com.example.salamatapp.DomainLayer.Illness.IllnessPharmacyList;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.Question;
import com.example.salamatapp.DomainLayer.VisitTimeDoctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IllnessConverter {

    public Illness ConverOneIllness(JSONObject response){
        Illness illness=new Illness();
        try {

            illness.setName(response.getString("Name"));
            illness.setId(response.getInt("Id"));
            illness.setAge(response.getInt("Age"));
            illness.setSuger(response.getInt("Suger"));
            illness.setPressure(response.getInt("PressureBlood"));
            illness.setInsuranceId(response.getInt("InsuranceId"));
            illness.setWeight(response.getInt("Weight"));
            illness.setInsuranceSerial(response.getString("Serialbime"));
            illness.setDate(response.getString("date"));
            illness.setImage(response.getString("Image"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return illness;
    }


    public List<IllnessVisitList> ConverVisitListIllness(JSONArray response){
        List<IllnessVisitList> visits=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            IllnessVisitList visittime=new IllnessVisitList();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                visittime.setDatetime(jsonObject.getString("Datetime"));
                visittime.setId(jsonObject.getInt("Id"));
                visittime.setDoctorId(jsonObject.getInt("DoctorId"));
                visittime.setPay(jsonObject.getBoolean("IsPay"));
                visittime.setVisit(jsonObject.getBoolean("IsVisit"));
                visittime.setNobat(jsonObject.getString("Nobat"));
                visittime.setDoctorname(jsonObject.getString("Doctorname"));
                visittime.setReserveDatetime(jsonObject.getString("ReserveDatetime"));
                visittime.setTimeDayDoctorDes(jsonObject.getString("TimeDayDoctorDes"));
                visits.add(visittime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return visits;
    }


    public List<IllnessJudge> ConverJudgeListIllness(JSONArray response){
        List<IllnessJudge> visits=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            IllnessJudge visittime=new IllnessJudge();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                visittime.setId(jsonObject.getInt("Id"));
                visittime.setCost(jsonObject.getInt("Cost"));
                visittime.setIllnessId(jsonObject.getInt("IllnessId"));
                visittime.setIllnessname(jsonObject.getString("Illnessname"));
                visittime.setFinishAnswer(jsonObject.getBoolean("FinishAnswer"));
                visits.add(visittime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return visits;
    }

    public List<IllnessPharmacyList> ConverPharmacyListIllness(JSONArray response){
        List<IllnessPharmacyList> visits=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            IllnessPharmacyList visittime=new IllnessPharmacyList();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                visittime.setPharmacyname(jsonObject.getString("Pharmacyname"));
                visittime.setId(jsonObject.getInt("Id"));
                visittime.setDoctorid(jsonObject.getInt("Doctorid"));
                visittime.setIllnessId(jsonObject.getInt("IllnessId"));
                visittime.setPharmacyId(jsonObject.getInt("PharmacyId"));
                visittime.setVisitId(jsonObject.getInt("VisitId"));
                visittime.setDescription(jsonObject.getString("Description"));
                visits.add(visittime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return visits;
    }


    public List<Question> GetAllQuestionList(JSONArray response){
        List<Question> questions=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Question question=new Question();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                question.setAnswer(jsonObject.getString("Answer"));
                question.setId(jsonObject.getInt("Id"));
                question.setText(jsonObject.getString("Text"));
                question.setAnswerId(jsonObject.getInt("AnswerId"));
                question.setHave_Answer(jsonObject.getBoolean("Have_Answer"));
                questions.add(question);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }


}
