package com.jbesic.components;

public class JBAnswer {

    private int Id;
    private int QuestionId;
    private String Answer;
    private int UserId;
    private String ComponentId;

    public int getId() {
        return this.Id;
    }

    public JBAnswer setId(int id) {
        this.Id = id;
        return this;
    }

    public String getComponentId() {
        return this.ComponentId;
    }

    public JBAnswer setComponentId(String componentId) {
        this.ComponentId = componentId;
        return this;
    }

    public int getQuestionId() {
        return this.QuestionId;
    }

    public JBAnswer setQuestionId(int questionId) {
        this.QuestionId = questionId;
        return this;
    }

    public String getAnswer() {
        return this.Answer;
    }

    public JBAnswer setAnswer(String answer) {
        this.Answer = answer;
        return this;
    }

    public int getUserId() {
        return this.UserId;
    }

    public JBAnswer setUserId(int UserId) {
        this.UserId = UserId;
        return this;
    }
}
