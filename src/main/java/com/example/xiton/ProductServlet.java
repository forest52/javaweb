package com.example.xiton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    // 创建一个商品列表，用于模拟数据库中的数据
    private List<Product> products = new ArrayList<>();

    // 在servlet初始化时，添加一些商品到列表中
    @Override
    public void init() {
        products.add(new Product(1, "iPhone 13", 7999.00, "iphone13.jpg"));
        products.add(new Product(2, "iPad Pro", 5999.00, "ipadpro.jpg"));
        products.add(new Product(3, "MacBook Air", 8999.00, "macbookair.jpg"));
        products.add(new Product(4, "AirPods Pro", 1499.00, "airpodspro.jpg"));
        products.add(new Product(5, "Apple Watch", 2999.00, "applewatch.jpg"));
    }

    // 处理GET请求，根据不同的action参数，执行不同的操作
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取action参数，如果为空，默认为browse
        String action = req.getParameter("action");
        if (action == null) {
            action = "browse";
        }

        switch (action) {
            case "browse":
                // 浏览商品，将商品列表存入请求属性中，然后转发到商品页面
                System.out.println(products);
                req.setAttribute("products", products);
                req.getRequestDispatcher("product.jsp").forward(req, resp);
                break;
            case "buy":
                // 购买商品，获取商品编号，然后将商品添加到购物车中，最后重定向到购物车页面
                int id = Integer.parseInt(req.getParameter("id"));
                Product product = findProductById(id); // 根据编号查找商品
                if (product != null) {
                    addToCart(req, product); // 将商品添加到购物车
                }
                resp.sendRedirect("cart.jsp");
                break;
            case "delete": // 新增的case语句，用于处理删除商品的action参数
                // 删除商品，获取商品编号，然后从购物车中移除该商品，最后重定向到购物车页面
                int id1 = Integer.parseInt(req.getParameter("id"));
                removeFromCart(req, id1); // 从购物车中移除指定的商品
                resp.sendRedirect("cart.jsp");
                break;
            default:
                // 无效的action参数，返回错误信息
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action: " + action);
        }

    }
    private void removeFromCart(HttpServletRequest req, int id) {
        // 获取session对象，如果不存在，就返回
        HttpSession session = req.getSession(false);
        if (session == null) {
            return;
        }
        // 获取购物车列表，如果不存在，就返回
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            return;
        }
        // 遍历购物车列表，找到匹配的商品，然后从列表中移除
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            if (product.getId() == id) {
                cart.remove(i);
                break;
            }
        }
    }

    // 根据商品编号查找商品，如果找不到，返回null
    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    // 将商品添加到购物车，如果购物车不存在，就创建一个新的购物车
    private void addToCart(HttpServletRequest req, Product product) {
        // 获取session对象，如果不存在，就创建一个
        HttpSession session = req.getSession(true);
        // 获取购物车列表，如果不存在，就创建一个新的列表
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        // 将商品添加到购物车列表中
        cart.add(product);
    }
}
