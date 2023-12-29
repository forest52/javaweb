package com.example.xiton;

public class Product {
    private int id; // 商品编号
    private String name; // 商品名称
    private double price; // 商品价格
    private String image; // 商品图片

    // 构造方法
    public Product(int id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    // getter和setter方法

    // 获取商品编号
    public int getId() {
        return id;
    }

    // 设置商品编号
    public void setId(int id) {
        this.id = id;
    }

    // 获取商品名称
    public String getName() {
        return name;
    }

    // 设置商品名称
    public void setName(String name) {
        this.name = name;
    }

    // 获取商品价格
    public double getPrice() {
        return price;
    }

    // 设置商品价格
    public void setPrice(double price) {
        this.price = price;
    }

    // 获取商品图片
    public String getImage() {
        return image;
    }

    // 设置商品图片
    public void setImage(String image) {
        this.image = image;
    }
}
