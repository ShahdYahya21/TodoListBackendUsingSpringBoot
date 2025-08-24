package com.example.toDoListBackend.controllers;

import com.example.toDoListBackend.models.ToDoItem;
import com.example.toDoListBackend.services.ToDoItemService;
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
    public ResponseEntity<List<ToDoItem>> getTodoItems(){
        List<ToDoItem> toDoItems = toDoItemService.getAllTodoItems();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }


    @PostMapping("/saveTodoItem")
    public ResponseEntity<List<ToDoItem>> saveTodoItem(@RequestBody String toDoTitle) {
        List<ToDoItem> toDoItems = toDoItemService.saveTodoItem(toDoTitle);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toDoItems);
    }

    @DeleteMapping("/deleteTodoItem/{id}")
    public ResponseEntity<List<ToDoItem>>  deleteTodoItem(@PathVariable Long id) {
        List<ToDoItem> toDoItems = toDoItemService.deleteTodoItemById(id);
      return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoItems);
    }

    @PutMapping("/toggleCompletionStatus/{id}")
    public ResponseEntity<List<ToDoItem>> toggleCompletionStatus(@PathVariable Long id) {
        List<ToDoItem> toDoItems = toDoItemService.toggleCompletionStatusById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }

    @PutMapping("/updateTodoItem/{id}")
    public ResponseEntity<List<ToDoItem>> updateTodoItem(@PathVariable Long id ,@RequestBody String toDoTitle) {
        List<ToDoItem> toDoItems = toDoItemService.updateTodoItemTitle(id, toDoTitle);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }

    @GetMapping("/searchForTodoItem")
    public ResponseEntity<List<ToDoItem>> searchForTodoItem(@RequestParam(required = false) String task) {
        if (task == null || task.trim().isEmpty()) {
            List<ToDoItem> allToDoItems = toDoItemService.getAllTodoItems();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(allToDoItems);
        }

        List<ToDoItem> toDoItems = toDoItemService.getFilteredTodoItems(task);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoItems);
    }

}





