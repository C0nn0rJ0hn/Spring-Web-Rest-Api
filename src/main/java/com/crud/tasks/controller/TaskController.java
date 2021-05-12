package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/v1/task")
@RestController
public class TaskController
{
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks()
    {
        return new ArrayList<>();
    }

    @GetMapping(value = "getSpecificTask")
    public TaskDto getTask(Long taskId)
    {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping(value = "deleteSpecificTask")
    public void deleteTask(Long taskId)
    {

    }

    @PutMapping(value = "updateSpecificTask")
    public TaskDto updateTask(TaskDto taskDto)
    {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping(value = "createNewTask")
    public void createTask(TaskDto taskDto)
    {

    }
}
