package com.example.salamatapp.ServiceLayer;

import com.android.volley.toolbox.Volley;
import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.Doctor.DoctorZir;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.DomainLayer.VisitTimeDoctor;

import java.util.ArrayList;
import java.util.List;

public class DataFakeGenarator {
    public static List<User> getdata(){
        List<User> allpro=new ArrayList<>();
        for (int i=0;i<10;i++){
            User newuser=new User();
            newuser.setId(i);
            newuser.setName("توحید حقیقی "+i);
            newuser.setTakhasos("دکتر عمومی "+i);
            newuser.setCity(i+" ارومیه");
            newuser.setDescription("رکتد حقیقی در زمینه های مختلف در حال انجام فعالیت میباشر ");
            newuser.setVisitCost("14,000");
            newuser.setImage("https://4.img-dpreview.com/files/p/E~TS590x0~articles/3925134721/0266554465.jpeg");
            allpro.add(newuser);
        }

        return allpro;
    }

    public static List<Group> getdatagroup(){
        List<Group> allgroup=new ArrayList<>();
        for (int i=0;i<10;i++){
            Group newgroup=new Group();
            newgroup.setId(i);
            newgroup.setName("دندان پزشکی ");
            allgroup.add(newgroup);
        }

        return allgroup;
    }

    public static List<DoctorZir> getdatausers(){
        List<DoctorZir> allgroup=new ArrayList<>();
        for (int i=0;i<10;i++){
            DoctorZir newgroup=new DoctorZir();
            newgroup.setId(i);
            newgroup.setName("توحید حقیقی ");
            allgroup.add(newgroup);
        }

        return allgroup;
    }

    public static List<City> getdatacity(){
        List<City> allgroup=new ArrayList<>();
        for (int i=0;i<10;i++){
            City newcity=new City();
            newcity.setId(i);
            newcity.setName("ارومیه");
            newcity.setStateId(i);
            allgroup.add(newcity);
        }

        return allgroup;
    }

    public static List<VisitTimeDoctor> getdatatimevisit(){
        List<VisitTimeDoctor> allgroup=new ArrayList<>();
        for (int i=0;i<4;i++){
            VisitTimeDoctor newVisitTimeDoctor=new VisitTimeDoctor();
            newVisitTimeDoctor.setId(i);
            newVisitTimeDoctor.setDate("1395/01/09");
            newVisitTimeDoctor.setDescription("az saate 1 ta 5");
            allgroup.add(newVisitTimeDoctor);
        }

        return allgroup;
    }
}
