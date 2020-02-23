<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pl.mysite.Library.entity.Book, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>
<form action=<c:url value='/edited'/> method="post"> 
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
<label>Date of Acquisition
    <input type="date" name="dateOfAcquisition" value="<%= book.getDateOfAcquisition() %>">
</label><br>
<input type="submit" name="send" value="Edit book">
</form>
</body>
</html>