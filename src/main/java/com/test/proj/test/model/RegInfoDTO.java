package com.test.proj.test.model;

import com.test.proj.test.validation.ValidPassword;

import javax.validation.constraints.NotBlank;

/**
 * DTO holding input data for registration end point
 */
public class RegInfoDTO {

    @NotBlank
    private String username;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    private String ipAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "RegInfoDTO{" +
                "username='" + username + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
