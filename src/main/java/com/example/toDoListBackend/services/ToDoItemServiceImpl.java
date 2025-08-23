package com.example.toDoListBackend.services;

import com.example.toDoListBackend.models.ToDoItem;
import com.example.toDoListBackend.repositories.ToDoItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// Spring annotation to indicate that this class is a Spring-managed component
@Component
// Lombok annotation to automatically generate a logger instance named "log"
@Slf4j
public class ToDoItemServiceImpl implements ToDoItemService {

    // Autowired annotation to inject the UserRepository dependency
    @Autowired
    private ToDoItemRepository toDoItemRepository;


    @Override
    public List<ToDoItem> getAllTodoItems() {
        return toDoItemRepository.findAll();
    }

    @Override
    public List<ToDoItem> saveTodoItem(String toDoTitle) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTaskTitle(toDoTitle);
        toDoItem.setCompleted(false);
        toDoItem.setMarkAsDeleted(false);
        toDoItem.setMarkAsUpdated(false);
        toDoItemRepository.save(toDoItem);
        return toDoItemRepository.findAll();

    }

    @Override
    public List<ToDoItem> deleteTodoItemById(Long id) {
        if(toDoItemRepository.existsById(id)) {
            toDoItemRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Todo item with ID " + id + " not found");
        }
        return toDoItemRepository.findAll();

    }
}