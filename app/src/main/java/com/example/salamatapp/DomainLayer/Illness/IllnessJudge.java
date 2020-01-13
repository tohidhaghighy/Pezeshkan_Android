package com.example.salamatapp.DomainLayer.Illness;

public class IllnessJudge {
    private Integer Id;
    private Integer Cost;
    private String PayDateTime;
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

    public String getPayDateTime() {
        return PayDateTime;
    }

    public void setPayDateTime(String payDateTime) {
        PayDateTime = payDateTime;
    }

    public Integer getIllnessId() {
        return IllnessId;
    }

    public void setIllnessId(Integer illnessId) {
        IllnessId = illnessId;
    }

    public Boolean getFinishAnswer() {
        return FinishAnswer;
    }

    public void setFinishAnswer(Boolean finishAnswer) {
        FinishAnswer = finishAnswer;
    }

    public String getIllnessname() {
        return Illnessname;
    }

    public void setIllnessname(String illnessname) {
        Illnessname = illnessname;
    }
}
