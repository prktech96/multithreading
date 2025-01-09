package com.app.multithreading.dto;

public class DataProcessingTask extends Task {
    private String data;

    public DataProcessingTask(String data) {
        super();
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void execute() {
        try {
            setTaskStatus(TaskStatus.IN_PROGRESS);
            System.out.println("Processing data: " + data);
            Thread.sleep(5000); // Simulate data processing
            setTaskStatus(TaskStatus.COMPLETED);
        } catch (InterruptedException e) {
            setTaskStatus(TaskStatus.FAILED);
            System.err.println("Failed to process data: " + data);
        }
    }
}


