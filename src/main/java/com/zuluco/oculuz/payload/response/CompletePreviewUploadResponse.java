package com.zuluco.oculuz.payload.response;

public class CompletePreviewUploadResponse {
    private String previewUrl;

    public CompletePreviewUploadResponse(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public CompletePreviewUploadResponse() {
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
