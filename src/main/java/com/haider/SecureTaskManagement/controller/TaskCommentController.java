package com.haider.SecureTaskManagement.controller;

import com.haider.SecureTaskManagement.dto.TaskCommentDto;
import com.haider.SecureTaskManagement.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskcomment")
public class TaskCommentController {
    @Autowired
    private TaskCommentService taskCommentService;

    @PostMapping
    public TaskCommentDto addComment(@RequestBody TaskCommentDto taskCommentDto){
        return taskCommentService.addCommentEntity(taskCommentDto);
    }

    @GetMapping("/{id}")
    public TaskCommentDto findById(@PathVariable Long id){
        return taskCommentService.getComment(id);
    }
}
