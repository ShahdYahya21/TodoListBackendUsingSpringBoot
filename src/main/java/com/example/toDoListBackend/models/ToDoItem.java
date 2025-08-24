package com.example.toDoListBackend.models;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "todo_list")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskTitle;
    private Boolean completed;

    public ToDoItem() {
    }


    public ToDoItem(Long id, String taskTitle, Boolean completed) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}