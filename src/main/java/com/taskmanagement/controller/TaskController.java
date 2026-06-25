package com.taskmanagement.controller;

import com.taskmanagement.dto.StatusUpdateRequest;
import com.taskmanagement.dto.TaskRequest;
import com.taskmanagement.dto.TaskSummary;
import com.taskmanagement.model.Task;
import com.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for task management.
 *
 *  POST   /api/tasks            -> Create Task
 *  GET    /api/tasks            -> Get All Tasks
 *  PATCH  /api/tasks/{id}/status -> Update Task Status
 *  DELETE /api/tasks/{id}       -> Delete Task
 *  GET    /api/tasks/summary    -> Dashboard summary
 *
 * CORS is enabled for the Angular dev server (http://localhost:4200).
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task created = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id,
                                              @Valid @RequestBody StatusUpdateRequest request) {
        Task updated = taskService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<TaskSummary> getSummary() {
        return ResponseEntity.ok(taskService.getSummary());
    }
}
