<%@ page import="com.test.eguay.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<jsp:include page="cabecera.jsp"/>

<h1>Mis categorias favoritas</h1><br> <hr><br>
<%
    List<CategoryDTO> myFavCats = (List<CategoryDTO>) request.getAttribute("favCategories");
    if(myFavCats != null){
        for(CategoryDTO cat : myFavCats){
%>

        <h3>
            <%= cat.getName() %>
        </h3> <br>

<%
    }}
%>