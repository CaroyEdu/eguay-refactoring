<%@ page import="com.test.eguay.dto.UserDTO" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jean-
  Date: 30/05/2022
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EGUAY - Mis Subastas</title>
</head>

<jsp:include page="userConnectedCheck.jsp"/>
<jsp:include page="cabecera.jsp"/>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    List<AuctionDTO> userAuctions = (List) request.getAttribute("userAuctions");
    String error = (String) request.getAttribute("error");
%>

<body>
<%
    if(userAuctions.isEmpty())
    {
%>
<br/>
<h1 class="wrapper">Aún no ha puesto en venta ningún artículo.</h1>
<%
}else{
%>
<br/>
<br/>
<% if(error!=null){ %>
<label style="color:red"class="wrapper"><%=error%></label>
<% } %>
<div>
    <form method="POST" action="MyProductsServlet" class="wrapper">
        <input class="search" placeholder="Filtrar subastas..." type="text" name="searchbar">
    </form>
</div>
<table border="1px" width="90%" style="margin:  0 5% 0 5%">
    <tr>
        <th width="50%">Título</th>
        <th width="10%">Activo</th>
        <th width="20%">Editar Subasta</th>
        <th width="20%">Borrar Subasta</th>
    </tr>
    <%
        for(AuctionDTO a : userAuctions){
    %>
    <tr>
        <td><%= a.getName()%></td>
        <td><%= a.isActive() %></td>
        <td><a href="/editAuction/<%= a.getId() %>/" style="padding: 2px 5px 2px 5px; color: white; background-color: #333; margin-left: 50%">X</a></td>
        <td><a href="/deleteAuction/<%= a.getId() %>/" style="padding: 2px 5px 2px 5px; color: white; background-color: #333; margin-left: 50%">X</a></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
