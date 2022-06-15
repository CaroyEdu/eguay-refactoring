<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : users
    Created on : 14-may-2022, 21:33:45
    Author     : carlos
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("page-name", "Usuarios"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
        <title>Crear Usuarios</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <jsp:include page="components/header.jsp"/>
                </div>
                <div class="col py-3">
                    <h1 class="mb-4">Crear usuario</h1>
                    <form:form method="post" action="/admin/users/new" modelAttribute="user">
                        <form:hidden class="form-control" disabled="false" path="id" />
                        <h3>Datos usuario</h3>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="email" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Nombre usuario</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="username" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Nombre</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="name" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Apellidos</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="surname" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Pais</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="country" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Ciudad</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="city" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Direccion</label>
                            <div class="col-sm-6">
                                <form:input class="form-control" path="address" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="id" class="col-sm-2 col-form-label">Contrasena</label>
                            <div class="col-sm-6">
                                <form:password class="form-control" path="password" />
                            </div>
                        </div>

                        <h3>Roles</h3>
                        <div class="form-check">
                            <form:checkbox path="marketing" class="form-check-input" label="Marketing" />
                        </div>
                        <div class="form-check">
                            <form:checkbox path="analista" class="form-check-input" label="Analista" />
                        </div>
                        <div class="form-check">
                            <form:checkbox path="admin" class="form-check-input" label="admin" />
                        </div>

                        <form:button class="btn btn-primary mt-4">Guardar</form:button>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
