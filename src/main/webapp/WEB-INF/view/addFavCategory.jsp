<%@ page import="com.test.eguay.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.eguay.entity.Category" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>Categorias Favoritas</title>


<jsp:include page="cabecera.jsp" />

<head>
    <link rel="stylesheet" href="/css/favcategory.css"/>
</head>

<body>
<h1 class="wrapper">Datos del cliente</h1>
<br>

<%
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categoryList");
    List<CategoryDTO> favCategories = (List<CategoryDTO>)request.getAttribute("favCategoryList");

    %>
<div class="options">
<form:form modelAttribute="auxFavCategory" action="/EditFavCategory/save" method="post">
    <form:checkboxes  delimiter="<br/>" path="favCategories" items="${categoryList}" itemValue="id" itemLabel="name"></form:checkboxes> <br>
    </div>
    <br>

    <form:button>Añadir</form:button>

</form:form>


</body>
