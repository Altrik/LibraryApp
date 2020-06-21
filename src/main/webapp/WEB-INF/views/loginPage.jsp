<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/app.js'/>"></script>
</head>
<body>
<div class="container">
    <h1>Library Application</h1>
    <br><hr><br>
    <form action=<c:url value='/'/> method="post">
    <h3>Sign In</h3> 
    <label>Login
        <input class="ml-1" type="text" name="login" required>
    </label><br>
    <label>Password
        <input class="ml-1" type="password" name="password" required>
    </label><br>
    <input class="btn btn-primary" type="submit" name="send" value="Sign In"><br>
    </form><br><hr><br>
</div>
<div class="container">
    <form id="registerForm" name="registerForm" action=<c:url value='/register'/> method="post"> 
    <h3>Register</h3>
    <label>Login
        <input id="loginField" class="ml-1" type="text" name="login" required>
    </label><br>
    <label>Password
        <input class="ml-1" type="password" name="password" required>
    </label><br>
    <label>E-Mail
        <input id="emailField" class="ml-1" type="text" name="email" required>
    </label><br>
    <input class="btn btn-primary" type="submit" id="submitButton" name="send" value="Register"><br>
    <br><hr>
    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>