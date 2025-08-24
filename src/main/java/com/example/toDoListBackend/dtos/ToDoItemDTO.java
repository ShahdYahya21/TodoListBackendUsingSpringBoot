package com.example.toDoListBackend.dtos;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ToDoItemDTO {

    private Long id;

    @NotNull(message = "Task title cannot be null")
    @Size(min = 3, max = 255, message = "Task title must be between 3 and 255 characters")
    private String taskTitle;

    private Boolean completed;
    public ToDoItemDTO() {
    }
    public ToDoItemDTO(Long id, String taskTitle, Boolean completed) {
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
