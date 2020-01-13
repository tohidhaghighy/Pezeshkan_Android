package com.example.salamatapp.DomainLayer;

public class Violation {
    private Integer Id;
    private String Number;
    private String Descriotion;
    private String AnswerAdmin;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getDescriotion() {
        return Descriotion;
    }

    public void setDescriotion(String descriotion) {
        Descriotion = descriotion;
    }

    public String getAnswerAdmin() {
        return AnswerAdmin;
    }

    public void setAnswerAdmin(String answerAdmin) {
        AnswerAdmin = answerAdmin;
    }
}
