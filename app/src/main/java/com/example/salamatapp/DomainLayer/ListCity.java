package com.example.salamatapp.DomainLayer;

public class ListCity {
    private String[] cityname;
    private Integer[] cityid;
    private Integer[] stateid;

    public String[] getCityname() {
        return cityname;
    }

    public void setCityname(String[] cityname) {
        this.cityname = cityname;
    }

    public Integer[] getCityid() {
        return cityid;
    }

    public void setCityid(Integer[] cityid) {
        this.cityid = cityid;
    }

    public Integer[] getStateid() {
        return stateid;
    }

    public void setStateid(Integer[] stateid) {
        this.stateid = stateid;
    }
}
