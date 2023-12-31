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
  <style>
    .edit-input {
      display: inline-block;
      width: 150px;
    }
  </style>
  <script>
    function showInput(name, rowId) {
      var valueTd = document.getElementById("value_" + rowId);
      var inputHtml = "<input type='text' name='newValue' class='edit-input' value='" + valueTd.innerText.trim() + "'>";
      valueTd.innerHTML = inputHtml;

      var modifyLink = document.getElementById("modify_" + rowId);
      modifyLink.style.display = "none";

      var confirmLink = document.getElementById("confirm_" + rowId);
      confirmLink.style.display = "inline-block";
    }

    function confirmEdit(name, rowId) {
      var valueTd = document.getElementById("value_" + rowId);
      var input = valueTd.getElementsByTagName("input")[0];
      var newValue = input.value.trim();

      if (newValue !== "") {
        // 构造表单并提交
        var form = document.createElement("form");
        form.setAttribute("method", "POST");
        form.setAttribute("action", "editServlet");

        var nameInput = document.createElement("input");
        nameInput.setAttribute("type", "hidden");
        nameInput.setAttribute("name", "name");
        nameInput.setAttribute("value", name);

        var valueInput = document.createElement("input");
        valueInput.setAttribute("type", "hidden");
        valueInput.setAttribute("name", "value");
        valueInput.setAttribute("value", newValue);

        form.appendChild(nameInput);
        form.appendChild(valueInput);
        document.body.appendChild(form);
        form.submit();
      }
    }
  </script>
</head>
<body>
<h1>欢迎来到个人信息页面</h1>
<!-- 使用JSTL标签判断是否有message属性，如果有则显示 -->
<c:if test="${not empty message}">
  <p>${message}</p>
</c:if>
<table>
  <!-- 使用JSTL标签遍历个人信息列表 -->
  <c:forEach var="info" items="${infoList}" varStatus="status">
    <tr>
      <td>${info.name}</td>
      <td id="value_${status.index}">${info.value}</td>
      <!-- 每一项信息都有一个修改按钮，点击之后会将行转换为输入框 -->
      <td>
        <a id="modify_${status.index}" href="javascript:showInput('${info.name}', ${status.index})">修改</a>
        <a id="confirm_${status.index}" href="javascript:confirmEdit('${info.name}', ${status.index})" style="display: none;">确定</a>
      </td>
    </tr>
  </c:forEach>
</table>
<a href="product?action=browse">返回商品页面</a>
</body>
</html>