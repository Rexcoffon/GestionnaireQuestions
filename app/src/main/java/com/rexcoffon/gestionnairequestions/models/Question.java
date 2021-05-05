package com.rexcoffon.gestionnairequestions.models;

public class Question {

    public Question(String id, String question, String reponce) {
        this.id = id;
        this.question = question;
        this.reponce = reponce;
    }

    private String id;
    private String question;
    private String reponce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponce() {
        return reponce;
    }

    public void setReponce(String reponce) {
        this.reponce = reponce;
    }
}
