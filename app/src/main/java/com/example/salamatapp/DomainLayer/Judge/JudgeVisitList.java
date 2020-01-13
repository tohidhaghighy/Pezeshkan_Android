package com.example.salamatapp.DomainLayer.Judge;

public class JudgeVisitList {
    private Integer Id;
    private Integer Cost;
    private String PayDatetime;
    private Integer IllnessId;
    private String Illnessname;
    private Boolean FinishAnswer;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCost() {
        return Cost;
    }

    public void setCost(Integer cost) {
        Cost = cost;
    }

    public String getPayDatetime() {
        return PayDatetime;
    }

    public void setPayDatetime(String payDatetime) {
        PayDatetime = payDatetime;
    }

    public Integer getIllnessId() {
        return IllnessId;
    }

    public void setIllnessId(Integer illnessId) {
        IllnessId = illnessId;
    }

    public String getIllnessname() {
        return Illnessname;
    }

    public void setIllnessname(String illnessname) {
        Illnessname = illnessname;
    }

    public Boolean getFinishAnswer() {
        return FinishAnswer;
    }

    public void setFinishAnswer(Boolean finishAnswer) {
        FinishAnswer = finishAnswer;
    }
}
