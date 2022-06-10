<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 10/06/2022
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group List</title>
</head>
<body>
<h1>Group List</h1>
<table>
    <tr>
        <th>Nombre</th>
        <th>Seleccionado</th>
    </tr>
    <c:forEach var="group" items="${groups}">
        <tr>
            <td><a href="/group/${group.id}">${group.name}</a></td>
            <td><input type="checkbox" name="checked" value="${group.id}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
