<%@ page import="com.test.eguay.entity.FavoriteAuction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>


<jsp:include page="cabecera.jsp"/>
<h1>Subastas compradas</h1> <br>

<form method="POST" action="/PurchasedAuctions/filter">

    <input type="text" name="filter">
    <input type="submit" name="buscar" placeholder="filtrar">
</form>

<%

    List<AuctionDTO> auctionDTOS = (List<AuctionDTO>) request.getAttribute("purchasedAucs") ;
  if(auctionDTOS != null){
    for(AuctionDTO a : auctionDTOS){
%>
<h2 ><%=  a.getName() %><h2/>
    <br>
    <img src="<%= a.getFotourl() %>">
    <br>
    <br>
    <br>

    <button href="/PurchasedAuctions/delete/<%= a.getId() %>">Borrar</button>
    <br>
    <hr>
<% }} %>
