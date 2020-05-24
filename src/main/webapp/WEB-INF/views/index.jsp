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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/app.js'/>"></script>
</head>
<body>
<p id="testJS">Add book</p>
<form action=<c:url value='/admin/add'/> method="post"> 
    <label>Title
        <input type="text" name="title" required>
    </label><br>
    <label>Author
        <input type="text" name="author" required> 
    </label><br>
    <label>Release Date
        <input type="number" name="releaseDate" min="1900" max="2099" step="1" value="2020" required>
    </label><br>
    <input type="submit" name="send" value="Add book">
</form>

<br>
<p>Search for books</p>
<form id="formById" class="searchForm" action=<c:url value='/admin/searchById'/> method="post"> 
    <label>ID
        <input type="number" name="id">
        <input id="searchId" class="searchBtn" type="submit" name="send" value="Search by ID"><br>
    </label><br>
</form>
<form id="formByUserId" class="searchForm" action=<c:url value='/admin/searchByUserId'/> method="post"> 
    <label>User ID
        <input type="number" name="id">
        <input id="searchUserId" class="searchBtn" type="submit" name="send" value="Search by User ID"><br>
    </label><br>
</form>
<form id="formByAuthor" class="searchForm" action=<c:url value='/admin/searchByAuthor'/> method="post"> 
    <label>Author
        <input type="text" name="author">
        <input id="searchAuthor" class="searchBtn" type="submit" name="send" value="Search by Author"><br>
    </label><br>
</form>
<form id="formByTitle" class="searchForm" action=<c:url value='/admin/searchByTitle'/> method="post"> 
    <label>Title
        <input type="text" name="title">
        <input id="searchTitle" class="searchBtn" type="submit" name="send" value="Search by Title"><br>
    </label><br>
</form>
<form id="formByDate" class="searchForm" action=<c:url value='/admin/searchByDate'/> method="post"> 
    <label>Date of Acquisition
        <input type="date" name="dateOfAcquisition">
        <input id="searchDate" class="searchBtn" type="submit" name="send" value="Search by Date of Acquisition">
    </label><br><br>
</form>
<form id="formByReleaseDate" class="searchForm" action=<c:url value='/admin/searchByReleaseDate'/> method="post"> 
    <label>Release Date<br>
        <input type="number" name="releaseDate" min="1900" max="2099" step="1" value="2020" name="releaseDate">
        <input id="searchStatus" class="searchBtn" type="submit" name="send" value="Search by Release Date">
    </label><br><br>
</form>
<form id="formByStatus" class="searchForm" action=<c:url value='/admin/searchByStatus'/> method="post"> 
    <label>Status<br>
        <input type="radio" name="isBorrowed" value="true">Borrowed<br>
        <input type="radio" name="isBorrowed" value="false">Not Borrowed<br>
        <input id="searchStatus" class="searchBtn" type="submit" name="send" value="Search by Status">
    </label><br><br>
</form>
    <p>Search results</p>
    <div id="searchAnchor"></div>
    <% 
        List <Book> bookList = (List <Book>) request.getAttribute("bookList");
        if (bookList != null) {
            %><div id='tableHeader'><table><tr><th>ID</th><th>Title</th><th>Author</th><th>Release Date</th><th>Date of Acquisition</th><th>Status</th></tr><%
            for (Book book : bookList) {
                %><tr><td><%= book.getId() %></td><td><%= book.getTitle() %></td><td><%= book.getAuthor() %></td><td><%= book.getReleaseDate() %></td>
                    <td><%= book.getDateOfAcquisition() %></td><td><%= book.getIsBorrowed() %></td>
                    <td><a href="http://localhost:8080/Library/admin/delete/<%= book.getId() %>">Delete</a></td>
                    <% 
                        if (book.getIsBorrowed()==false) { 
                            %><td><a href="http://localhost:8080/Library/admin/status/<%= book.getId() %>">Lend Book</a></td><%
                        } else {
                            %><td><a href="http://localhost:8080/Library/admin/return/<%= book.getId() %>">Return Book</a></td><%
                        }
                    %>
                    <td><a href="http://localhost:8080/Library/admin/edit/<%= book.getId() %>">Edit</a></td></tr><%
            }%></table><%
        }
    %>
</body>
</html>