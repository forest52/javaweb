package com.example.xiton;
// 导入数据库连接类
import java.sql.*;

// 定义UserService类
public class UserService {

    // 定义数据库连接字符串
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?user=root&password=1234567";

    // 定义数据库查询语句
    private static final String FIND_BY_USERNAME_QUERY = "SELECT * FROM profile WHERE username = ?";
    private static final String UPDATE_QUERY = "UPDATE profile SET password = ?, sex = ?, email = ?, phone = ?, address = ? WHERE username = ?";

    // 定义一个根据用户名查找用户的方法，传入用户名，返回User对象
    public User findUserByUsername(String username) {
        // 声明数据库连接和语句对象
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // 创建数据库连接
            conn = DriverManager.getConnection(DB_URL);

            // 创建预编译语句
            stmt = conn.prepareStatement(FIND_BY_USERNAME_QUERY);

            // 设置用户名参数
            stmt.setString(1, username);

            // 执行查询
            ResultSet rs = stmt.executeQuery();

            // 检查查询是否返回结果
            if (rs.next()) {
                // 从结果集中获取用户信息字段
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                // 创建并返回User对象
                return new User(username, password,sex,email,phone,address);
            }

            // 关闭结果集
            rs.close();

            // 如果没有找到用户，返回null
            return null;

        } catch (SQLException e) {
            // 处理SQL异常
            e.printStackTrace();
            // 返回null
            return null;
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

    // 定义一个更新用户信息的方法，传入User对象，返回是否更新成功
    public boolean updateUser(User user) {
        // 声明数据库连接和语句对象
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // 创建数据库连接
            conn = DriverManager.getConnection(DB_URL);

            // 创建预编译语句
            stmt = conn.prepareStatement(UPDATE_QUERY);

            // 设置用户信息参数
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getSex());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getAddress());
            // 设置用户名参数
            stmt.setString(6, user.getUsername());

            // 执行更新
            int rows = stmt.executeUpdate();

            // 检查更新是否成功
            if (rows > 0) {
                // 更新成功，返回true
                return true;
            } else {
                // 更新失败，返回false
                return false;
            }

        } catch (SQLException e) {
            // 处理SQL异常
            e.printStackTrace();
            // 返回false
            return false;
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
