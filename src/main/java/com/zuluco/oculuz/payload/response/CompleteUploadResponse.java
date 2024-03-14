package com.zuluco.oculuz.payload.response;

public class CompleteUploadResponse {
    private String fileUrl;

    public CompleteUploadResponse(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public CompleteUploadResponse() {
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}