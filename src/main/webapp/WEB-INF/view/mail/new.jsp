<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 12/06/2022
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enviar Correo</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<c:set var="marketing" value="marketing"/>
<c:if test="${sessionScope.user == null || !sessionScope.user.username.equals(marketing) || sessionScope.categoryList == null || sessionScope.categoryList.size() == 0}">
    <c:redirect url="/"/>
</c:if>
<jsp:include page="../cabecera.jsp"/>
<h1>Enviar Correo</h1>

<form:form modelAttribute="mail" action="/mail/new">
    <h2>Subastas</h2>
    <table>
        <tr>
            <th>Titulo subasta</th>
            <th>Seleccionada</th>
        </tr>
        <c:forEach var="auction" items="${auctions}">
            <tr>
                <td><img src="${auction.fotourl}" href="/showAuction/${auction.id}" height="30" width="30"/> <a href="/showAuction/${auction.id}">${auction.name}</a></td>
                <td><input type="checkbox" name="auction" value="${auction.id}"></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Grupos</h2>
    <table>
        <tr>
            <th>Nombre del grupo</th>
            <th>Seleccionado</th>
        </tr>
        <c:forEach var="group" items="${groups}">
            <tr>
                <td><a href="/group/${group.id}/edit">${group.name}</a></td>
                <td><input type="checkbox" name="group" value="${group.id}"></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Asunto</h2>
    <form:textarea path="subject" rows="2" cols="20" title="Asunto"/>

    <form:button>Enviar</form:button>
</form:form>
</body>
</html>
