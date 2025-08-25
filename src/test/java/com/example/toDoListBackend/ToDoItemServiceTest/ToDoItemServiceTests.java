package com.example.toDoListBackend.ToDoItemServiceTest;

import com.example.toDoListBackend.dtos.ToDoItemDTO;
import com.example.toDoListBackend.models.ToDoItem;
import com.example.toDoListBackend.repositories.ToDoItemRepository;
import com.example.toDoListBackend.services.ToDoItemServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ToDoItemServiceTests {

    @Mock
    private ToDoItemRepository toDoItemRepository;

    @Mock
    private ModelMapper modelMapper;


    @InjectMocks
    private ToDoItemServiceImpl toDoItemService;

    private static final String TEST_TASK_TITLE = "Test Task";
    private static final Long TEST_ID = 12L;
    private ToDoItemDTO toDoItemDTO;
    private ToDoItem toDoItem;

    @BeforeEach
    void setUp() {
        toDoItemDTO = ToDoItemDTO.builder().id(TEST_ID).taskTitle(TEST_TASK_TITLE).completed(false).build();
        toDoItem = new ToDoItem(TEST_ID, TEST_TASK_TITLE, false);
    }

    @Test
    public void getAllTodoItemsSuccess() {
        when(toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")))).thenReturn(Arrays.asList(toDoItem));
        when(modelMapper.map(toDoItem, ToDoItemDTO.class)).thenReturn(toDoItemDTO);

        List<ToDoItemDTO> response = toDoItemService.getAllTodoItems();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Test Task", response.get(0).getTaskTitle());
        verify(toDoItemRepository, times(1)).findAll(Sort.by(Sort.Order.asc("id")));
        verify(modelMapper, times(1)).map(toDoItem, ToDoItemDTO.class);
    }

//    @Test
//    public void saveTodoItemSuccess() {
//        ToDoItemDTO toDoItemDTO = ToDoItemDTO.builder().id(TEST_ID).taskTitle(TEST_TASK_TITLE).completed(false).build();
//        ToDoItem toDoItem = new ToDoItem(TEST_ID, TEST_TASK_TITLE, false);
//
//        when(toDoItemRepository.save(any(ToDoItem.class))).thenReturn(toDoItem);
//
//        when(modelMapper.map(toDoItem, ToDoItemDTO.class)).thenReturn(toDoItemDTO);
//
//        List<ToDoItemDTO> response = toDoItemService.saveTodoItem(toDoItemDTO);
//
//        assertNotNull(response);
//        assertEquals(1, response.size());
//        assertEquals(TEST_TASK_TITLE, response.get(0).getTaskTitle());
//
//        verify(toDoItemRepository, times(1)).save(any(ToDoItem.class));
//        verify(modelMapper, times(1)).map(toDoItem, ToDoItemDTO.class);
//    }

    @Test
    public void deleteTodoItemByIdSuccess() {
        when(toDoItemRepository.existsById(TEST_ID)).thenReturn(true);
        doNothing().when(toDoItemRepository).deleteById(TEST_ID);

        List<ToDoItemDTO> response = toDoItemService.deleteTodoItemById(TEST_ID);

        assertNotNull(response);
        assertEquals(0, response.size());

        verify(toDoItemRepository, times(1)).deleteById(TEST_ID);
    }

//    @Test
//    public void updateTodoItemTitleSuccess() {
//        ToDoItem originalItem = new ToDoItem(TEST_ID, "Old Task", false);
//        ToDoItem updatedItem = new ToDoItem(TEST_ID, "Updated Task", false);
//        ToDoItemDTO toDoItemDTO = new ToDoItemDTO(TEST_ID, "Updated Task", false);
//
//        when(toDoItemRepository.findById(TEST_ID)).thenReturn(Optional.of(originalItem));
//        when(toDoItemRepository.save(any(ToDoItem.class))).thenReturn(updatedItem);
//
//        when(modelMapper.map(updatedItem, ToDoItemDTO.class)).thenReturn(toDoItemDTO);
//
//        List<ToDoItemDTO> response = toDoItemService.updateTodoItemTitle(toDoItemDTO);
//
//        assertNotNull(response);
//        assertEquals(1, response.size());
//        assertEquals("Updated Task", response.get(0).getTaskTitle());
//
//        verify(toDoItemRepository, times(1)).findById(TEST_ID);
//        verify(toDoItemRepository, times(1)).save(any(ToDoItem.class));
//        verify(modelMapper, times(1)).map(updatedItem, ToDoItemDTO.class);
//    }


}
