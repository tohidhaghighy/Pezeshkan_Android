package com.example.salamatapp.DomainLayer.Illness;

public class Illness {
    private Integer Id;
    private String Name;
    private String InsuranceSerial;
    private Integer Age;
    private Integer InsuranceId;
    private Integer Pressure;
    private Integer Suger;
    private Integer Weight;
    private String Image;
    private String Date;

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

    public String getInsuranceSerial() {
        return InsuranceSerial;
    }

    public void setInsuranceSerial(String insuranceSerial) {
        InsuranceSerial = insuranceSerial;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Integer getInsuranceId() {
        return InsuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        InsuranceId = insuranceId;
    }

    public Integer getPressure() {
        return Pressure;
    }

    public void setPressure(Integer pressure) {
        Pressure = pressure;
    }

    public Integer getSuger() {
        return Suger;
    }

    public void setSuger(Integer suger) {
        Suger = suger;
    }

    public Integer getWeight() {
        return Weight;
    }

    public void setWeight(Integer weight) {
        Weight = weight;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
