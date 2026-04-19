package com.haider.SecureTaskManagement.dto.response;

import com.haider.SecureTaskManagement.enums.Priority;
import com.haider.SecureTaskManagement.enums.Status;
import com.haider.SecureTaskManagement.enums.Visibility;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class TaskResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "Unique ID of the task", example = "101")
    private Long taskId;
    @Schema(description = "Title of the task", example = "Implement JWT")
    private String title;
    @Schema(description = "Description of the task", example = "implement jwt for request authentication")
    private String description;
    @Schema(description = "Time of the task creation")
    private LocalDateTime dueDate;

    @Schema(description = "Priority of the task", example = "HIGH")
    private Priority priority;
    @Schema(description = "Status of the task", example = "COMPLETED")
    private Status status;
    @Schema(description = "Visibility of the task", example = "TEAM")
    private Visibility visibility;

    // Instead of full UserEntity → only IDs or names
    @Schema(description = "User_id of the creator", example = "5")
    private Long createdById;
    @Schema(description = "Unique ID of the user whom to assign", example = "14")
    private Long assignedToId;

    @Schema(description = "Time of the task creation")
    private LocalDateTime createdAt;
    @Schema(description = "Time of the task modification")
    private LocalDateTime updatedAt;

    // getters & setters

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
