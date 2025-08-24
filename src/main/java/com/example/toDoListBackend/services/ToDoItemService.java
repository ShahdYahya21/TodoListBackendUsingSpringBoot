package com.example.toDoListBackend.services;

import com.example.toDoListBackend.dtos.ToDoItemDTO;
import com.example.toDoListBackend.models.ToDoItem;

import java.util.List;

public interface ToDoItemService {

    List<ToDoItemDTO> getAllTodoItems();
    List<ToDoItemDTO> saveTodoItem(ToDoItemDTO toDoItemDTO);
    List<ToDoItemDTO> deleteTodoItemById(Long id);
    List<ToDoItemDTO> toggleCompletionStatusById(Long id);
    List<ToDoItemDTO> updateTodoItemTitle(ToDoItemDTO toDoItemDTO);
    List<ToDoItemDTO> getFilteredTodoItems(String toDoTitle);
}
