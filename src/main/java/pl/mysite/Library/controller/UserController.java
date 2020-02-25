package pl.mysite.Library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mysite.Library.entity.Book;
import pl.mysite.Library.repository.BookRepository;

@Controller
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	BookRepository bookRepo;
	
	@RequestMapping(value="/home")
	public String home () {
		return "userIndex";
	}
	
	@RequestMapping(value="/searchByAuthor", method=RequestMethod.POST) 
	public void searchByAuthor (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Book> bookList = bookRepo.findByAuthor(request.getParameter("author"));
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/user/home").forward(request, response); 
	}

	@RequestMapping(value="/searchByTitle", method=RequestMethod.POST) 
	public void searchByTitle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Book> bookList = bookRepo.findByTitle(request.getParameter("title"));
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/user/home").forward(request, response); 
	}

	@RequestMapping(value="/searchByDate", method=RequestMethod.POST) 
	public void searchByDate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
		java.sql.Date fixedSqlDate = new java.sql.Date(sqlDate.getTime()+24*60*60*1000);
		List <Book> bookList = bookRepo.findByDateOfAcquisition(fixedSqlDate);
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/user/home").forward(request, response); 
	}

	@RequestMapping(value="/searchByStatus", method=RequestMethod.POST) 
	@ResponseBody
	public void searchByStatus (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean isBorrowed = Boolean.parseBoolean(request.getParameter("isBorrowed"));
		List <Book> bookList = bookRepo.findByIsBorrowed(isBorrowed);
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/user/home").forward(request, response); // "/home"
	}
	
}
