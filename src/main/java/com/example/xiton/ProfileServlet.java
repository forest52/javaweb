package com.example.xiton;

// 导入输入输出流类
import java.io.*;
// 导入数据库连接类
import java.sql.*;
// 导入servlet类
import javax.servlet.*;
import javax.servlet.http.*;

// 定义一个名为ProfileServlet的类，继承HttpServlet类
public class ProfileServlet extends HttpServlet {

    // 定义数据库连接字符串
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?user=root&password=1234567";

    // 定义数据库查询语句
    private static final String SELECT_QUERY = "SELECT * FROM profile WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE profile SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";

    // 重写doGet方法，用于处理GET请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        // 声明一个变量用于存储username
        String username = null;
        // 获取请求中的所有cookie
        Cookie[] cookies = request.getCookies();
        // 判断是否有cookie
        if (cookies != null) {
            // 遍历所有cookie
            for (Cookie cookie : cookies) {
                // 判断是否是username的cookie
                if ("username".equals(cookie.getName())) {
                    // 获取cookie的值
                    username = cookie.getValue();
                    // 跳出循环
                    break;
                }
            }
        }
        // 判断是否获取到username
        if (username != null) {
            // 从cookie中获取到username，继续后续的操作
            // 调用UserService的findUserByUsername方法，传入username，返回User对象
            UserService userService = new UserService();
            User user = userService.findUserByUsername(username);
            // 判断是否找到用户
            if (user != null) {
                System.out.println(user);
                // 找到用户，设置个人信息的属性
                request.setAttribute("username", user.getUsername());
                request.setAttribute("password", user.getPassword());
                request.setAttribute("sex", user.getSex());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("phone", user.getPhone());
                request.setAttribute("address", user.getAddress());
            } else {
                // 没有找到用户，设置message属性为"用户不存在"
                request.setAttribute("message", "用户不存在");
            }
            // 转发到profile.jsp页面
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            // 没有从cookie中获取到username，跳转到登录页面
            response.sendRedirect("login.jsp");
        }
    }

    // 重写doPost方法，用于处理POST请求
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取session对象
        HttpSession session = request.getSession();

        // 从session中获取用户id
        String username = null;
        // 获取请求中的所有cookie
        Cookie[] cookies = request.getCookies();
        // 判断是否有cookie
        if (cookies != null) {
            // 遍历所有cookie
            for (Cookie cookie : cookies) {
                // 判断是否是username的cookie
                if ("username".equals(cookie.getName())) {
                    // 获取cookie的值
                    username = cookie.getValue();
                    // 跳出循环
                    break;
                }
            }
        }
        if (username != null) {
            // 从cookie中获取到username，继续后续的操作
            // 调用UserService的findUserByUsername方法，传入username，返回User对象
            UserService userService = new UserService();
            User user = userService.findUserByUsername(username);
        }else {
            // 没有找到用户，设置message属性为"用户不存在"
            request.setAttribute("message", "用户不存在");
        }
        // 转发到profile.jsp页面
        request.getRequestDispatcher("profile.jsp").forward(request, response);
        // 从请求参数中获取个人信息字段
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // 声明数据库连接和语句对象
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // 创建数据库连接
            conn = DriverManager.getConnection(DB_URL);

            // 创建预编译语句
            stmt = conn.prepareStatement(UPDATE_QUERY);

            // 设置个人信息参数
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, address);
            // 设置用户id参数
            stmt.setInt(5, username);

            // 执行更新
            int rows = stmt.executeUpdate();

            // 检查更新是否成功
            if (rows > 0) {
                // 更新成功，设置message属性为"个人信息更新成功"
                request.setAttribute("message", "个人信息更新成功");
            } else {
                // 更新失败，设置message属性为"个人信息更新失败"
                request.setAttribute("message", "个人信息更新失败");
            }

            // 重新设置个人信息的属性
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);

            // 转发请求到profile.jsp页面
            request.getRequestDispatcher("profile.jsp").forward(request, response);

        } catch (SQLException e) {
            // 处理SQL异常
            e.printStackTrace();
        } finally {
            // 关闭语句和连接对象
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
