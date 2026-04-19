package com.haider.SecureTaskManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Comment object for creating a comment on a task")
public class TaskCommentRequestDto {

    @Schema(
            description = "Id for the task, on which to comment",
            example = "Task_id",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Task ID is required")
    @Positive(message = "Task ID must be a positive number")
    private Long taskId;

    @Schema(
            description = "What comment ?",
            example = "Task looking interesting",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Comment can not be empty")
    @Size(min = 2, max = 500, message = "Comment must be between 2 and 500 characters")
    private String comment;

    // getters & setters

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
