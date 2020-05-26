<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("User");
%>
<form id="changePasswordForm" action=<c:url value='/admin/changedPassword'/> method="post">
<label>New Password
    <input type="password" name="newPassword" required>
</label><br>
    <input type="hidden" name="id" value="<%= user.getId() %>">
    <input type="submit" name="send" value="Change Password">
</form>
</body>
</html>