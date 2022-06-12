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
        <th>Fecha de envío</th>
        <th>Asunto</th>
        <th>Producto</th>
        <th>Vendedor</th>
        <th>Categoría</th>
        <th>Mayor puja</th>
        <th>Precio de cierre</th>
        <th>Fecha de cierre</th>
    </tr>
    <c:forEach var="mail" items="${mails}">
        <c:forEach var="auction" items="${mail.auctions}">
            <tr>
                <td>${mail.sentDate}</td>
                <td>${mail.subject}</td>
                <td><img src="${auction.fotourl}" href="/showAuction/${auction.id}" height="30" width="30"/> <a href="/showAuction/${auction.id}">${auction.name}</a></td>
                <td>${auction.seller}</td>
                <td>${auction.category}</td>
                <td>${auction.maxBid != null ? auction.maxBid : auction.startPrice}€</td>
                <th>${auction.closePrice != null ? auction.closePrice : "No establecido"}€</th>
                <td>${auction.closeDate != null ? auction.closeDate : "No establecida"}</td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>
