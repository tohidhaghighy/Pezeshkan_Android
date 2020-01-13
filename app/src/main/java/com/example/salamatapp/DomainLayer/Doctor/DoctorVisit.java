package com.example.salamatapp.DomainLayer.Doctor;

public class DoctorVisit {
    private Integer Id;
    private Integer DoctorId;
    private String Datetime;
    private Boolean IsVisit;
    private Integer Nobat;
    private String Doctorname;
    private String ReserveDatetime;
    private String TimeDayDoctorDes;
    private String Illnessname;
    private String PeigiriCode;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Integer doctorId) {
        DoctorId = doctorId;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public Boolean getVisit() {
        return IsVisit;
    }

    public void setVisit(Boolean visit) {
        IsVisit = visit;
    }

    public Integer getNobat() {
        return Nobat;
    }

    public void setNobat(Integer nobat) {
        Nobat = nobat;
    }

    public String getDoctorname() {
        return Doctorname;
    }

    public void setDoctorname(String doctorname) {
        Doctorname = doctorname;
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

    public String getIllnessname() {
        return Illnessname;
    }

    public void setIllnessname(String illnessname) {
        Illnessname = illnessname;
    }

    public String getPeigiriCode() {
        return PeigiriCode;
    }

    public void setPeigiriCode(String peigiriCode) {
        PeigiriCode = peigiriCode;
    }
}
