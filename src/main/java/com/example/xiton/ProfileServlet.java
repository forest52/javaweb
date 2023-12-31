package com.example.xiton;

// 导入输入输出流类
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 一个类来封装个人信息的属性和方法


// 一个类来显示个人信息的Servlet
@WebServlet(name = "profileServlet", urlPatterns = "/profileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 定义数据库的URL，用户名和密码


        // 定义数据库的连接，预编译语句和结果集对象
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 加载并注册MySQL的驱动类
            // 获取数据库的连接对象
            conn = DBUtil.getConnection();
            // 创建一个预编译语句对象，传入SQL语句
            stmt = conn.prepareStatement("select * from profile where username = ?");
            // 从cookie中获取用户名的值
            String username = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        username = cookie.getValue();
                        break;
                    }
                }
            }
            // 设置SQL语句的占位符的值，即用户名
            stmt.setString(1, username);
            // 执行SQL语句，返回一个结果集对象
            rs = stmt.executeQuery();
            // 使用一个列表来存储个人信息的对象
            List<Info> infoList = new ArrayList<>();

            // 遍历结果集，获取每一列的值，创建个人信息对象，添加到列表中
            while (rs.next()) {
                infoList.add(new Info("username", rs.getString("username")));
                infoList.add(new Info("password", rs.getString("password")));
                infoList.add(new Info("sex", rs.getString("sex")));
                infoList.add(new Info("email", rs.getString("email")));
                infoList.add(new Info("phone", rs.getString("phone")));
                infoList.add(new Info("address", rs.getString("address")));
            }
            // 将列表设置为请求属性，传递给JSP页面
            req.setAttribute("infoList", infoList);
            // 转发请求到JSP页面
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        } finally {
            // 关闭资源
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
