<%@ page import="com.test.eguay.entity.FavoriteAuction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>


<jsp:include page="cabecera.jsp"/>
<h1>Subastas compradas</h1>
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
    <a href="/PurchasedAuctions/delete/<%= a.getId() %>">Borrar</a>
    <br>
    <hr>
<% }} %>
