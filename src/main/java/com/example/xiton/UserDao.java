package com.example.xiton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // 根据用户名查询用户
    public User findUserByUsername(String username) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = DBUtil.getConnection();
            // 定义sql语句
            String sql = "select * from tb_user where username = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数
            ps.setString(1, username);
            // 执行查询
            rs = ps.executeQuery();
            // 判断结果集是否有数据
            if (rs.next()) {
                // 创建用户对象
                user = new User();
                // 封装用户属性
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            DBUtil.closeAll(rs, ps, conn);
        }
        // 返回用户对象
        return user;
    }

    // 添加用户
    public boolean addUser(User user) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取数据库连接
            conn = DBUtil.getConnection();
            // 定义sql语句
            // 定义插入语句
            String sql = "insert into profile(username,password,sex,email,phone,address) values(?,?,?,?,?,?)";
// 创建预编译语句
            ps = conn.prepareStatement(sql);
// 设置参数
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());
// 执行插入

            int rows = ps.executeUpdate();
            // 判断影响的行数是否大于0
            if (rows > 0) {
                // 设置标志为true
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            DBUtil.closeAll(null, ps, conn);
        }
        // 返回标志
        return flag;
    }
}