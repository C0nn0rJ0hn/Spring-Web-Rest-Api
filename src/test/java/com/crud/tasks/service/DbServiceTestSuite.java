package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class DbServiceTestSuite {

    @Autowired
    private DbService service;

    @Test
    void shouldSaveTask() {
        //Given
        Task task = new Task(null, "Title", "Content");

        //When
        Task savedTask = service.saveTask(task);
        Long savedTaskId = savedTask.getId();

        //Then
        assertThat(savedTaskId).isNotNull();
        assertEquals("Title", savedTask.getTitle());
    }

    @Test
    void shouldGetAllTasks() {
        //Given
        Task task = new Task(null, "Title", "Content");
        service.saveTask(task);
        Task task2 = new Task(null, "Title2", "Content2");
        service.saveTask(task2);
        Task task3 = new Task(null, "Title3", "Content3");
        service.saveTask(task3);

        //When
        List<Task> resultList = service.getAllTasks();

        //Then
        assertEquals(3, resultList.size());
        assertNotEquals(0, resultList.get(0).getId());
    }

    @Test
    void shouldGetTaskById() throws TaskNotFoundException{
        //Given
        Task task = new Task(null, "Title", "Content");
        Task savedTask = service.saveTask(task);
        Long id = savedTask.getId();
        Task task2 = new Task(null, "Title2", "Content2");
        Task savedTask2 = service.saveTask(task2);
        Long id2 = savedTask2.getId();

        //When
        Optional<Task> getTask = service.getTaskById(id);
        Optional<Task> getTask2 = service.getTaskById(id2);

        //Then
        assertEquals("Title", getTask.orElseThrow(TaskNotFoundException::new).getTitle());
        assertEquals("Title2", getTask2.orElseThrow(TaskNotFoundException::new).getTitle());
    }

    @Test
    void shouldDeleteTask() {
        //Given
        Task task = new Task(null, "Title", "Content");
        service.saveTask(task);
        Task task2 = new Task(null, "Title2", "Content2");
        service.saveTask(task2);
        Task task3 = new Task(null, "Title3", "Content3");
        service.saveTask(task3);

        //When
        service.deleteTask(task.getId());

        //Then
        assertEquals(2, service.getAllTasks().size());
    }
}
