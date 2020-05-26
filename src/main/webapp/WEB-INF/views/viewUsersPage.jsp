<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.User, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Users</title>
</head>
<body>
<p>Users View</p>
<% 
    List <User> userList = (List <User>) request.getAttribute("UserList");
%>
<table>
    <tr><th>ID</th><th>Login</th><<th>Email</th><th>Last Log In</th><th>Created</th><th>Admin status</th></tr>
    <% 
        for (User user : userList) {
    %><tr><td><%= user.getId() %></td><td><%= user.getLogin() %></td><td><%= user.getEmail() %></td>
        <td><%= user.getLastLogIn() %></td><td><%= user.getCreated() %></td><td><%= user.isIs_Admin() %></td>
        <td><a href="http://localhost:8080/Library/admin/editUser/<%= user.getId() %>">Edit User</a></td>
        <td><a href="http://localhost:8080/Library/admin/deleteUser/<%= user.getId() %>">Delete User</a></td>
        <td><a href="http://localhost:8080/Library/admin/changePassword/<%= user.getId() %>">Change Password</a></td></tr>
    <%
        }
    %>
</table>
</body>
</html>