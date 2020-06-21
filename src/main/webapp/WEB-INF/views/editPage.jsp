<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.Book, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Edit Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/app.js'/>"></script>
</head>
<body>
<div class="container">
    <hr>
    <h3>Edit Book</h3>
    <form action=<c:url value='http://localhost:8080/Library/admin/edited'/> method="post"> 
    <%
        Book book = (Book) request.getAttribute("Book");
    %>
    <input type="hidden" name="id" value="<%= book.getId() %>">
    <label>Title
        <input type="text" name="title" value="<%= book.getTitle() %>">
    </label><br>
    <label>Author
        <input type="text" name="author" value="<%= book.getAuthor() %>">
    </label><br>
    <label>Release Date
        <input type="number" name="releaseDate" min="1900" max="2099" step="1" value="<%= book.getReleaseDate() %>">
    </label><br>
    <label>Date of Acquisition
        <input type="date" name="dateOfAcquisition" value="<%= book.getDateOfAcquisition() %>">
    </label><br>
    <input class="btn btn-primary" type="submit" name="send" value="Edit book">
    </form><hr>
</div>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>