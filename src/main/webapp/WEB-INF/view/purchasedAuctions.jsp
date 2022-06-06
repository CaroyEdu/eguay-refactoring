<%@ page import="com.test.eguay.entity.FavoriteAuction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<h1>Kir</h1>

<%
    List<AuctionDTO> auctionDTOS = (List<AuctionDTO>) request.getAttribute("purchasedAucs") ;
  if(auctionDTOS != null){
    for(AuctionDTO a : auctionDTOS){
%>
<%= a.getName() %>
<a href="/PurchasedAuctions/delete/<%= a.getId() %>">Borrar</a>
<% }} %>
