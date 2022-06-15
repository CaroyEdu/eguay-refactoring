<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : editProduct
    Created on : 15-may-2022, 21:19:44
    Author     : carlo
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="com.test.eguay.dto.AuctionDTO" %>
<%@ page import="com.test.eguay.dto.CategoryDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("page-name", "Productos"); %>
<% AuctionDTO producto = (AuctionDTO) request.getAttribute("product"); %>
<% List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories"); %>
<% SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
        <title>Admin Productos</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <jsp:include page="components/header.jsp"/>
                </div>
                <div class="col py-3">
                    <h1 class="mb-4">Editar producto</h1>
                    <%--@elvariable id="product" type="com.test.eguay.dto.AuctionDTO"--%>
                    <form:form method="post" action="/admin/products/edit" modelAttribute="product">
                        <form:hidden class="form-control" disabled="false" path="id" />
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Titulo</label>
                            <div class="col-sm-6">
                                <form:input path="name" class="form-control" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Descripcion</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="description" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">URL Foto</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="fotourl" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Precio inicial</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="startPrice" />
                            </div>
                        </div>

                        Precio Cierre  <br/>

                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Cerrar cuando llegue a</label>
                            <div class="col-sm-2 col-form-labe">
                                <form:input class="form-control" path="closePrice" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Cerrar cuando llegue a pujas</label>
                            <div class="col-sm-2 col-form-labe">
                                <form:input class="form-control" path="closeNumberofBids" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Categoria</label>
                            <div class="col-sm-6">
                                <form:select class="form-select" path="auctionCategory" >
                                <form:options items="${categories}" itemLabel="name" itemValue="id" />
                            </div>
                        </div>
                        Categoria:
                    </form:select>
                        <form:button class="btn btn-primary mt-4">Guardar</form:button>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
