<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Lend Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/app.js'/>"></script>
</head>
<body>
<div class="container">
    <%
        Book book = (Book) request.getAttribute("Book");
    %>
    <table class="table">
    <tr><th>Book to lend</th></tr>
    <tr><td><strong>ID:</strong> <%= book.getId() %></td></tr>
    <tr><td><strong>Title:</strong> <%= book.getTitle() %></td></tr>
    <tr><td><strong>Author:</strong> <%= book.getAuthor() %></td></tr>
    <tr><td><strong>Release Date:</strong> <%= book.getReleaseDate() %></td></tr>
    <tr><td><strong>Date of Acquisition:</strong> <%= book.getDateOfAcquisition() %></td></tr>
    </table>
    <form action=<c:url value='http://localhost:8080/Library/admin/lentBook'/> method="post"> 
    <label>User's ID
        <input type="number" name="id" required>
    </label><br>
    <input type="hidden" name="book_id" value="<%= book.getId() %>">
    <input class="btn btn-primary" type="submit" name="send" value="Lend Book">
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>