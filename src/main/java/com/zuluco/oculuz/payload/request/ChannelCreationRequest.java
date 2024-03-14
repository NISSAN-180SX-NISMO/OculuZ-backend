package com.zuluco.oculuz.payload.request;

public class ChannelCreationRequest {
    private String channelName;

    // Конструкторы

    public ChannelCreationRequest() {
    }

    public ChannelCreationRequest(String channelName) {
        this.channelName = channelName;
    }

    // Геттеры и сеттеры
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
