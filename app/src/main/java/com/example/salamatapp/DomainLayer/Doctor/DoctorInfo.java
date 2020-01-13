package com.example.salamatapp.DomainLayer.Doctor;

public class DoctorInfo {
    private Integer Id;
    private String Name;
    private String Description;
    private Integer Cost;
    private String Image;
    private String NezamPezeshki;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getCost() {
        return Cost;
    }

    public void setCost(Integer cost) {
        Cost = cost;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getNezamPezeshki() {
        return NezamPezeshki;
    }

    public void setNezamPezeshki(String nezamPezeshki) {
        NezamPezeshki = nezamPezeshki;
    }
}
