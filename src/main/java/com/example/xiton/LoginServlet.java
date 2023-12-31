package com.example.xiton;

import com.example.xiton.User;
// 控制层，处理登录请求
// LoginServlet.java
// 控制层，处理登录请求
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应的编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 创建用户对象
        // 创建数据访问对象
        UserDao userDao = new UserDao();
        // 调用查询方法
        User result = userDao.findUserByUsername(username);
        // 判断结果是否为空
        if (result == null) {
            // 用户名不存在，跳转到错误页面
            request.setAttribute("msg", "用户名不存在");
            request.getRequestDispatcher("error.html").forward(request, response);
        } else {
            // 用户名存在，判断密码是否正确
            if (result.getPassword().equals(password)) {
                // 密码正确，跳转到成功页面
                // 创建一个cookie对象，指定名称和值为用户名
                Cookie cookie = new Cookie("username", username);
                // 设置cookie的有效期，单位为秒，可以根据需要修改
                cookie.setMaxAge(60 * 60 * 24); // 一天
                // 设置cookie的路径，可以根据需要修改
                cookie.setPath("/"); // 根目录
                // 将cookie添加到响应中
                response.addCookie(cookie);
                request.getRequestDispatcher("/product").forward(request, response);
            } else {
                // 密码错误，跳转到错误页面
                request.setAttribute("msg", "密码错误");
                request.getRequestDispatcher("error.html").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}