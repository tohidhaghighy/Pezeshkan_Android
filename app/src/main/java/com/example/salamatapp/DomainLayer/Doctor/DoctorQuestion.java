package com.example.salamatapp.DomainLayer.Doctor;

public class DoctorQuestion {
    private Integer Id;
    private String Text;
    private Integer DoctorId;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Integer getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Integer doctorId) {
        DoctorId = doctorId;
    }
}
