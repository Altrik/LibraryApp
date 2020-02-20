<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>
<p>Sign In</p>
<form action=<c:url value='/'/> method="post">
<label>Login
    <input type="text" name="login">
</label><br>
<label>Password
    <input type="password" name="password">
</label><br>
<input type="submit" name="send" value="Sign In"><br>
</form>
<form action=<c:url value='/register'/> method="post">
<p>Register</p>
<label>Login
    <input type="text" name="login">
</label><br>
<label>Password
    <input type="password" name="password">
</label><br>
<label>E-Mail
    <input type="email" name="email">
</label><br>
<input type="submit" name="send" value="Register"><br>
</form>
</body>
</html>