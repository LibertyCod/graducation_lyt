package com.graduation.project.dto;

import com.graduation.project.model.User;

/**
 * @author Binbin Wang
 * @date 2018/3/23
 */
public class LoginResponseDTO {

    private User user;

    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
