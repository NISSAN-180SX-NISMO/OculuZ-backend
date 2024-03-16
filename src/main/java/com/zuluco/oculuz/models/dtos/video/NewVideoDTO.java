package com.zuluco.oculuz.models.dtos.video;

import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.CommentBranch;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;

public class NewVideoDTO {
    private String title;
    private String url;
    private String description;
    private Long duration;
    private String previewUrl;
    private LocalDateTime uploadDate;
    private boolean adultContent;
    private String channelName;

    public NewVideoDTO() {
    }

    public NewVideoDTO(
            String title,
            String url,
            String description,
            Long duration,
            String previewUrl,
            LocalDateTime uploadDate,
            boolean adultContent,
            String channelName) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.duration = duration;
        this.previewUrl = previewUrl;
        this.uploadDate = uploadDate;
        this.adultContent = adultContent;
        this.channelName = channelName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }


    public boolean isAdultContent() {
        return adultContent;
    }

    public void setAdultContent(boolean adultContent) {
        this.adultContent = adultContent;
    }


    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
