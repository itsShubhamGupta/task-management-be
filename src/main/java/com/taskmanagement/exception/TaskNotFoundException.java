package com.taskmanagement.exception;

/**
 * Thrown when a task with the given id does not exist.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task not found with id: " + id);
    }
}
