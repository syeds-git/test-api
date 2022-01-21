package com.test.proj.test.model;

import java.util.UUID;

/**
 * DTO for sending response for registration end point
 */
public class OutputDTO {
    private String uuid;
    private String welcomeMsg;

    public OutputDTO(String welcomeMsg) {
        this.uuid = UUID.randomUUID().toString();
        this.welcomeMsg = welcomeMsg;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }
}
