package com.microservices.quiz_service.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;


    @ElementCollection // it is basically used to store the list of values in a separate table
    private List<Integer> questionIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }
}
