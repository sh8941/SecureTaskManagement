package com.haider.SecureTaskManagement.dto.request;

import com.haider.SecureTaskManagement.enums.Priority;
import com.haider.SecureTaskManagement.enums.Status;
import com.haider.SecureTaskManagement.enums.Visibility;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Schema(description = "Request object for creating a task")
public class TaskRequestDto {

    @Schema(
            description = "Title of the task",
            example = "Complete backend project",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(
            description = "Detailed description of the task",
            example = "Complete backend project",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(max = 500, message = "Description can't exceed 500 characters")
    private String description;

    @Schema(
            description = "Due date of the task",
            example = "dd-mm-yyyy",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @FutureOrPresent(message = "Due date must be today or future")
    private LocalDateTime dueDate;

    @Schema(
            description = "Priority of the task",
            example = "MEDIUM",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Priority is required")
    private Priority priority;

    @Schema(
            description = "Status of the task",
            example = "PENDING",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Status is required")
    private Status status;

    @Schema(
            description = "Visibility of the task",
            example = "PUBLIC",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Visibility is required")
    private Visibility visibility;

    @Schema(
            description = "Whom to assign",
            example = "USER_ID",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    // Only IDs from frontend
    private Long assignedToId;

    // getters & setters

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

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }
}