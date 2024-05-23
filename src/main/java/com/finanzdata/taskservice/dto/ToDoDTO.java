package com.finanzdata.taskservice.dto;

import com.finanzdata.taskservice.domain.ToDoStatus;

import java.time.LocalDate;

public class ToDoDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate completionDate;



    public ToDoDTO() {}

    public ToDoDTO(Long id, String title, String description, LocalDate completionDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completionDate = completionDate;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }



    @Override
    public String toString() {
        return "ToDoDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completionDate=" + completionDate +
                '}';
    }
}
