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
@RequestMapping("/admin") 
public class HomeController {

//dodaæ do sesji passy, zrobiæ szyfrowanie has³a, error page i css
// PAMIÊTAÆ O ZMIANACH W WEB.XML
// zrobiæ osobny controller dla logowania, osobny dla u¿ytkownika i osobny dla admina, z osobnymi mapowaniami ca³ych controllerów i filtrem dla zalogowanych
@Autowired
BookRepository bookRepo;

@Autowired
UserRepository userRepo; // ROZPOCZ¥Æ POJEDYNEK Z FILTREM

@RequestMapping(value="/home") // "/home"
public String home () {
	return "index";
}

@RequestMapping(value="/add", method=RequestMethod.POST) // "/add"
public void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = new Book (request.getParameter("title"), request.getParameter("author"));
	bookRepo.save(book);
	request.getRequestDispatcher("/admin/home").forward(request, response); // "/home"
}

@RequestMapping(value="/delete/{id}", method= {RequestMethod.GET, RequestMethod.POST}) // "/delete/{id}"
public void deleteBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	bookRepo.deleteById(Long.parseLong(id));
	response.sendRedirect("http://localhost:8080/Library/admin/home"); // "/home"
}

@RequestMapping(value="/status/{id}", method= {RequestMethod.GET, RequestMethod.POST}) // "/status/{id}"
public void changeStatusOfBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id)).get(0);
	if (book.getIsBorrowed() == true) {
		book.setIsBorrowed(false);
	} else if (book.getIsBorrowed() == false) {
		book.setIsBorrowed(true);
	}
	bookRepo.save(book);
	response.sendRedirect("http://localhost:8080/Library/admin/home"); // "/home"
}

@RequestMapping(value="/edit/{id}", method={RequestMethod.GET, RequestMethod.POST}) // "/edit/{id}"
public void editBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id)).get(0);
	request.setAttribute("Book", book);
	request.getRequestDispatcher("/admin/editBook").forward(request, response); // "/editBook"
}

@RequestMapping(value="/editBook", method={RequestMethod.GET, RequestMethod.POST})  // "/editBook"
public String editPage () {
	return "editPage"; // "editPage"
}

@RequestMapping(value="/edited", method={RequestMethod.GET, RequestMethod.POST}) //  "/edited"
public void saveEditedBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(request.getParameter("id"))).get(0);
	book.setTitle(request.getParameter("title"));
	book.setAuthor(request.getParameter("author"));
	
	java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
	java.sql.Date fixedSqlDate = new java.sql.Date(sqlDate.getTime()+24*60*60*1000);
	book.setDateOfAcquisition(fixedSqlDate);
	
	bookRepo.save(book);
	response.sendRedirect("http://localhost:8080/Library/admin/home"); // "/home"
}

@RequestMapping(value="/searchById", method=RequestMethod.POST) // "/searchById"
public void searchById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	long id = Long.parseLong(request.getParameter("id"));
	List <Book> bookList = bookRepo.findById(id);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); // "/home"
}

@RequestMapping(value="/searchByAuthor", method=RequestMethod.POST) // "/searchByAuthor"
public void searchByAuthor (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List <Book> bookList = bookRepo.findByAuthor(request.getParameter("author"));
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); // "/home"
}

@RequestMapping(value="/searchByTitle", method=RequestMethod.POST) // "/searchByTitle"
public void searchByTitle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List <Book> bookList = bookRepo.findByTitle(request.getParameter("title"));
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); // "/home"
}

@RequestMapping(value="/searchByDate", method=RequestMethod.POST) // "/searchByDate"
public void searchByDate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
	java.sql.Date fixedSqlDate = new java.sql.Date(sqlDate.getTime()+24*60*60*1000);
	List <Book> bookList = bookRepo.findByDateOfAcquisition(fixedSqlDate);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); // "/home"
}

@RequestMapping(value="/searchByStatus", method=RequestMethod.POST) // "/searchByStatus"
@ResponseBody
public void searchByStatus (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Boolean isBorrowed = Boolean.parseBoolean(request.getParameter("isBorrowed"));
	List <Book> bookList = bookRepo.findByIsBorrowed(isBorrowed);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); // "/home"
}
}
