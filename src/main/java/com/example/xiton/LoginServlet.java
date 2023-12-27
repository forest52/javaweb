package com.example.xiton;

import com.example.xiton.User;
// 控制层，处理登录请求
// LoginServlet.java
// 控制层，处理登录请求
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        User user = new User(username, password);
        // 创建数据访问对象
        UserDao userDao = new UserDao();
        // 调用查询方法
        User result = userDao.findUserByUsername(user.getUsername());
        // 判断结果是否为空
        if (result == null) {
            // 用户名不存在，跳转到错误页面
            request.setAttribute("msg", "用户名不存在");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            // 用户名存在，判断密码是否正确
            if (result.getPassword().equals(user.getPassword())) {
                // 密码正确，跳转到成功页面
                request.setAttribute("user", user);
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                // 密码错误，跳转到错误页面
                request.setAttribute("msg", "密码错误");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}