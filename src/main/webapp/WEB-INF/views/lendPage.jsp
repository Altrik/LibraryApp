<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lend Page</title>
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>

<%
    Book book = (Book) request.getAttribute("Book");
%>
<table>
<tr><th>Book to lend</th></tr>
<tr><td>ID: <%= book.getId() %></td></tr>
<tr><td>Title: <%= book.getTitle() %></td></tr>
<tr><td>Author: <%= book.getAuthor() %></td></tr>
<tr><td>Release Date: <%= book.getReleaseDate() %></td></tr>
<tr><td>Date of Acquisition: <%= book.getDateOfAcquisition() %></td></tr>
</table>
<form action=<c:url value='http://localhost:8080/Library/admin/lentBook'/> method="post"> 
<label>User's ID
    <input type="number" name="id" required>
</label>
<input type="hidden" name="book_id" value="<%= book.getId() %>">
<input type="submit" name="send" value="Lend Book">
</form>
</body>
</html>