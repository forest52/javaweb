
<%--
  Created by IntelliJ IDEA.
  User: hahaha
  Date: 2023/12/29
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品页面</title>
</head>
<body>
<h1>欢迎来到商品页面</h1>
<table border="1">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>价格</th>
        <th>图片</th>
        <th>操作</th>
    </tr>

    <!-- 使用JSTL标签遍历商品列表 -->
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td><img src="images/${product.image}" width="100"></td>
            <td><a href="product?action=buy&id=${product.id}">购买</a></td>
        </tr>
    </c:forEach>
</table>
<a href="cart.jsp">查看购物车</a>
<th><a href="profileServlet">个人信息</a></th>
</body>
</html>
