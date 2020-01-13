package com.example.salamatapp.ServiceLayer.Converters;

import com.example.salamatapp.DomainLayer.BaseMessage;
import com.example.salamatapp.DomainLayer.Judge.JudgeInfo;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MassageConverter {

    public List<BaseMessage> ConvertMassage(){
        List<BaseMessage> visits=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            BaseMessage visittime=new BaseMessage();
                JSONArray jsonArray=new JSONArray();
                visittime.setId(i);
                visittime.setName("tohid haghighi");
                if (i%2==1){
                    visittime.setSenderReciver(0);
                }
                else {
                    visittime.setSenderReciver(1);
                }
                visittime.setText("this is my text");
                visittime.setType(0);
                visits.add(visittime);

        }
        return visits;
    }
}
