package com.example.salamatapp.DomainLayer;

public class Chat {
    private int Id;
    private String Text;
    private Boolean UserDoctor;
    private String OnlyUrlText;
    private Integer TypeMassage;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Boolean getUserDoctor() {
        return UserDoctor;
    }

    public void setUserDoctor(Boolean userDoctor) {
        UserDoctor = userDoctor;
    }

    public String getOnlyUrlText() {
        return OnlyUrlText;
    }

    public void setOnlyUrlText(String onlyUrlText) {
        OnlyUrlText = onlyUrlText;
    }

    public Integer getTypeMassage() {
        return TypeMassage;
    }

    public void setTypeMassage(Integer typeMassage) {
        TypeMassage = typeMassage;
    }
}
