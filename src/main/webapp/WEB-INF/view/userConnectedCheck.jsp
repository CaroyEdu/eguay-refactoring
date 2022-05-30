<%@ page import="com.test.eguay.dto.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: jean-
  Date: 30/05/2022
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if(user == null)
    {
        %>
        <jsp:forward page="login.jsp"/>
        <%
    }
%>
