<%@ page import="com.test.eguay.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.entity.Category" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>Categorias Favoritas</title>

<jsp:include page="cabecera.jsp" />

<body>
<h1 class="wrapper">Datos del cliente</h1>
<br>


<%
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categoryList");
    List<CategoryDTO> favCategories = (List<CategoryDTO>)request.getAttribute("favCategoryList");
//    /EditFavCategory/save
    for(CategoryDTO category : categories){ %>

    <%
        if((favCategories != null) && (favCategories.contains(category))) {
    %>

        <input  type="checkbox" id="<%= category.getId().toString()%>" name="<%= category.getId().toString()%>" value="<%= category.getName() %>" checked=checked>
        <label for="<%= category.getId().toString() %>"> <%= category.getName() %> </label>
        <% }else{ %>
        <input type="checkbox" id="<%= category.getId().toString()%>" name="<%= category.getId().toString()%>" value="<%= category.getName() %>" >
        <label for="<%= category.getId().toString() %>"> <%= category.getName() %> </label>
<% } %>

    <input class="wrapper" type="button" onclick="window.location.href='/EditFavCategory/save/<%= category.getId() %>';" value="Editar Categorias Favoria" />
   <% } %>


</body>
