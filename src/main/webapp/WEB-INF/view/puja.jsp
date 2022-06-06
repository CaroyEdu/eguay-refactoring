<%@ page import="com.test.eguay.entity.Bid" %>
<%@ page import="com.test.eguay.dto.BidDTO" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<%@ page import="com.test.eguay.dto.UserDTO" %><%--
    Document   : submitBid
    Created on : Apr 19, 2022, 3:29:33 PM
    Author     : Parsa zendehdel nobari
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<%

    BidDTO bid = (BidDTO) request.getAttribute("highestBid");
    AuctionDTO auction = (AuctionDTO) request.getAttribute("auction");
    UserDTO user = (UserDTO) session.getAttribute("user");

%>
<body>
<form name="submitBid" method="POST" action="/showAuction/submitBid/finilizeSubmitingBid">
    <input type="hidden" name="AuctionID" value="<%= auction.getId() %>" />
    <% if (bid == null) { %>
    <h1>No hay puja, Sé primero en pujar este producto </h1><br>
    <h2> La puja debe ser mayor que el precio inicial : <%= auction.getStartPrice()%> </h2>
    <br> <br>

    <h3> Nueva puja <h3> <br>
        Puja:<input type="number" name="Bid" value="<%= auction.getStartPrice()+ 1 %>" required/><br/>
        <br>
        <p> Para el usuario <%= user.getUsername()%> con el nombre <%= user.getName() + "\t" + user.getSurname() + "\t" %> con la direccion <%= user.getAddress() %> y el correro <%= user.getEmail() %> </p>


            <% }else { %>
        <h1>Puja mas alta es : <%= bid.getBid() %> del usuario : <%= bid.getUsersByBiderid().getName()%> </h1><br>

        <h2> La puja debe ser mayor que <%= bid.getBid() %> </h2>
        <br> <br>
        <h3> Nueva puja <h3> <br>
            Puja:<input type=number name="Bid" value="<%= bid.getBid() + 1 %>" min="<%= bid.getBid() + 1 %>" step="0.01" required/><br/>
            <br>
            <p> Para el usuario <%= user.getUsername()%> con el nombre <%= user.getName() + "\t" + user.getSurname() + "\t" %> con la direccion <%= user.getAddress() %> y el correro <%= user.getEmail() %> </p>
                <% } %>
            <input type="submit" id="checkBtn" value="Añadir Puja"/>
</form>
</body>
</html>
