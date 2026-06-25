package com.taskmanagement.dto;

import com.taskmanagement.model.TaskStatus;
import jakarta.validation.constraints.NotNull;

/**
 * Request payload used when updating a task's status.
 */
public class StatusUpdateRequest {

    @NotNull(message = "Status is mandatory")
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
