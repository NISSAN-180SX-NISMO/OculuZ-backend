package com.zuluco.oculuz.payload.request;

public class InitiateUploadRequest {
    private String fileName;

    public InitiateUploadRequest() {
    }

    public InitiateUploadRequest(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
