<%--
  Created by IntelliJ IDEA.
  User: hahaha
  Date: 2023/12/29
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>个人信息</title>
</head>
<body>
<h1>欢迎来到个人信息页面</h1>
<!-- 使用JSTL标签判断是否有message属性，如果有则显示 -->
<c:if test="${not empty message}">
  <p>${message}</p>
</c:if>
<form action="profileServlet" method="post">
  <table>
    <tr>
      <td>用户名：</td>
      <td><input type="text" name="username" value="${username}" readonly></td>
    </tr>
    <tr>
      <td>密码：</td>
      <td><input type="password" name="password" value="${password}"></td>
    </tr>
    <tr>
      <td>性别：</td>
      <td>
        <c:choose>
          <c:when test="${sex == '男'}">
            <input type="radio" name="sex" value="男" checked>男
            <input type="radio" name="sex" value="女">女
          </c:when>
          <c:when test="${sex == '女'}">
            <input type="radio" name="sex" value="男">男
            <input type="radio" name="sex" value="女" checked>女
          </c:when>
          <c:otherwise>
            <input type="radio" name="sex" value="男">男
            <input type="radio" name="sex" value="女">女
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
    <tr>
      <td>邮箱：</td>
      <td><input type="text" name="email" value="${email}"></td>
    </tr>
    <tr>
      <td>电话：</td>
      <td><input type="text" name="phone" value="${phone}"></td>
    </tr>
    <tr>
      <td>地址：</td>
      <td><input type="text" name="address" value="${address}"></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="修改">
        <input type="reset" value="重置">
      </td>
    </tr>
  </table>
</form>
<a href="product.jsp">返回商品页面</a>
</body>
</html>
