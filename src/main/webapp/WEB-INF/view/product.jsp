<%--
    Document   : product
    Created on : 12-abr-2022, 21:24:03
    Author     : Parsa zendehdel nobari
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
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
<div class="product-container">
    <div class="col">
        <img src="<%= auction.getFotourl() %>" style="width: 100%; height: 100%">
    </div>
    <div class="col">
        <h1><%= auction.getName() %></h1>
        <p><%=auction.getDescription()%></p>
        <%if(auction.getActualBid()!=null){%>
        <label style="margin-left: 15px">Puja Actual: </label><label style="font-size:18px;font-weight: bold;color: #fbb72c;"><%=auction.getActualBid()%>€</label><br/><br/>
        <%
            }else{
        %>
        <label style="margin-left: 15px">Puja Actual: </label><label style="font-size:18px;font-weight: bold;color: #fbb72c;"><%=auction.getStartPrice()%>€</label><br/><br/>
        <%
            }
        %>
        <label style="font-weight: bold; margin-left: 15px">Disponibilidad:</label>
        <%if(auction.isActive()){%>
        <label style="color: green">En Stock</label>
        <%
            }else{
        %>
        <label style="color: red">Fuera de Stock</label>
        <%
            }
        %>
        <br/>
        <label style="font-weight: bold; margin-left: 15px">Vendedor:</label> <%=auction.getSeller()%>
        <p>Opciones de cierre de puja:</p>
        <% if(auction.getCloseDate()!=null)
        { %>
        <p id="cd_<%= closeDate %>" style="color:#c82333; font-weight: bold"></p>
        <% } %>
        <% if(auction.getCloseNumberofBids()!=null)
        { %>
        <p style="font-weight: bold">¡Sólo quedan <%= auction.getCloseNumberofBids() %> pujas disponibles!</p>
        <% } %>
        <% if(auction.getClosePrice()!=null)
        { %>
        <p style="font-weight: bold">¡Puja <%= auction.getClosePrice() %>$ y te lo llevas!</p>
        <% } %>
        <div style="margin: 25px 0px 0px 75px">
            <button class="button-53" onclick="window.location.href='/showAuction/purchaseAuction/<%= auction.getId() %>';">
                Puja Directa</button>
            <button class="button-53" onclick="window.location.href='/showAuction/submitBid/<%= auction.getId() %>';"> Añadir Puja </button>
        </div>
    </div>
</div>


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
