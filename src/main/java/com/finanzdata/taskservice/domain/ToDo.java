package com.finanzdata.taskservice.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "todo")
@JsonPropertyOrder({"id", "title", "description", "status", "completionDate", "subtasks"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    //@NotNull
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    //@NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "completion_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy") // Specify your desired date format
    private LocalDate completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ToDoStatus status;



    @Version
    @JsonIgnore
    private Long version;

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

    public ToDoStatus getStatus() {
        return status;
    }

    public void setStatus(ToDoStatus status) {
        this.status = status;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public ToDo(Long id, String title, String description, LocalDate completionDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completionDate = completionDate;
    }
// Constructors

    public ToDo() {}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo)) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(getId(), toDo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completionDate=" + completionDate +
                ", status=" + status +
                '}';
    }
}
