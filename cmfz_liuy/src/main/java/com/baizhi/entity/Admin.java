package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    private String admin_id;
    private String username;
    private String password;

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
