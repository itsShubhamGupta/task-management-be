package com.taskmanagement.service;

import com.taskmanagement.dto.TaskRequest;
import com.taskmanagement.exception.TaskNotFoundException;
import com.taskmanagement.model.Task;
import com.taskmanagement.model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
        // Not calling seedSampleData() so each test starts from a clean, predictable state.
    }

    @Test
    void createTask_addsTaskWithPendingStatus() {
        TaskRequest request = new TaskRequest();
        request.setName("Write tests");
        request.setDescription("Add unit tests for the service layer");

        Task created = taskService.createTask(request);

        assertNotNull(created.getId());
        assertEquals("Write tests", created.getName());
        assertEquals(TaskStatus.PENDING, created.getStatus());
        assertEquals(1, taskService.getAllTasks().size());
    }

    @Test
    void updateStatus_marksTaskCompleted() {
        TaskRequest request = new TaskRequest();
        request.setName("Sample task");
        Task created = taskService.createTask(request);

        Task updated = taskService.updateStatus(created.getId(), TaskStatus.COMPLETED);

        assertEquals(TaskStatus.COMPLETED, updated.getStatus());
    }

    @Test
    void updateStatus_throwsWhenTaskDoesNotExist() {
        assertThrows(TaskNotFoundException.class,
                () -> taskService.updateStatus(999L, TaskStatus.COMPLETED));
    }

    @Test
    void deleteTask_removesTaskFromList() {
        TaskRequest request = new TaskRequest();
        request.setName("Task to delete");
        Task created = taskService.createTask(request);

        taskService.deleteTask(created.getId());

        assertTrue(taskService.getAllTasks().isEmpty());
    }

    @Test
    void deleteTask_throwsWhenTaskDoesNotExist() {
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(999L));
    }

    @Test
    void getSummary_reflectsCorrectCounts() {
        TaskRequest pending = new TaskRequest();
        pending.setName("Pending task");
        taskService.createTask(pending);

        TaskRequest toComplete = new TaskRequest();
        toComplete.setName("Task to complete");
        Task created = taskService.createTask(toComplete);
        taskService.updateStatus(created.getId(), TaskStatus.COMPLETED);

        var summary = taskService.getSummary();

        assertEquals(2, summary.getTotalTasks());
        assertEquals(1, summary.getPendingTasks());
        assertEquals(1, summary.getCompletedTasks());
    }
}
