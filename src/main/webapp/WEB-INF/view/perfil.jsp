<%--
    Document   : profile
    Created on : 04-abr-2022, 9:30:46
    Author     : Parsa zendehdel nobari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>


<jsp:include page="cabecera.jsp"/>

<body>
<h1 class="wrapper">Perfil</h1>
<form>
    <input class="wrapper" type="button" onclick="window.location.href='AddFavCategoryServlet';" value="Editar Categorias Favorias" />
    <input class="wrapper" type="button" onclick="window.location.href='EditFavAuctionServlet';" value="Editar pujas favoritas" />
    <input class="wrapper" type="button" onclick="window.location.href='CheckPurchasedAuctionsServlet';" value="Pujas Compradas" />
    <input class="wrapper" type="button" onclick="window.location.href='MyProductsServlet';" value="Mis Subastas" />

</form>
</div>
</body>
</html>
