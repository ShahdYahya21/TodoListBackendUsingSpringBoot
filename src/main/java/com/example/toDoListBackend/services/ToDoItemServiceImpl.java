package com.example.toDoListBackend.services;

import com.example.toDoListBackend.models.ToDoItem;
import com.example.toDoListBackend.repositories.ToDoItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ToDoItemServiceImpl implements ToDoItemService {

    @Autowired
    private ToDoItemRepository toDoItemRepository;


    @Override
    public List<ToDoItem> getAllTodoItems() {
        return toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    @Override
    public List<ToDoItem> saveTodoItem(String toDoTitle) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTaskTitle(toDoTitle);
        toDoItem.setCompleted(false);
        toDoItemRepository.save(toDoItem);
        return toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")));

    }

    @Override
    public List<ToDoItem> deleteTodoItemById(Long id) {
        if(toDoItemRepository.existsById(id)) {
            toDoItemRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Todo item with ID " + id + " not found");
        }
        return toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")));

    }

    @Override
    public List<ToDoItem> toggleCompletionStatusById(Long id) {
        ToDoItem todoItem = toDoItemRepository.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setCompleted(!todoItem.getCompleted());
            toDoItemRepository.save(todoItem);
            return toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")));  // Ensure order by id
        } else {
            throw new RuntimeException("ToDoItem with id " + id + " not found.");
        }
    }

    public List<ToDoItem> updateTodoItemTitle(Long id, String title) {
        ToDoItem toDoItem = toDoItemRepository.findById(id).orElse(null);

        if (toDoItem == null) {
            throw new RuntimeException("ToDoItem with id " + id + " not found.");
        }
        toDoItem.setTaskTitle(title);
        toDoItemRepository.save(toDoItem);
        return toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    @Override
    public List<ToDoItem> getFilteredTodoItems(String toDoTitle) {
        return toDoItemRepository.findByTaskTitleStartingWith(toDoTitle.trim().toLowerCase());

    }
}



