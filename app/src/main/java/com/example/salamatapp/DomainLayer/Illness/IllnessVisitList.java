package com.example.salamatapp.DomainLayer.Illness;

public class IllnessVisitList {
    private int Id;
    private String Datetime;
    private Integer DoctorId;
    private String Doctorname;
    private Boolean IsPay;
    private Boolean IsVisit;
    private String Nobat;
    private String ReserveDatetime;
    private String TimeDayDoctorDes;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public Integer getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Integer doctorId) {
        DoctorId = doctorId;
    }

    public Boolean getPay() {
        return IsPay;
    }

    public void setPay(Boolean pay) {
        IsPay = pay;
    }

    public Boolean getVisit() {
        return IsVisit;
    }

    public void setVisit(Boolean visit) {
        IsVisit = visit;
    }

    public String getNobat() {
        return Nobat;
    }

    public void setNobat(String nobat) {
        Nobat = nobat;
    }

    public String getReserveDatetime() {
        return ReserveDatetime;
    }

    public void setReserveDatetime(String reserveDatetime) {
        ReserveDatetime = reserveDatetime;
    }

    public String getTimeDayDoctorDes() {
        return TimeDayDoctorDes;
    }

    public void setTimeDayDoctorDes(String timeDayDoctorDes) {
        TimeDayDoctorDes = timeDayDoctorDes;
    }

    public String getDoctorname() {
        return Doctorname;
    }

    public void setDoctorname(String doctorname) {
        Doctorname = doctorname;
    }
}
