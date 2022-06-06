<%@ page import="com.test.eguay.entity.FavoriteAuction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<h1>Kir</h1>

<%
    List<AuctionDTO> auctionDTOS = (List<AuctionDTO>) request.getAttribute("favCats") ;
    for(AuctionDTO a : auctionDTOS){
%>
    <%= a.getName() %>
<% } %>