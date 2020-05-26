<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("User");
%>
<form id="editUserForm" action=<c:url value='/admin/editedUser'/> method="post">
    <input type="hidden" name="id" value="<%= user.getId() %>">
    <label>Login
        <input type="text" name="login" value="<%= user.getLogin() %>">
    </label><br>
    <label>Email
        <input type="text" name="email" value="<%= user.getEmail() %>">
    </label><br>
    <label>Admin Status<br>
        <% 
            if (user.isIs_Admin()==true) {
                %>
                <input type="radio" name="isAdmin" value="true" checked>Admin<br>
                <input type="radio" name="isAdmin" value="false">User<br>
                <%
            } else {
                %>
                <input type="radio" name="isAdmin" value="true">Admin<br>
                <input type="radio" name="isAdmin" value="false" checked>User<br>
                <%
            }
        %>
    </label><br>
    <input type="submit" name="send" value="Edit User">
</form>
</body>
</html>