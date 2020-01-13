package com.example.salamatapp.DomainLayer.Illness;

public class IllnessPharmacyList {
    private Integer Id;
    private Integer IllnessId;
    private String Pharmacyname;
    private Integer Doctorid;
    private Integer PharmacyId;
    private Integer VisitId;
    private String Description;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIllnessId() {
        return IllnessId;
    }

    public void setIllnessId(Integer illnessId) {
        IllnessId = illnessId;
    }

    public String getPharmacyname() {
        return Pharmacyname;
    }

    public void setPharmacyname(String pharmacyname) {
        Pharmacyname = pharmacyname;
    }

    public Integer getDoctorid() {
        return Doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        Doctorid = doctorid;
    }

    public Integer getPharmacyId() {
        return PharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        PharmacyId = pharmacyId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getVisitId() {
        return VisitId;
    }

    public void setVisitId(Integer visitId) {
        VisitId = visitId;
    }
}
