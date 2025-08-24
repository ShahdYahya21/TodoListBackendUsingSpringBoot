package com.example.toDoListBackend.repositories;

import com.example.toDoListBackend.models.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    @Query("SELECT t FROM ToDoItem t WHERE LOWER(t.taskTitle) LIKE LOWER(CONCAT(:taskTitle, '%'))")
    List<ToDoItem> findByTaskTitleStartingWith(@Param("taskTitle") String taskTitle);

}