package com.example.salamatapp.DomainLayer.Pharmacy;

public class PharmacyVisitList {
    private Integer DoctorId;
    private String DoctorName;
    private Integer VisitId;
    private Integer IllnessId;
    private String IllnessName;


    public Integer getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Integer doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public Integer getVisitId() {
        return VisitId;
    }

    public void setVisitId(Integer visitId) {
        VisitId = visitId;
    }

    public Integer getIllnessId() {
        return IllnessId;
    }

    public void setIllnessId(Integer illnessId) {
        IllnessId = illnessId;
    }

    public String getIllnessName() {
        return IllnessName;
    }

    public void setIllnessName(String illnessName) {
        IllnessName = illnessName;
    }
}
