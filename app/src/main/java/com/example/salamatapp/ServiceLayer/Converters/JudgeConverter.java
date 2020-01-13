package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.Illness.Illness;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.Judge.JudgeInfo;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JudgeConverter {

    public JudgeInfo ConverOneJudge(JSONObject response){
        JudgeInfo judge=new JudgeInfo();
        try {

            judge.setName(response.getString("Name"));
            judge.setId(response.getInt("Id"));
            judge.setDescription(response.getString("Description"));
            judge.setImage(response.getString("Image"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return judge;
    }


    public List<JudgeVisitList> ConverVisitListJudge(JSONArray response){
        List<JudgeVisitList> visits=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            JudgeVisitList visittime=new JudgeVisitList();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                visittime.setId(jsonObject.getInt("Id"));
                visittime.setCost(jsonObject.getInt("Cost"));
                visittime.setPayDatetime(jsonObject.getString("PayDatetime"));
                visittime.setIllnessname(jsonObject.getString("Illnessname"));
                visittime.setIllnessId(jsonObject.getInt("IllnessId"));
                visittime.setFinishAnswer(jsonObject.getBoolean("FinishAnswer"));
                visits.add(visittime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return visits;
    }


}
