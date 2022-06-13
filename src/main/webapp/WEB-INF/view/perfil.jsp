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
    <link rel="stylesheet" href="/css/profile.css"/>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
</head>


<jsp:include page="cabecera.jsp"/>

<body>
<h1 class="wrapper">Perfil</h1>
<br>
<hr>
<br>
<div class="wrapper">
    <a href="/EditFavCategory/">
    `<button  class="btn btn-1 btn-sep icon-info">Editar categorias favoritas</button>
    </a>
    <a href="/FavAuctions/">
        `<button  class="btn btn-3 btn-sep icon-heart">Editar pujas favoritas</button>
    </a>
    <a href="/PurchasedAuctions">
        `<button  class="btn btn-2 btn-sep icon-cart">Pujas Compradas</button>
    </a>
    <a href="/myAuctions">
        `<button  class="btn btn-4 btn-sep icon-send">Mis Subastas</button>
    </a>

</div>
</body>
</html>
