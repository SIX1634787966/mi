package com.mi.web.controller.webSocket;

import java.io.Serializable;

public class UserMessage implements Serializable {
    private String from;

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "from='" + from + '\'' +
                ", userId='" + userId + '\'' +
                ", context='" + context + '\'' +
                '}';
    }

    public void setFrom(String from) {
        this.from = from;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    private String context;
}
