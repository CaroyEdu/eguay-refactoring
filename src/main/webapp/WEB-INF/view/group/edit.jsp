<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 10/06/2022
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Grupo</title>
</head>
<body>
<h1>Editar Grupo</h1>
<form:form modelAttribute="group" action="/group/edit">
    <form:hidden path="id"/>
    Nombre: <form:input path="name" id="name"/>
    <table>
        <tr>
            <th>Nombre de usuario</th>
            <th>Seleccionado</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td><input type="checkbox" name="checked" value="${user.id}" ${group.userIds.contains(user.id) ? "checked": ""}/></td>
            </tr>
        </c:forEach>
    </table>
    <form:button>Editar</form:button>
</form:form>
<script>
    $("#name").attr('required', '');
</script>
</body>
</html>
