package com.example.xiton;

public class Info {
    private String name; // 信息的名称，如用户名，密码，性别等
    private String value; // 信息的值，如admin，123456，男等

    public Info(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
