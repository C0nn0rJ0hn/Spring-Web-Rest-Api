package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper mapper;

    @Test
    void shouldGetAllTasks() throws Exception {
        //Given
        List<Task> taskList = List.of(new Task(1L, "Task title", "Task content"));
        List<TaskDto> taskDtoList = List.of(new TaskDto(1L, "Task Dto title", "Task Dto content"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(mapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/task/getTasks")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Task Dto title"));
    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(5L, "Title", "Content");
        TaskDto taskDto = new TaskDto(8L, "Title Dto", "Content Dto");

        when(service.getTaskById(5L)).thenReturn(Optional.of(task));
        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/task/getTaskById")
        .contentType(MediaType.APPLICATION_JSON)
        .param("taskId", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Title Dto")))
                .andExpect(jsonPath("$.id", Matchers.is(8)))
                .andExpect(jsonPath("$.content", Matchers.is("Content Dto")));
    }

    @Test
    void shouldDeleteTaskById() throws Exception {
        //Given, When & Then
        mockMvc.perform(MockMvcRequestBuilders
        .delete("/v1/task/deleteTaskById")
        .contentType(MediaType.APPLICATION_JSON)
        .param("taskId", "3"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(7L, "Title Dto", "Content Dto");
        Task task = new Task(10L, "Task Title", "Task Content");

        when(mapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
        .put("/v1/task/updateTask")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7L))
                .andExpect(jsonPath("$.title").value("Title Dto"))
                .andExpect(jsonPath("$.content").value("Content Dto"));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        TaskDto dto = new TaskDto(1L, "Dto Title", "Dto Content");
        Task task = new Task(2L, "Task Title", "Task Content");

        when(mapper.mapToTask(dto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(dto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/task/createTask")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(jsonContent))
                .andExpect(status().isOk());
    }

}
