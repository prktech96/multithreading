package com.app.multithreading.service;


import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.multithreading.dto.Task;
import com.app.multithreading.dto.TaskStatus;

@Service
public class TaskScheduler {

    private final ExecutorService executorService;
    private final Map<String, Task> taskMap = new ConcurrentHashMap<>();

    @Autowired
    public TaskScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void scheduleTask(Task task) {
        taskMap.put(task.getTaskId(), task);

        executorService.submit(() -> {
            try {
                task.execute();
            } catch (Exception e) {
                task.setTaskStatus(TaskStatus.FAILED);
                System.err.println("Error executing task: " + task.getTaskId() + ", Error: " + e.getMessage());
            } finally {
                taskMap.put(task.getTaskId(), task); // Update task status after execution
            }
        });
    }

    public TaskStatus getTaskStatus(String taskId) {
        Task task = taskMap.get(taskId);
        return (task != null) ? task.getTaskStatus() : null;
    }

    public Map<String, Task> getAllTaskStatus() {
        return Collections.unmodifiableMap(taskMap); // Prevent modification from external sources
    }
}



