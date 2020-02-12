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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/app.js'/>"></script>
</head>
<body>
<p id="testJS">Add book</p>
<form action=<c:url value='/add'/> method="post">
    <label>Title
        <input type="text" name="title" required>
    </label>
    <br>
    <label>Author
        <input type="text" name="author" required> 
    </label>
    <br>
    <input type="submit" name="send" value="Add book">
</form>
<!-- dodać wyszukiwanie i po wyszukaniu edit i delete -->
<br>
<p>Search for books</p>
<form action=<c:url value='/searchById'/> method="post">
    <label>ID
        <input type="number" name="id">
        <input id="searchId" class="searchBtn" type="submit" name="send" value="Search by ID"><br>
    </label><br>
</form>
<form action=<c:url value='/searchByAuthor'/> method="post">
    <label>Author
        <input type="text" name="author">
        <input id="searchAuthor" class="searchBtn" type="submit" name="send" value="Search by Author"><br>
    </label><br>
</form>
<form action=<c:url value='/searchByTitle'/> method="post">
    <label>Title
        <input type="text" name="title">
        <input id="searchTitle" class="searchBtn" type="submit" name="send" value="Search by Title"><br>
    </label><br>
</form>
<form action=<c:url value='/searchByDate'/> method="post">
    <label>Date of Acquisition
        <input type="date" name="dateOfAcquisition">
        <input id="searchDate" class="searchBtn" type="submit" name="send" value="Search by Date of Acquisition">
    </label><br><br>
</form>
<form action=<c:url value='/searchByStatus'/> method="post">
    <label>Status<br>
        <input type="radio" name="isBorrowed" value="true">Borrowed<br>
        <input type="radio" name="isBorrowed" value="false">Not Borrowed<br>
        <input id="searchStatus" class="searchBtn" type="submit" name="send" value="Search by Status">
    </label><br><br>
</form>
    <p id="searchAnchor">Search results</p>
</body>
</html>