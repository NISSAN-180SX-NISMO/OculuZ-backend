package com.zuluco.oculuz.models.dtos.video;

import java.time.LocalDateTime;
import java.util.Date;

public class VideoPageDTO {
    private String id;
    private String url;
    private String previewUrl;
    private String channelAvatarUrl;
    private String title;
    private String channelName;
    private String description;
    private Long duration;
    private LocalDateTime uploadDate;
    private LocalDateTime editDate;
    private Boolean monetized;
    private Boolean adultContent;
    private Boolean banned;
    private Integer views;
    private Integer likes;
    private Integer dislikes;
    private Long commentBranchId;

    public VideoPageDTO(String id, String url, String previewUrl, String channelAvatarUrl, String title, String channelName, String description, Long duration, LocalDateTime uploadDate, LocalDateTime editDate, Boolean monetized, Boolean adultContent, Boolean banned, Integer views, Integer likes, Integer dislikes, Long commentBranchId) {
        this.id = id;
        this.url = url;
        this.previewUrl = previewUrl;
        this.channelAvatarUrl = channelAvatarUrl;
        this.title = title;
        this.channelName = channelName;
        this.description = description;
        this.duration = duration;
        this.uploadDate = uploadDate;
        this.editDate = editDate;
        this.monetized = monetized;
        this.adultContent = adultContent;
        this.banned = banned;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.commentBranchId = commentBranchId;
    }

    public VideoPageDTO() {
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public Boolean getMonetized() {
        return monetized;
    }

    public void setMonetized(Boolean monetized) {
        this.monetized = monetized;
    }

    public Boolean getAdultContent() {
        return adultContent;
    }

    public void setAdultContent(Boolean adultContent) {
        this.adultContent = adultContent;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Long getCommentBranchId() {
        return commentBranchId;
    }

    public void setCommentBranchId(Long commentBranchId) {
        this.commentBranchId = commentBranchId;
    }
}
