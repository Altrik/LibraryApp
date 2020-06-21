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
<title>Index</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/app.js'/>"></script>
</head>
<body>
<div class="container">
    <hr>
    <h3>Add book</h3>
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
        <input class="btn btn-primary" type="submit" name="send" value="Add book">
    </form>
</div>
<br>
<div class="container">
    <hr>
    <a href="http://localhost:8080/Library/admin/viewUsers"><button class="btn btn-secondary">View Users</button></a>
    <hr>
</div>
<br>
<div class="container">
    <h3>Search for books</h3>
    <form id="formById" class="searchForm" action=<c:url value='/admin/searchById'/> method="post"> 
        <label>ID
            <input type="number" name="id">
            <input  id="searchId" class="btn btn-primary" type="submit" name="send" value="Search by ID"><br>
        </label><br>
    </form>
    <form id="formByUserId" class="searchForm" action=<c:url value='/admin/searchByUserId'/> method="post"> 
        <label>User ID
            <input type="number" name="id">
            <input id="searchUserId" class="btn btn-primary" type="submit" name="send" value="Search by User ID"><br>
        </label><br>
    </form>
    <form id="formByAuthor" class="searchForm" action=<c:url value='/admin/searchByAuthor'/> method="post"> 
        <label>Author
            <input type="text" name="author">
            <input id="searchAuthor" class="btn btn-primary" type="submit" name="send" value="Search by Author"><br>
        </label><br>
    </form>
    <form id="formByTitle" class="searchForm" action=<c:url value='/admin/searchByTitle'/> method="post"> 
        <label>Title
            <input type="text" name="title">
            <input id="searchTitle" class="btn btn-primary" type="submit" name="send" value="Search by Title"><br>
        </label><br>
    </form>
    <form id="formByDate" class="searchForm" action=<c:url value='/admin/searchByDate'/> method="post"> 
        <label>Date of Acquisition
            <input type="date" name="dateOfAcquisition">
            <input id="searchDate" class="btn btn-primary" type="submit" name="send" value="Search by Date of Acquisition">
        </label><br>
    </form>
    <form id="formByReleaseDate" class="searchForm" action=<c:url value='/admin/searchByReleaseDate'/> method="post"> 
        <label>Release Date
            <input type="number" name="releaseDate" min="1900" max="2099" step="1" value="2020" name="releaseDate">
            <input id="searchStatus" class="btn btn-primary" type="submit" name="send" value="Search by Release Date">
        </label><br>
    </form>
    <form id="formByStatus" class="searchForm" action=<c:url value='/admin/searchByStatus'/> method="post"> 
        <label>Status<br>
            <input type="radio" name="isBorrowed" value="true">Borrowed<br>
            <input type="radio" name="isBorrowed" value="false">Not Borrowed<br>
            <input id="searchStatus" class="btn btn-primary" type="submit" name="send" value="Search by Status">
        </label><hr><br>
    </form>
</div>
<div class="container">
    <h3>Search results</h3>
    <div id="searchAnchor"></div>
    <% 
        List <Book> bookList = (List <Book>) request.getAttribute("bookList");
        if (bookList != null) {
            %><div id='tableHeader'><table class="table"><tr><th>ID</th><th>Title</th><th>Author</th><th>Release Date</th><th>Date of Acquisition</th><th>Status</th></tr><%
            for (Book book : bookList) {
                %><tr><td><%= book.getId() %></td><td><%= book.getTitle() %></td><td><%= book.getAuthor() %></td><td><%= book.getReleaseDate() %></td>
                    <td><%= book.getDateOfAcquisition() %></td>
                    <% if (book.getIsBorrowed()==true) {
                    %> <td>Borrowed</td> <%
                    } else {
                    %> <td>Available</td> <%
                    }    
                    %>
                    <td><a href="http://localhost:8080/Library/admin/delete/<%= book.getId() %>"><button class="btn btn-primary">Delete</button></a></td>
                    <% 
                        if (book.getIsBorrowed()==false) { 
                            %><td><a href="http://localhost:8080/Library/admin/status/<%= book.getId() %>"><button class="btn btn-primary">Lend Book</button></a></td><%
                        } else {
                            %><td><a href="http://localhost:8080/Library/admin/return/<%= book.getId() %>"><button class="btn btn-primary">Return Book</button></a></td><%
                        }
                    %>
                    <td><a href="http://localhost:8080/Library/admin/edit/<%= book.getId() %>"><button class="btn btn-primary">Edit</button></a></td></tr><%
            }%></table><%
        }
    %>
</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>