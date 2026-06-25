package com.taskmanagement.service;

import com.taskmanagement.dto.TaskRequest;
import com.taskmanagement.dto.TaskSummary;
import com.taskmanagement.exception.TaskNotFoundException;
import com.taskmanagement.model.Task;
import com.taskmanagement.model.TaskStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * In-memory task storage and business logic.
 *
 * Per the assignment, no database is required: tasks live in a
 * thread-safe in-memory Map for the lifetime of the application,
 * seeded with a few sample tasks on startup.
 */
@Service
public class TaskService {

//    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final List<Task> tasks=new ArrayList<>();
     private final AtomicLong idGenerator = new AtomicLong(0);

    @PostConstruct
    public void seedSampleData() {
        createTask(new TaskRequest() {{
            setName("Set up project repository");
            setDescription("Initialize Git repo and push the base project structure");
        }});
        createTaskWithStatus("Design database schema",
                "Plan entities and relationships for the task management app",
                TaskStatus.COMPLETED);
        createTaskWithStatus("Implement REST APIs",
                "Build create, read, update, delete endpoints for tasks",
                TaskStatus.PENDING);
    }

    public List<Task> getAllTasks() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    public Task createTask(TaskRequest request) {
        return createTaskWithStatus(request.getName(), request.getDescription(), TaskStatus.PENDING);
    }

    private Task createTaskWithStatus(String name, String description, TaskStatus status) {
        long id = idGenerator.incrementAndGet();
        Task task = new Task(id, name, description, status, LocalDateTime.now());
        tasks.add( task);
        return task;
    }

    public Task updateStatus(Long id, TaskStatus status) {
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new TaskNotFoundException(id));

        task.setStatus(status);

        return task;
    }

    public void deleteTask(Long id) {

        boolean removed = tasks.removeIf(
                task -> task.getId().equals(id));
        if (!removed) {
            throw new TaskNotFoundException(id);
        }
    }

    public TaskSummary getSummary() {

        long total = tasks.size();
        long completed = tasks.stream()
                .filter(t -> t.getStatus() == TaskStatus.COMPLETED)
                .count();
        long pending = total - completed;
        return new TaskSummary(total, pending, completed);
    }
}
