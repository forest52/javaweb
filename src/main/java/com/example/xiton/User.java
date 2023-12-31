package com.example.xiton;
// 导入序列化接口
import java.io.Serializable;

// 定义User类，实现序列化接口
public class User implements Serializable {
    // 定义序列化版本号
    private static final long serialVersionUID = 1L;
    // 定义私有属性
    private String username; // 用户名
    private String password; // 密码
    private String sex; // 性别
    private String email; // 邮箱
    private String phone; // 电话
    private String address; // 地址

    // 定义无参构造方法


    // 定义有参构造方法
    public User(String username, String password, String sex, String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public User() {

    }




    // 定义getters和setters方法
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // 重写toString方法
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
