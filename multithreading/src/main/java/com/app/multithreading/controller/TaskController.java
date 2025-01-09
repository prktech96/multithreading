package com.app.multithreading.controller;


import com.app.multithreading.dto.Task;
import com.app.multithreading.dto.TaskStatus;
import com.app.multithreading.service.TaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskScheduler taskScheduler;

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleTask(@RequestBody Task task) {
        task.setTaskId(UUID.randomUUID().toString());
        taskScheduler.scheduleTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task Scheduled with ID: " + task.getTaskId());
    }

    @GetMapping("/{taskId}/status")
    public ResponseEntity<TaskStatus> getTaskStatus(@PathVariable String taskId) {
        TaskStatus status = taskScheduler.getTaskStatus(taskId);
        if (status != null) {
            return ResponseEntity.ok(status);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<Map<String, Task>> getAllTaskStatus() {
        return ResponseEntity.ok(taskScheduler.getAllTaskStatus());
    }
}


