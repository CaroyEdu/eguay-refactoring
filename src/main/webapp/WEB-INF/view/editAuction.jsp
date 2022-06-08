<%@ page import="com.test.eguay.entity.FavoriteAuction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>

<jsp:include page="userConnectedCheck.jsp"/>
<jsp:include page="cabecera.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: parsa-
  Date: 30/05/2022
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>eguay - Añadir Producto</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/styleaddproduct.css"/>
</head>





<body>

<div class="flex-container">
    <h1>Modifica la información sobre tu producto:</h1>
</div>

<div class="flex-container">
        <form:form method="post" action="/editSelectedAuction" modelAttribute="auction">
            Título:<form:input path="name" /><br/>
         Descripción:<form:input path="description" /><br/>
            URL Foto:<form:input path="fotourl" /><br/>
            Precio Inicial:<form:input path="startPrice" />        <br/>

            <form:hidden path="id" ></form:hidden>
        <br/>
        <button > Añadir </button>
        </form:form>
</div>









</body>
</html>
