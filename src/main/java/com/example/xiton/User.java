package com.example.xiton;
import java.io.Serializable; // 导入序列化接口

public class User implements Serializable { // 实现序列化接口
    private static final long serialVersionUID = 1L; // 定义序列化版本号
    private String username; // 用户名
    private String password; // 密码

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 以下是getters和setters
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
    }}

