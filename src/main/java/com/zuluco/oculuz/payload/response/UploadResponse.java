package com.zuluco.oculuz.payload.response;

public class UploadResponse {
    private String uploadId;
    private String message;
    private Integer partNumber;

    public UploadResponse(String uploadId, String message, int partNumber) {
        this.uploadId = uploadId;
        this.message = message;
        this.partNumber = partNumber;
    }

    public UploadResponse() {
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getMessage() {
        return message;
    }

    public int getPartNumber() {
        return partNumber;
    }
}