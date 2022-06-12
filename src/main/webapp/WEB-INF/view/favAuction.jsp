<%@ page import="com.test.eguay.entity.FavoriteAuction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<jsp:include page="cabecera.jsp"/>

<style>
    h1{
        font-size: 3vw;
    }
    #grid {
        display: grid;
        grid-template-rows: repeat(3, 1fr);
        grid-template-columns: repeat(3, 1fr);
        grid-gap: 10px;
    }
    #grid > div {
        background-color: #42b0cd;
        color: white;
        font-size: 4vw;
        padding: 20px;
    }

    .product{
        max-height: 560px;
        max-width: 600px;
        padding-left: 55px;
    }
</style>

<h1>Subastas favoritas</h1>

<form method="POST" action="/FavAuctions/filter">

    <input type="text" name="filter">
    <input type="submit" name="buscar" placeholder="filtrar">

</form>

<br>



<div id="grid">
<%
    List<AuctionDTO> auctionDTOS = (List<AuctionDTO>) request.getAttribute("favCats") ;
    for(AuctionDTO a : auctionDTOS){
%>
    <div>
    <h2 ><%=  a.getName() %><h2/>
    <img class="product" src="<%= a.getFotourl() %>">
    </div>
<% } %>
</div>


