package com.example.toDoListBackend.services;

import com.example.toDoListBackend.dtos.ToDoItemDTO;
import com.example.toDoListBackend.models.ToDoItem;
import com.example.toDoListBackend.repositories.ToDoItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@Slf4j
public class ToDoItemServiceImpl implements ToDoItemService {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ToDoItemDTO> getAllTodoItems() {
        List<ToDoItem> toDoItems = toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")));
        return toDoItems.stream()
                .map(toDoItem -> modelMapper.map(toDoItem, ToDoItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ToDoItemDTO> saveTodoItem(ToDoItemDTO toDoItemDTO) {
        ToDoItem toDoItem = modelMapper.map(toDoItemDTO, ToDoItem.class);
        toDoItem = toDoItemRepository.save(toDoItem);
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> deleteTodoItemById(Long id) {
        if (toDoItemRepository.existsById(id)) {
            toDoItemRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Todo item with ID " + id + " not found");
        }
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> toggleCompletionStatusById(Long id) {
        ToDoItem todoItem = toDoItemRepository.findById(id).orElseThrow(() ->
                new RuntimeException("ToDoItem with id " + id + " not found."));
        todoItem.setCompleted(!todoItem.getCompleted());
        toDoItemRepository.save(todoItem);
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> updateTodoItemTitle(ToDoItemDTO toDoItemDTO) {
        ToDoItem toDoItem = toDoItemRepository.findById(toDoItemDTO.getId()).orElseThrow(() ->
                new RuntimeException("ToDoItem with id " + toDoItemDTO.getId() + " not found."));
        toDoItem.setTaskTitle(toDoItemDTO.getTaskTitle());
        toDoItemRepository.save(toDoItem);
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> getFilteredTodoItems(String toDoTitle) {
        List<ToDoItem> toDoItems = toDoItemRepository.findByTaskTitleStartingWith(toDoTitle.trim().toLowerCase());
        return toDoItems.stream()
                .map(toDoItem -> modelMapper.map(toDoItem, ToDoItemDTO.class))
                .collect(Collectors.toList());
    }
}
