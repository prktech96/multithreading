package com.app.multithreading.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FileUploadTask.class, name = "FileUploadTask"),
        @JsonSubTypes.Type(value = DataProcessingTask.class, name = "DataProcessingTask")
})
public abstract class Task {
    private String taskId;
    private TaskStatus taskStatus;

    public Task() {
        this.taskStatus = TaskStatus.PENDING;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public abstract void execute();
}

