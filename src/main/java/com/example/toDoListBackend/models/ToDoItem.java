package com.example.toDoListBackend.models;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "TodoList")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskTitle;
    private Boolean completed;
    private Boolean markAsDeleted;
    private Boolean markAsUpdated;

    public ToDoItem() {
    }

    public ToDoItem(Long id, String taskTitle, Boolean completed, Boolean markAsDeleted, Boolean markAsUpdated) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.completed = completed;
        this.markAsDeleted = markAsDeleted;
        this.markAsUpdated = markAsUpdated;
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

    public Boolean getMarkAsDeleted() {
        return markAsDeleted;
    }

    public void setMarkAsDeleted(Boolean markAsDeleted) {
        this.markAsDeleted = markAsDeleted;
    }

    public Boolean getMarkAsUpdated() {
        return markAsUpdated;
    }

    public void setMarkAsUpdated(Boolean markAsUpdated) {
        this.markAsUpdated = markAsUpdated;
    }
}