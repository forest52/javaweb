package com.example.xiton;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应的编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取请求参数
// 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
// 创建用户对象
        User user = new User(username, password, sex, email, phone, address);

        // 创建数据访问对象
        UserDao userDao = new UserDao();
        // 调用添加方法
        boolean flag = userDao.addUser(user);
        // 判断标志是否为true
        if (flag) {
            // 注册成功，跳转到登录页面
            response.sendRedirect("login.html");
        } else {
            // 注册失败，跳转到错误页面
            request.setAttribute("msg", "注册失败");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}