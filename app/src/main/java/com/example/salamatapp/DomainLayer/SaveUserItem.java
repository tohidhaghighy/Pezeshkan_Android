package com.example.salamatapp.DomainLayer;

public class SaveUserItem {
    private String Name;
    private String Image;
    private String Number;
    private String Password;
    private boolean Is_Active;
    private String UserId;
    private Integer Type;
    private Integer FirstTime;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isIs_Active() {
        return Is_Active;
    }

    public void setIs_Active(boolean is_Active) {
        Is_Active = is_Active;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Integer getFirstTime() {
        return FirstTime;
    }

    public void setFirstTime(Integer firstTime) {
        FirstTime = firstTime;
    }
}
