<%@ page import="com.test.eguay.entity.FavoriteAuction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<jsp:include page="cabecera.jsp"/>

<h1>Subastas favoritas</h1>
<br>
<hr>
<br>
<%
    List<AuctionDTO> auctionDTOS = (List<AuctionDTO>) request.getAttribute("favCats") ;
    for(AuctionDTO a : auctionDTOS){
%>
    <h2 ><%=  a.getName() %><h2/>
        <br>
    <img src="<%= a.getFotourl() %>">
        <br>
        <br>
        <br>
        <hr>
<% } %>