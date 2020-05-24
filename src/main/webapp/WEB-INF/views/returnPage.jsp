<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.Book, pl.mysite.Library.entity.Rental" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Return Page</title>
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>

<%
    Book book = (Book) request.getAttribute("Book");
    Rental rental = (Rental) request.getAttribute("Rental");
    double price = (double) request.getAttribute("Price");
    long days = (long) request.getAttribute("Days");
%>
<table>
<tr><th>Book to return</th></tr>
<tr><td>ID: <%= book.getId() %></td></tr>
<tr><td>Title: <%= book.getTitle() %></td></tr>
<tr><td>Author: <%= book.getAuthor() %></td></tr>
<tr><td>Release Date: <%= book.getReleaseDate() %></td></tr>
<tr><td>Date of Acquisition: <%= book.getDateOfAcquisition() %></td></tr>
<tr><td><hr></td></tr>
<tr><td>Date of Rental: <%= rental.getDayOfRental() %></td></tr>
<tr><td>Days: <%= days %></td></tr>
<tr><td>Price: <%= price %></td></tr>
</table><br>
<a href="http://localhost:8080/Library/admin/returnedBook/<%= book.getId()%>">Return Book</a>
</body>
</html>