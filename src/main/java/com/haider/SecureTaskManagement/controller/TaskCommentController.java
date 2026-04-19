package com.haider.SecureTaskManagement.controller;

import com.haider.SecureTaskManagement.dto.request.TaskCommentRequestDto;
import com.haider.SecureTaskManagement.dto.response.TaskCommentResponseDto;
import com.haider.SecureTaskManagement.service.TaskCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taskcomment")
@Tag(name = "Task Comment Controller", description = "To add comment on the task")
public class TaskCommentController {
    @Autowired
    private TaskCommentService taskCommentService;

    @Operation(summary = "To add comment on a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @PostMapping
    public ResponseEntity<TaskCommentResponseDto> addComment(@Valid @RequestBody TaskCommentRequestDto taskCommentRequestDto){
        TaskCommentResponseDto addedComment = taskCommentService.addCommentEntity(taskCommentRequestDto);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get comment using comment_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment found"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskCommentResponseDto> findById(@PathVariable Long id){
        TaskCommentResponseDto taskCommentResponseDto = taskCommentService.getComment(id);
        return new ResponseEntity<>(taskCommentResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "To delete a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        TaskCommentResponseDto dto = taskCommentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
