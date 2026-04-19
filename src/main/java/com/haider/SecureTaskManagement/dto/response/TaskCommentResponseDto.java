package com.haider.SecureTaskManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Task comment response dto")
public class TaskCommentResponseDto {

    @Schema(description = "Unique ID for the comment", example = "110")
    private Long taskCommentId;
    @Schema(description = "Unique ID of the specif task", example = "12")
    private Long taskId;
    @Schema(description = "Unique ID of the user who make this comment", example = "72")
    private Long userId;
    @Schema(description = "What is the comment", example = "Task is amaing")
    private String comment;
    @Schema(description = "Time when comment created")
    private LocalDateTime createdAt;

    // getters & setters

    public Long getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(Long taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
