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
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<c:set var="marketing" value="marketing"/>
<c:if test="${sessionScope.user == null || !sessionScope.user.username.equals(marketing) || sessionScope.categoryList == null || sessionScope.categoryList.size() == 0}">
    <c:redirect url="/"/>
</c:if>
<jsp:include page="../cabecera.jsp"/>
<h1>Group List</h1>
<form>
    <input type="submit" formaction="/group/new" value="Nuevo grupo"/>
    <table>
        <tr>
            <th>Nombre</th>
            <th>Seleccionado</th>
        </tr>
        <c:forEach var="group" items="${groups}">
            <tr>
                <td><a href="/group/${group.id}/edit">${group.name}</a></td>
                <td><input type="checkbox" name="checked" value="${group.id}"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" formaction="/group/delete" value="Eliminar"/>
    <input type="submit" formaction="/group/join" value="Unir"/>
</form>
</body>
</html>
