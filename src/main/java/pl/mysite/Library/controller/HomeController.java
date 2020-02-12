package pl.mysite.Library.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mysite.Library.repository.UserRepository;
import pl.mysite.Library.repository.BookRepository;
import pl.mysite.Library.entity.Book;

@Controller
public class HomeController {

@Autowired
BookRepository bookRepo;

@Autowired
UserRepository userRepo;
	
@RequestMapping(value="/")
public String home () {
	return "index";
}
@RequestMapping(value="/add", method=RequestMethod.POST)
public void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = new Book (request.getParameter("title"), request.getParameter("author"));
	bookRepo.save(book);
	request.getRequestDispatcher("/").forward(request, response);
}

@RequestMapping(value="/searchById", method=RequestMethod.POST)
@ResponseBody
public List <Book> bookListById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("ID Java");
	long id = Long.parseLong(request.getParameter("id"));
	return bookRepo.findById(id); 
}

@RequestMapping(value="/searchByAuthor", method=RequestMethod.POST)
@ResponseBody
public List <Book> bookListByAuthor (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Author Java");
	return bookRepo.findByAuthor(request.getParameter("author"));
}

@RequestMapping(value="/searchByTitle", method=RequestMethod.POST)
@ResponseBody
public List <Book> bookListByTitle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Title Java");
	return bookRepo.findByTitle(request.getParameter("title"));
}

@RequestMapping(value="/searchByDate", method=RequestMethod.POST)
@ResponseBody
public List <Book> bookListByDate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Date Java");
	java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
	System.out.println(sqlDate);
	return bookRepo.findByDateOfAcquisition(sqlDate);
}

@RequestMapping(value="/searchByStatus", method=RequestMethod.POST)
@ResponseBody
public List <Book> bookListByStatus (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Status Java");
	Boolean isBorrowed = Boolean.parseBoolean(request.getParameter("isBorrowed"));
	return bookRepo.findByIsBorrowed(isBorrowed);
}

}
