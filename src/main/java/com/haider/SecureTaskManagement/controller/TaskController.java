package com.haider.SecureTaskManagement.controller;

import com.haider.SecureTaskManagement.dto.request.TaskRequestDto;
import com.haider.SecureTaskManagement.dto.response.PageResponse;
import com.haider.SecureTaskManagement.dto.response.TaskResponseDto;
import com.haider.SecureTaskManagement.enums.Priority;
import com.haider.SecureTaskManagement.enums.Status;
import com.haider.SecureTaskManagement.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/tasks")
@Tag(name = "Task Controller", description = "To add, and delete the task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Operation(summary = "To add Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task generated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping
    public ResponseEntity<TaskResponseDto> addTask(@Valid @RequestBody TaskRequestDto taskCreateRequestDto) {
        TaskResponseDto responseDto = taskService.addTask(taskCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Fetch Task using task_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long id) {
        TaskResponseDto dto = taskService.getTaskResponseDto(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(summary = "To assign a task")
    @GetMapping("/assigned")
    public PageResponse<TaskResponseDto> getAssignedTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )       Pageable pageable
            )  {
        return taskService.getAssignedToMeTask(status,priority,pageable);
    }

    @Operation(summary = "To fetch current user tasks")
    @GetMapping("/my")
    public ResponseEntity<PageResponse<TaskResponseDto>> getFilteredTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {

        PageResponse<TaskResponseDto> tasks = taskService.getMyTask(status,priority,pageable);
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "To delete a task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        TaskResponseDto dto = taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
