package com.zuluco.oculuz.payload.response;

public class CompleteVideoUploadResponse {
    private String videoUrl;

    public CompleteVideoUploadResponse(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public CompleteVideoUploadResponse() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}