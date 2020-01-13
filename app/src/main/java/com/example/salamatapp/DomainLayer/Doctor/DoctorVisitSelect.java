package com.example.salamatapp.DomainLayer.Doctor;

import android.content.Intent;

public class DoctorVisitSelect {
    private Integer Id;
    private String Date;
    private String EnglishDate;
    private String Description;
    private Integer Count;
    private Integer DoctorId;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getEnglishDate() {
        return EnglishDate;
    }

    public void setEnglishDate(String englishDate) {
        EnglishDate = englishDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Integer getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Integer doctorId) {
        DoctorId = doctorId;
    }
}
