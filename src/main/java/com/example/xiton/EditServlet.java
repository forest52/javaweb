package com.example.xiton;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@WebServlet(name = "editServlet", urlPatterns = "/editServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 定义数据库的连接，预编译语句和结果集对象
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 加载并注册MySQL的驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取数据库的连接对象
            conn = DBUtil.getConnection();
            // 获取请求参数，即修改的信息的名称和值
            String name = request.getParameter("name");
            String value = request.getParameter("value");
            System.out.println("name: " + name);
            System.out.println("value: " + value);
            // 判断是否修改用户名
            if (name.equals("user")) {
                // 创建一个预编译语句对象，传入SQL语句
                stmt = conn.prepareStatement("update profile set username = ? where username = ?");
                // 设置SQL语句的占位符的值，即新的用户名和旧的用户名
                stmt.setString(1, value);
                // 从cookie中获取旧的用户名的值
                String oldUsername = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("username")) {
                            oldUsername = cookie.getValue();
                            break;
                        }
                    }
                }
                stmt.setString(2, oldUsername);
                // 执行SQL语句，返回受影响的行数
                int rows = stmt.executeUpdate();
                // 判断是否修改成功
                if (rows > 0) {
                    // 设置一个消息属性，提示用户修改成功
                    request.setAttribute("message", "修改成功！");
                    if (cookies != null) {
                        // 遍历 Cookie 数组，找到要修改的 Cookie
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("username")) {
                                // 修改找到的 Cookie 的值
                                cookie.setValue(value);
                                // 将修改后的 Cookie 添加到响应中
                                response.addCookie(cookie);
                                break;
                            }
                        }
                    }

                } else {
                    // 设置一个消息属性，提示用户修改失败
                    request.setAttribute("message", "修改失败！");
                }
            } else {
                // 创建一个预编译语句对象，传入SQL语句
                stmt = conn.prepareStatement("update profile set " + name + " = ? where username = ?");
                // 设置SQL语句的占位符的值，即新的值和用户名
                stmt.setString(1, value);
                // 从cookie中获取用户名的值
                String username = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("username")) {
                            username = cookie.getValue();
                            break;
                        }
                    }
                }
                stmt.setString(2, username);
                // 执行SQL语句，返回受影响的行数
                int rows = stmt.executeUpdate();
                // 判断是否修改成功
                if (rows > 0) {
                    // 设置一个消息属性，提示用户修改成功
                    request.setAttribute("message", "修改成功！");
                } else {
                    // 设置一个消息属性，提示用户修改失败
                    request.setAttribute("message", "修改失败！");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // 处理异常
            e.printStackTrace();
        } finally {
            // 关闭数据库连接和预编译语句对象
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 重定向回个人信息页面并显示消息
        response.sendRedirect("profileServlet");
    }
}