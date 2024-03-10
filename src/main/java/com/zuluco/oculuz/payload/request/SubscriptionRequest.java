package com.zuluco.oculuz.payload.request;

public class SubscriptionRequest {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SubscriptionRequest(String action) {
        this.action = action;
    }

    public SubscriptionRequest() {
    }
}
