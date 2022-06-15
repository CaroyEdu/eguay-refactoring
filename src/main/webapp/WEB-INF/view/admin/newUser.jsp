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
                    <form action="SubmitCreate" method="POST">
                        <h3>Datos usuario</h3>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Email</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" name="email">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Nombre usuario</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" name="username">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Nombre</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" name="name">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Apellidos</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" name="surname">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Pais</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" name="country">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Ciudad</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" name="city">
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Direccion</label>
                          <div class="col-sm-6">  
                            <input type="text" class="form-control" name="address">
                          </div>
                        </div>
                         
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Contrasena</label>
                          <div class="col-sm-6">  
                            <input type="password" class="form-control" name="password">
                          </div>
                        </div>
                          
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Nacimiento</label>
                          <div class="col-sm-6">  
                              <input type="text" class="form-control" name="birthday">
                          </div>
                        </div>
                          
                        <div class="row mb-3">
                          <label for="id" class="col-sm-2 col-form-label">Sexo</label>
                          <div class="col-sm-6">  
                            <select class="form-select" aria-label="Default select example" name="sex">
                                <% for(Sex sex : Sex.values()) { %>
                                <option value="<%= sex.getId() %>"><%= sex.getName() %></option>
                                <% } %>
                            </select>
                          </div>
                        </div>
                          
                        <h3>Roles</h3>
                        <div class="form-check">
                          <input name="roleIds" class="form-check-input" id="roleMarketing" type="checkbox" value="2" id="flexCheckDefault">
                          <label class="form-check-label" for="roleMarketing">
                            Marketing
                          </label>
                        </div>
                        <div class="form-check">
                          <input name="roleIds" class="form-check-input" id="roleAnalista" type="checkbox" value="3" id="flexCheckDefault">
                          <label class="form-check-label" for="roleAnalista">
                            Analista
                          </label>
                        </div>
                        <div class="form-check">
                            <input name="roleIds" class="form-check-input" id="roleAdmin" type="checkbox" value="4" id="flexCheckDefault">
                          <label class="form-check-label" for="roleAdmin">
                            Admin
                          </label>
                        </div>
                        <button type="submit" class="btn btn-primary mt-4">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
