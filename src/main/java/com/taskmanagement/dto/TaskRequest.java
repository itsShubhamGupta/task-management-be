package com.taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request payload used when creating a new task.
 * Validation: task name is mandatory.
 */
public class TaskRequest {

    @NotBlank(message = "Task name is mandatory")
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
