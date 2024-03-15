package com.zuluco.oculuz.models.dtos.channel;

public class ChannelMiniatureDTO {
    private String name;
    private String avatarUrl;
    private Integer subscribersCount;

    public ChannelMiniatureDTO(String name, String avatarUrl, Integer subscribersCount) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.subscribersCount = subscribersCount;
    }

    public ChannelMiniatureDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getSubscribersCount() {
        return this.subscribersCount;
    }

    public void setSubscribersCount(Integer subscribersCount) {
        this.subscribersCount = subscribersCount;
    }
}
