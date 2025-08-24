package com.example.toDoListBackend.controllers;

import com.example.toDoListBackend.dtos.ToDoItemDTO;
import com.example.toDoListBackend.models.ToDoItem;
import com.example.toDoListBackend.services.ToDoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/Todo")
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoListController {

    @Autowired
    private ToDoItemService toDoItemService;


    @GetMapping("/getTodoItems")
    public ResponseEntity<List<ToDoItemDTO>> getTodoItems(){
        List<ToDoItemDTO> toDoItems = toDoItemService.getAllTodoItems();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }


    @PostMapping("/saveTodoItem")
    public ResponseEntity<List<ToDoItemDTO>> saveTodoItem(@RequestBody @Valid ToDoItemDTO toDoItemDTO) {
        List<ToDoItemDTO> toDoItems = toDoItemService.saveTodoItem(toDoItemDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toDoItems);
    }

    @DeleteMapping("/deleteTodoItem/{id}")
    public ResponseEntity<List<ToDoItemDTO>>  deleteTodoItem(@PathVariable Long id) {
        List<ToDoItemDTO> toDoItems = toDoItemService.deleteTodoItemById(id);
      return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoItems);
    }

    @PutMapping("/toggleCompletionStatus/{id}")
    public ResponseEntity<List<ToDoItemDTO>> toggleCompletionStatus(@PathVariable Long id) {
        List<ToDoItemDTO> toDoItems = toDoItemService.toggleCompletionStatusById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }

    @PutMapping("/updateTodoItem")
    public ResponseEntity<List<ToDoItemDTO>> updateTodoItem(@RequestBody @Valid ToDoItemDTO toDoItemDTO) {
        List<ToDoItemDTO> toDoItems = toDoItemService.updateTodoItemTitle(toDoItemDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }

    @GetMapping("/searchForTodoItem")
    public ResponseEntity<List<ToDoItemDTO>> searchForTodoItem(@RequestParam(required = false) String task) {
        if (task == null || task.trim().isEmpty()) {
            List<ToDoItemDTO> allToDoItems = toDoItemService.getAllTodoItems();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(allToDoItems);
        }

        List<ToDoItemDTO> toDoItems = toDoItemService.getFilteredTodoItems(task);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }

}





