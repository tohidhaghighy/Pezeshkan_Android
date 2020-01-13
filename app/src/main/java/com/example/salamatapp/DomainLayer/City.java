package com.example.salamatapp.DomainLayer;

public class City {
    private int Id;
    private String Name;
    private int StateId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStateId() {
        return StateId;
    }

    public void setStateId(int stateid) {
        StateId = stateid;
    }
}
