<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.User, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>View Users</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/app.js'/>"></script>
</head>
<body>
<div class="container">
    <hr>
    <h3>Users View</h3>
    <% 
        List <User> userList = (List <User>) request.getAttribute("UserList");
    %>
    <table class="table">
        <tr><th>ID</th><th>Login</th><th>Email</th><th>Last Log In</th><th>Created</th><th>Admin status</th></tr>
        <% 
            for (User user : userList) {
        %><tr><td><%= user.getId() %></td><td><%= user.getLogin() %></td><td><%= user.getEmail() %></td>
            <td><%= user.getLastLogIn() %></td><td><%= user.getCreated() %></td><td><%= user.isIs_Admin() %></td>
            <td><a href="http://localhost:8080/Library/admin/editUser/<%= user.getId() %>"><button class="btn btn-primary">Edit User</button></a></td>
            <td><a href="http://localhost:8080/Library/admin/deleteUser/<%= user.getId() %>"><button class="btn btn-primary">Delete User</button></a></td>
            <td><a href="http://localhost:8080/Library/admin/changePassword/<%= user.getId() %>"><button class="btn btn-primary">Change Password</button></a></td></tr>
        <%
            }
        %>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>