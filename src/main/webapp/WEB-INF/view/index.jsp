<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<%@ page import="com.test.eguay.dto.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: jean-
  Date: 23/05/2022
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="cabecera.jsp"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EGUAY - Inicio</title>
</head>

<%
    List<AuctionDTO> auctionList = (List) request.getAttribute("auctionList");
    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
    UserDTO user = (UserDTO) session.getAttribute("user");
    List<AuctionDTO> auctionFavList = null;

    if(user!=null){
        auctionFavList = (List<AuctionDTO>) request.getAttribute("favAuctions");
    }
%>

<body>
<div>
    <h2 class="title">Últimos productos</h2>
    <p class="title-description">Aquí tendrás una lista de los últimos productos publicados en Eguay</p>
</div>
<%
    int cantidad = 0;

    for(AuctionDTO a : auctionList)
    {
        if(a.isActive()){
            if(cantidad == 0)
            {
%>
<div class="flex-container">
    <%
        }
        String closeDate = "";
        if(a.getCloseDate() != null){
            closeDate = sdf.format(a.getCloseDate());
        }
    %>
    <div class="card">
        <% if(!a.getFotourl().equals("") )
        { %>
        <img src="<%= a.getFotourl() %>" style="width:100%; height: 50%">
        <% }else { %>
        <img src="img/placeholder.png" style="width:100%; height: 50%">
        <% } %>
        <p class="card-title"><a href="/showAuction/<%= a.getId()%>"><%= a.getName() %></a></p>
        <p class="card-description"><%= a.getDescription() %></p>
        <div class="product-bottom-details">
            <%
                if(a.getMaxBid()!=null){
            %>
            <div class="product-price"><small><%=a.getStartPrice()%></small><%=a.getMaxBid()%>
            <%
                }else{
            %>
            <div class="product-price"><%=a.getStartPrice()%>
            <%
                }
            %>
                <% if(a.getCloseDate()!=null)
                { %>
                <p class="card-countdown" id="cd_<%= closeDate %>"></p>
                <% } %>
                <div class="product-links">
                    <% if(user != null) { %>
                    <% if(auctionFavList != null && auctionFavList.contains(a)){ %>
                    <a href="/deleteFav/<%= a.getId() %>">💔<i class="fa fa-heart"></i></a>
                    <% }else { %>
                    <a href="/addFav/<%= a.getId() %>">❤️<i class="fa fa-shopping-cart"></i></a>
                    <% } %>
                    <% } %>
                    <a href="/showAuction/<%= a.getId()%>">🛒<i class="fa fa-shopping-cart"></i></a>
                </div>
            </div>
        </div>

</div>
<%
    cantidad++;
    if(cantidad == 6)
    {
%>
</div>
<%
                cantidad = 0;
            }
        }}
    if(cantidad > 0){
%>
</div>
<%
    }
%>
<script>
    function TimeRemaining(){
        var els = document.querySelectorAll('[id^="cd_"]');
        for (var i=0; i<els.length; i++) {
            var el_id = els[i].getAttribute('id');
            var end_time = el_id.split('_')[1];
            var deadline = new Date(end_time);
            var now = new Date();
            var t = Math.floor(deadline.getTime() - now.getTime());
            var days = Math.floor(t / (1000 * 60 * 60 * 24));
            var hours = Math.floor((t % (1000 * 60 * 60 * 24))/(1000 * 60 * 60));
            var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((t % (1000 * 60)) / 1000);
            if (t < 0) {
                document.getElementById("cd_" + end_time).innerHTML = 'EXPIRED';
            }else{
                document.getElementById("cd_" + end_time).innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s";
            }
        }
    }

    function StartTimeRemaining(){
        TimeRemaining();
        setInterval(function(){
            TimeRemaining();
        }, 1000)
    }


    StartTimeRemaining();
</script>
</body>
</html>
