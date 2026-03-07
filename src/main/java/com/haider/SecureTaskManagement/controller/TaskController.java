package com.haider.SecureTaskManagement.controller;

import com.haider.SecureTaskManagement.dto.TaskCreateRequestDto;
import com.haider.SecureTaskManagement.entity.TaskEntity;
import com.haider.SecureTaskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskEntity addTask(@RequestBody TaskCreateRequestDto taskCreateRequestDto) {
        return taskService.addTask(taskCreateRequestDto);
    }

    @GetMapping("/{id}")
    public TaskEntity getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }
}
