package com.app.multithreading.dto;

public class FileUploadTask extends Task {
    private String fileName;

    public FileUploadTask(String fileName) {
        super();
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try {
            setTaskStatus(TaskStatus.IN_PROGRESS);
            System.out.println("Uploading file: " + fileName);
            Thread.sleep(5000); // Simulate file upload
            setTaskStatus(TaskStatus.COMPLETED);
        } catch (InterruptedException e) {
            setTaskStatus(TaskStatus.FAILED);
            System.err.println("Failed to upload file: " + fileName);
        }
    }
}



