package com.zuluco.oculuz.models.dtos.video;

import java.time.LocalDateTime;
import java.util.Date;

public class VideoPreviewDTO {
    private String id;
    private String previewUrl;
    private String channelAvatarUrl;
    private String url;
    private String title;
    private String channelName;
    private Long duration;
    private LocalDateTime uploadDate;
    private Integer views;

    public VideoPreviewDTO(String id, String previewUrl, String channelAvatarUrl, String url, String title, String channelName, Long duration, LocalDateTime uploadDate, Integer views) {
        this.id = id;
        this.previewUrl = previewUrl;
        this.channelAvatarUrl = channelAvatarUrl;
        this.url = url;
        this.title = title;
        this.channelName = channelName;
        this.duration = duration;
        this.uploadDate = uploadDate;
        this.views = views;
    }

    public VideoPreviewDTO() {
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getChannelAvatarUrl() {
        return channelAvatarUrl;
    }

    public void setChannelAvatarUrl(String channelAvatarUrl) {
        this.channelAvatarUrl = channelAvatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
