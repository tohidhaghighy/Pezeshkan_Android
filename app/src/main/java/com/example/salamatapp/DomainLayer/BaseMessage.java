package com.example.salamatapp.DomainLayer;

public class BaseMessage {
    private Integer Id;
    private String Name;
    private String Text;
    // music=2 and image=1 and text=0
    private Integer Type;
    // sender=0 and reciver=1
    private Integer SenderReciver;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Integer getSenderReciver() {
        return SenderReciver;
    }

    public void setSenderReciver(Integer senderReciver) {
        SenderReciver = senderReciver;
    }
}
