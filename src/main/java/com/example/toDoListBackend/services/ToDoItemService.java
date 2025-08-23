package com.example.toDoListBackend.services;

import com.example.toDoListBackend.models.ToDoItem;

import java.util.List;

public interface ToDoItemService {

    List<ToDoItem> getAllTodoItems();
    List<ToDoItem> saveTodoItem(String toDoTitle);
    List<ToDoItem> deleteTodoItemById(Long id);
}