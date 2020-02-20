package pl.mysite.Library.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mysite.Library.repository.UserRepository;
import pl.mysite.Library.repository.BookRepository;
import pl.mysite.Library.entity.Book;
import pl.mysite.Library.entity.User;

@Controller
public class HomeController {

@Autowired
BookRepository bookRepo;

@Autowired
UserRepository userRepo;

@RequestMapping(value="/", method=RequestMethod.GET)
public String loginPage () {
	System.out.println("loginPage");
	return "loginPage";
}

@RequestMapping(value="/", method=RequestMethod.POST)
public void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	System.out.println(login);
	System.out.println(password);
	if (login!=null && password!=null) {
		User user = userRepo.findByLoginAndPassword(login, password);
		//User user = new User("Ala", "Kot", "ala@wp.pl", new java.sql.Date(2020, 02, 02), new java.sql.Date(2020, 01, 01), true);
		System.out.println("Entered 1");
		if (user!=null) {
			System.out.println("Entered 2");
			request.getRequestDispatcher("/home").forward(request, response);
		}
	}
	response.sendRedirect("http://localhost:8080/Library/");
}

@RequestMapping(value="/home")
public String home () {
	return "index";
}

@RequestMapping(value="/home/add", method=RequestMethod.POST)
public void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = new Book (request.getParameter("title"), request.getParameter("author"));
	bookRepo.save(book);
	request.getRequestDispatcher("/home").forward(request, response);
}

@RequestMapping(value="/register", method=RequestMethod.POST)
public void registerUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	User user = new User (login, password, email);
	userRepo.save(user);
	response.sendRedirect("http://localhost:8080/Library/");
}

@RequestMapping(value="/delete/{id}", method= {RequestMethod.GET, RequestMethod.POST})
public void deleteBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	bookRepo.deleteById(Long.parseLong(id));
	response.sendRedirect("http://localhost:8080/Library/home");
}

@RequestMapping(value="/status/{id}", method= {RequestMethod.GET, RequestMethod.POST})
public void changeStatusOfBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id)).get(0);
	if (book.getIsBorrowed() == true) {
		book.setIsBorrowed(false);
	} else if (book.getIsBorrowed() == false) {
		book.setIsBorrowed(true);
	}
	bookRepo.save(book);
	response.sendRedirect("http://localhost:8080/Library/home");
}

@RequestMapping(value="/edit/{id}", method={RequestMethod.GET, RequestMethod.POST})
public void editBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id)).get(0);
	request.setAttribute("Book", book);
	request.getRequestDispatcher("/editBook").forward(request, response);
}

@RequestMapping(value="/editBook", method={RequestMethod.GET, RequestMethod.POST})
public String editPage () {
	return "editPage";
}

@RequestMapping(value="/edited", method={RequestMethod.GET, RequestMethod.POST})
public void saveEditedBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(request.getParameter("id"))).get(0);
	book.setTitle(request.getParameter("title"));
	book.setAuthor(request.getParameter("author"));
	
	java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
	java.sql.Date fixedSqlDate = new java.sql.Date(sqlDate.getTime()+24*60*60*1000);
	book.setDateOfAcquisition(fixedSqlDate);
	
	bookRepo.save(book);
	response.sendRedirect("http://localhost:8080/Library/home");
}

@RequestMapping(value="/searchById", method=RequestMethod.POST)
public void searchById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	long id = Long.parseLong(request.getParameter("id"));
	List <Book> bookList = bookRepo.findById(id);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/home").forward(request, response);
}

@RequestMapping(value="/searchByAuthor", method=RequestMethod.POST)
public void searchByAuthor (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List <Book> bookList = bookRepo.findByAuthor(request.getParameter("author"));
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/home").forward(request, response);
}

@RequestMapping(value="/searchByTitle", method=RequestMethod.POST)
public void searchByTitle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List <Book> bookList = bookRepo.findByTitle(request.getParameter("title"));
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/home").forward(request, response);
}

@RequestMapping(value="/searchByDate", method=RequestMethod.POST)
public void searchByDate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
	java.sql.Date fixedSqlDate = new java.sql.Date(sqlDate.getTime()+24*60*60*1000);
	List <Book> bookList = bookRepo.findByDateOfAcquisition(fixedSqlDate);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/home").forward(request, response);
}

@RequestMapping(value="/searchByStatus", method=RequestMethod.POST)
@ResponseBody
public void searchByStatus (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Boolean isBorrowed = Boolean.parseBoolean(request.getParameter("isBorrowed"));
	List <Book> bookList = bookRepo.findByIsBorrowed(isBorrowed);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/home").forward(request, response);
}
}
