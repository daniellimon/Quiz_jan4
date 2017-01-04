package com.quiz.model;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable{

    String question;
    int answer;
    List<String> listOptions;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public List<String> getListOptions() {
        return listOptions;
    }

    public void setListOptions(List<String> listOptions) {
        this.listOptions = listOptions;
    }
}
