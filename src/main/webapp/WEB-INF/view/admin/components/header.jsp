<%-- 
    Document   : header
    Created on : 14-may-2022, 18:47:46
    Author     : carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String pageName = (String) request.getAttribute("page-name"); %>

<div class="min-vh-100 d-flex align-items-stretch flex-column flex-shrink-0 p-3 text-white bg-dark">
  <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
    <svg class="bi pe-none me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
    <span class="fs-4">Eguay</span>
  </a>
  <hr>
  <ul class="nav nav-pills flex-column mb-auto">
    <li class="nav-item">
        <a href="<%= request.getContextPath() %>/admin/users/" class="nav-link text-white <%= pageName.equals("Usuarios") ? "active" : "" %>" aria-current="page">
        <i class="bi bi-people-fill me-2"></i>
        Usuarios
      </a>
    </li>
    <li>
      <a href="<%= request.getContextPath() %>/admin/products/" class="nav-link text-white <%= pageName.equals("Productos") ? "active" : "" %>">
        <i class="bi bi-box2-fill me-2"></i>
        Productos
      </a>
    </li>
    <li>
      <a href="<%= request.getContextPath() %>/admin/categories/" class="nav-link text-white <%= pageName.equals("Categorias") ? "active" : "" %>">
        <i class="bi bi-grid me-2"></i>
        Categorias
      </a>
    </li>
  </ul>
  <hr>
  <div class="dropdown">
    <a href="<%= request.getContextPath() %>/IndexServlet" class="text-white"><strong>Salir</strong></a>
  </div>
</div>
