package com.taskmanagement.dto;

/**
 * Dashboard summary statistics: total / pending / completed task counts.
 */
public class TaskSummary {

    private long totalTasks;
    private long pendingTasks;
    private long completedTasks;

    public TaskSummary(long totalTasks, long pendingTasks, long completedTasks) {
        this.totalTasks = totalTasks;
        this.pendingTasks = pendingTasks;
        this.completedTasks = completedTasks;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public long getPendingTasks() {
        return pendingTasks;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }
}
