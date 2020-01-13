package com.example.salamatapp.DomainLayer;

public class User {
    private int Id;
    private String Name;
    private String Takhasos;
    private String City;
    private String Image;
    private String Description;
    private String VisitCost;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTakhasos() {
        return Takhasos;
    }

    public void setTakhasos(String takhasos) {
        Takhasos = takhasos;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getVisitCost() {
        return VisitCost;
    }

    public void setVisitCost(String visitCost) {
        VisitCost = visitCost;
    }
}
