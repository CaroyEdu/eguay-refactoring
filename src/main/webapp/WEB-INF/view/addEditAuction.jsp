<%@ page import="com.test.eguay.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.test.eguay.dto.AuctionDTO" %><%--
  Created by IntelliJ IDEA.
  User: jean-
  Date: 30/05/2022
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>eguay - Añadir Producto</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/styleaddproduct.css"/>
</head>

<%
    List<CategoryDTO> categoryList = (List) request.getAttribute("categoryList");
    Calendar now = new GregorianCalendar();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    AuctionDTO auction = (AuctionDTO)request.getAttribute("auction");
    Long categoryID = Long.parseLong("0");
    if(auction!=null)
    {
        for(CategoryDTO c : auction.getCategoryList())
        {
            categoryID = c.getId();
        }
    }
%>
<%--<jsp:include page="userConnectedCheck.jsp"/>--%>
<jsp:include page="cabecera.jsp"/>

<body>
<%
    if(auction == null){
%>
<div class="flex-container">
    <h1>Introduce información sobre tu producto:</h1>
</div>
<%
}else{
%>
<div class="flex-container">
    <h1>Modifica la información sobre tu producto:</h1>
</div>
<%
    }
%>

<div class="flex-container">
    <form name="addForm" method="POST" action="insertAuction">
        Título:<input type="text" name="title" value="<%= auction==null? "": auction.getName() %>" required/><br/>
        Descripción:<input type="text" name="description" value="<%= auction==null? "": auction.getDescription() %>"/><br/>
        URL Foto:<input type="text" name="fotourl" value="<%= auction==null? "": auction.getFotourl() %>"/><br/>
        Precio Inicial:<input type="text" name="startprice" value="<%= auction==null? "": auction.getStartPrice() %>" required/><br/>
        <br/>Categoría:
        <select name="category">
            <%  for(CategoryDTO category : categoryList)
            {
            %>
            <option <% if(auction!=null && category.getId()==categoryID){%> selected <%} %> value="<%= category.getId()%>"><%= category.getName()%></option>
            <%
                }
            %>
        </select>
        <br/>
        Elige cómo cerrar tu puja:<br/>
        <input <% if(auction!=null){if(auction.getClosePrice()!=null){ %> checked <%}}%>type="checkbox" id="closePrice" name="checkBoxClosePrice" onclick="checkInput()"/>
        Cerrar cuando llegue a <input type="text" id="inputClosePrice" name="inputClosePrice" value="<% if(auction!=null){if(auction.getClosePrice()!=null){%><%= auction.getClosePrice() %><%}} %>"/> $.
        <br/>

        <input <% if(auction!=null){if(auction.getCloseDate()!=null){ %> checked <%}}%>type="checkbox" id="closeDate" name="checkBoxCloseDate"/>
        Cerrar cuando llegue a la fecha <input type="date" name="inputCloseDate" id="inputCloseDate1" min="<%= sdf.format(now.getTime()) %>" value="<% if(auction!=null){if(auction.getCloseDate()!=null){%><%= sdf.format(auction.getCloseDate()) %><%}} %>"/> <input type="time" id="inputCloseDate2" name="inputCloseDateTime" />
        <br/>

        <input <% if(auction!=null){if(auction.getCloseNumberofBids()!=null){ %> checked <%}}%>type="checkbox" id="closeNumberOfBids" name="checkBoxCloseNumberOfBids"/>
        Cerrar cuando llegue a: <input type="text" id="inputCloseNumberOfBids" name="inputCloseNumberOfBids" value="<% if(auction!=null){if(auction.getCloseNumberofBids()!=null){%><%= auction.getCloseNumberofBids()%><%}} %>"/> pujas.
        <br/>
        <input type="text" name="auctionid" value="<%= auction==null? "": auction.getId() %>" hidden />
        <input type="text" name="active" value="<%= auction==null? "": auction.isActive() %>" hidden />
        <% if(auction!=null){ %>
        <input type="submit" id="checkBtn" value="Editar"/>
        <% }else{ %>
        <input type="submit" id="checkBtn" value="Añadir"/>
        <% } %>
    </form>
</div>
<script>
    document.getElementById("closePrice").addEventListener('change', function(){
        document.getElementById("inputClosePrice").required = this.checked;
    });
    document.getElementById("closeDate").addEventListener('change', function(){
        document.getElementById("inputCloseDate1").required = this.checked;
        document.getElementById("inputCloseDate2").required = this.checked;
    });
    document.getElementById("closeNumberOfBids").addEventListener('change', function(){
        document.getElementById("inputCloseNumberOfBids").required = this.checked;
    });
    document.getElementById("addForm").onsubmit = function() {check()};
    function check(){
        alert("test");
        var closePriceCheck = document.getElementById("closePrice");
        var closeDateCheck = document.getElementById("closePrice");
        var closeNumberOfBidsCheck = document.getElementById("closePrice");
        if(!closePriceCheck.checked && !closeDateCheck.checked && !closeNumberOfBidsCheck)
        {
            document.getElementById("inputClosePrice").required = true;
        }else{
            document.getElementById("inputClosePrice").required = false;
        }
    }
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#checkBtn').click(function() {
            checked = $("input[type=checkbox]:checked").length;

            if(!checked) {
                alert("ERROR: Debes seleccionar al menos una opción de cierre de puja.");
                return false;
            }

        });
    });
</script>
</body>
</html>
