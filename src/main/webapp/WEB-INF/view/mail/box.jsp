<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 12/06/2022
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bandeja de Correo</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>Bandeja de Correo</h1>
<table>
    <tr>
        <th>Asunto</th>
        <th>Producto</th>
        <th>Vendedor</th>
        <th>Mayor puja</th>
        <th>Categoría</th>
        <th>Fecha de envío</th>
    </tr>
    <c:forEach var="mail" items="${mails}">
        <c:forEach var="auction" items="${mail.auctions}">
            <tr>
                <td>${mail.subject}</td>
                <td><img src="${auction.fotourl}" href="/showAuction/${auction.id}"/> <a
                        href="/showAuction/${auction.id}">${auction.name}</a></td>
                <td>${auction.seller}</td>
                <td>${auction.maxBid}</td>
                <td>${auction.category}</td>
                <td>${mail.sentDate}</td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>
