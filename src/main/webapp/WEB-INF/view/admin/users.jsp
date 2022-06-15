<%-- 
    Document   : users
    Created on : 14-may-2022, 21:33:45
    Author     : carlos
--%>

<%@page import="java.util.List"%>
<%@ page import="com.test.eguay.dto.UserDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("page-name", "Usuarios"); %>
<% List<UserDTO> users = (List<UserDTO>) request.getAttribute("users"); %>
<% String msg = request.getParameter("msg"); %>
<% String username = request.getParameter("filter"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
        <title>Admin Usuarios</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <jsp:include page="components/header.jsp"/>
                </div>
                <div class="col py-3">
                    <h1>Usuarios <a href="new"><i class="bi bi-plus-square" style="font-size: 1.75rem;"></i></a></h1>
                    <%= msg != null ? msg : "" %>
                    
                    <form action="" method="GET">
                        <label for="username">Filtrar por usuario</label>
                        <input name="filter" id="filter" value="<%= username != null ? username : "" %>"/>
                        <button type="submit" class="mb-4">Filtrar</button>
                    </form>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Usuario</th>
                                <th>Nombre</th>
                                <th>Pais</th>
                                <th>Email</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(UserDTO user : users) { %>
                            <tr>
                                <td><%= user.getId() %></td>
                                <td><%= user.getUsername() %></td>
                                <td><%= user.getName() %></td>
                                <td><%= user.getCountry()%></td>
                                <td><%= user.getEmail() %></td>
                                <td><a href="edit?id=<%= user.getId() %>">Editar</a></td>
                                <td><a href="edit?id=<%= user.getId() %>">Borrar</a></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
