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
  <title>购物车页面</title>
</head>
<body>
<h1>欢迎来到购物车页面</h1>
<table border="1">
  <tr>
    <th>编号</th>
    <th>名称</th>
    <th>价格</th>
    <th>图片</th>
    <th>操作</th> <!-- 新增的表头，用于显示删除链接 -->
  </tr>
  <!-- 使用JSTL标签遍历购物车列表 -->
  <c:forEach var="product" items="${cart}">
    <tr>
      <td>${product.id}</td>
      <td>${product.name}</td>
      <td>${product.price}</td>
      <td><img src="images/${product.image}" width="100"></td>
      <td><a href="product?action=delete&id=${product.id}">删除</a></td> <!-- 新增的单元格，用于显示删除链接 -->
    </tr>
  </c:forEach>
</table>
<a href="product?action=browse">继续购物</a>
</body>
</html>
