package com.example.salamatapp.DomainLayer;

public class Question {
    private Integer Id;
    private String Answer;
    private String Text;
    private Integer AnswerId;
    private Boolean Have_Answer;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Integer getAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(Integer answerId) {
        AnswerId = answerId;
    }

    public Boolean getHave_Answer() {
        return Have_Answer;
    }

    public void setHave_Answer(Boolean have_Answer) {
        Have_Answer = have_Answer;
    }
}
