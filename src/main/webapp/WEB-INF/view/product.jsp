<%--
    Document   : product
    Created on : 12-abr-2022, 21:24:03
    Author     : Parsa zendehdel nobari
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EGUAY - Producto</title>
</head>

<jsp:include page="userConnectedCheck.jsp"/>
<jsp:include page="cabecera.jsp"/>

<%
    AuctionDTO auction = (AuctionDTO) request.getAttribute("auction");

    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
    String closeDate = "";
    if(auction.getCloseDate() != null){
        closeDate = sdf.format(auction.getCloseDate());
    }
%>



<body>
<h1><%= auction.getName() %></h1>
<img src="<%= auction.getFotourl() %>">
<p>Cierre(s) de puja:</p>
<% if(auction.getCloseDate()!=null)
{ %>
<p class="description" id="cd_<%= closeDate %>"></p>
<% } %>
<% if(auction.getCloseNumberofBids()!=null)
{ %>
<p class="description">¡Sólo quedan <%= auction.getCloseNumberofBids() %> pujas disponibles!</p>
<% } %>
<% if(auction.getClosePrice()!=null)
{ %>
<p class="description" >¡Puja <%= auction.getClosePrice() %>$ y te lo llevas!</p>
<% } %>
<button onclick="window.location.href='/showAuction/purchaseAuction/<%= auction.getId() %>';">
    Puja Directa</button>
<button onclick="window.location.href='/showAuction/submitBid/<%= auction.getId() %>';"> Puja </button>


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
