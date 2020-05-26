package pl.mysite.Library.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mysite.Library.repository.BookRepository;
import pl.mysite.Library.repository.RentalRepository;
import pl.mysite.Library.repository.UserRepository;
import pl.mysite.Library.entity.Book;
import pl.mysite.Library.entity.Rental;
import pl.mysite.Library.entity.User;

@Controller
@RequestMapping("/admin") 
public class HomeController {
	
@Autowired
BookRepository bookRepo;

@Autowired
UserRepository userRepo;

@Autowired
RentalRepository rentalRepo;

@RequestMapping(value="/home")
public String home () {
	return "index";
}

@RequestMapping(value="/add", method=RequestMethod.POST) 
public void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = new Book (request.getParameter("title"), request.getParameter("author"), Integer.parseInt(request.getParameter("releaseDate")));
	bookRepo.save(book);
	request.getRequestDispatcher("/admin/home").forward(request, response); 
}

@RequestMapping(value="/delete/{id}", method= {RequestMethod.GET, RequestMethod.POST}) 
public void deleteBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	bookRepo.deleteById(Long.parseLong(id));
	response.sendRedirect("http://localhost:8080/Library/admin/home"); 
}

@RequestMapping(value="/status/{id}", method= {RequestMethod.GET, RequestMethod.POST})
public void changeStatusOfBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id));
	request.setAttribute("Book", book);
	request.getRequestDispatcher("/admin/lendBook").forward(request, response);
}

@RequestMapping(value="/lendBook", method={RequestMethod.GET, RequestMethod.POST})  
public String lendPage () {
	return "lendPage"; 
}

@RequestMapping(value="/lentBook", method= {RequestMethod.GET, RequestMethod.POST})
public void lentBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong((String) request.getParameter("book_id")));
	User user = userRepo.findById(Long.parseLong((String) request.getParameter("id")));
	
	book.setIsBorrowed(true);
	Rental rental = new Rental(book, user);
	
	bookRepo.save(book);
	rentalRepo.save(rental);
	response.sendRedirect("http://localhost:8080/Library/admin/home");
}

@RequestMapping(value="return/{id}", method = {RequestMethod.GET, RequestMethod.POST})
public void returnPage (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id));
	Rental rental = rentalRepo.findByBook(book);
	
	LocalDate currentDate = LocalDate.now();
	String [] dateOfRentalTokens = rental.getDayOfRental().toString().split("-");
	LocalDate dateOfRental = LocalDate.of(Integer.parseInt(dateOfRentalTokens[0]), Integer.parseInt(dateOfRentalTokens[1]), Integer.parseInt(dateOfRentalTokens[2]));
		
	long days = dateOfRental.until(currentDate, ChronoUnit.DAYS);

	System.out.println(days);
	
	Double price = 0.0;
	
	if (days>14) {
		price += 0.1*(days-14);
	} 
	if (days>21) {
		price += 0.15*(days-21);
	}
	if (days>28) {
		price += 0.25*(days-28);
	}

	request.setAttribute("Days", days);
	request.setAttribute("Price", price);
	request.setAttribute("Book", book);
	request.setAttribute("Rental", rental);
	request.getRequestDispatcher("/admin/returnBook").forward(request, response);
}

@RequestMapping(value="/returnBook", method={RequestMethod.GET, RequestMethod.POST})  
public String returnPage () {
	return "returnPage"; 
}

@RequestMapping(value="/returnedBook/{id}", method= {RequestMethod.GET, RequestMethod.POST})
public void returnBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id));
	Rental rental = rentalRepo.findByBook(book);
	book.setIsBorrowed(false);
	rentalRepo.delete(rental);
	bookRepo.save(book);
	response.sendRedirect("http://localhost:8080/Library/admin/home");
}


@RequestMapping(value="/viewUsers", method= {RequestMethod.GET, RequestMethod.POST})
public void viewUsers (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List <User> userList = userRepo.findAll();
	request.setAttribute("UserList", userList);
	request.getRequestDispatcher("/admin/viewUsersPage").forward(request, response); 
}

@RequestMapping(value="/viewUsersPage", method= {RequestMethod.GET, RequestMethod.POST})
public String viewUsersPage () {
	return "viewUsersPage";
}

@RequestMapping(value="/editUser/{id}", method= {RequestMethod.GET, RequestMethod.POST})
public void editUsers (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("User", userRepo.findById(Long.parseLong(id)));
	request.getRequestDispatcher("/admin/editUserPage").forward(request, response); 
}

@RequestMapping(value="/editUserPage", method= {RequestMethod.GET, RequestMethod.POST})
public String editUserPage () {
	return "editUserPage";
}
 
@RequestMapping(value="/editedUser", method= {RequestMethod.GET, RequestMethod.POST})
public void editedUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	User user = userRepo.findById(Long.parseLong(request.getParameter("id")));
	user.setLogin(request.getParameter("login"));
	user.setEmail(request.getParameter("email"));
	if (request.getParameter("isAdmin").equals("true")) {
		user.setIs_Admin(true);
	} else {
		user.setIs_Admin(false);
	}
	userRepo.save(user);
	response.sendRedirect("http://localhost:8080/Library/admin/home");
}

@RequestMapping(value="/changePassword/{id}", method= {RequestMethod.GET, RequestMethod.POST}) 
public void changePassword (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("User", userRepo.findById(Long.parseLong(id)));
	request.getRequestDispatcher("/admin/changePasswordPage").forward(request, response);
}

@RequestMapping(value="/changePasswordPage", method= {RequestMethod.GET, RequestMethod.POST})
public String editPasswordPage () {
	return "editPasswordPage";
}

@RequestMapping(value="/changedPassword", method= {RequestMethod.GET, RequestMethod.POST}) 
public void changedPassword (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	User user = userRepo.findById(Long.parseLong(request.getParameter("id")));
	user.setPassword(request.getParameter("newPassword"));
	userRepo.save(user);
	response.sendRedirect("http://localhost:8080/Library/admin/home");
}

@RequestMapping(value="/deleteUser/{id}", method= {RequestMethod.GET, RequestMethod.POST}) 
public void deleteUser (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	userRepo.deleteById(Long.parseLong(id));
	response.sendRedirect("http://localhost:8080/Library/admin/home"); 
}

@RequestMapping(value="/edit/{id}", method={RequestMethod.GET, RequestMethod.POST})
public void editBook (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(id));
	request.setAttribute("Book", book);
	request.getRequestDispatcher("/admin/editBook").forward(request, response); 
}

@RequestMapping(value="/editBook", method={RequestMethod.GET, RequestMethod.POST})  
public String editPage () {
	return "editPage"; 
}

@RequestMapping(value="/edited", method={RequestMethod.GET, RequestMethod.POST}) 
public void saveEditedBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Book book = bookRepo.findById(Long.parseLong(request.getParameter("id")));
	book.setTitle(request.getParameter("title"));
	book.setAuthor(request.getParameter("author"));
	book.setReleaseDate(Integer.parseInt(request.getParameter("releaseDate")));
	
	java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
	java.sql.Date fixedSqlDate = new java.sql.Date(sqlDate.getTime()+24*60*60*1000);
	book.setDateOfAcquisition(fixedSqlDate);
	
	bookRepo.save(book);
	response.sendRedirect("http://localhost:8080/Library/admin/home"); 
}

@RequestMapping(value="/searchById", method=RequestMethod.POST) 
public void searchById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	long id = Long.parseLong(request.getParameter("id"));
	Book book = bookRepo.findById(id);
	request.setAttribute("bookList", book);
	request.getRequestDispatcher("/admin/home").forward(request, response);
}

@RequestMapping(value="/searchByUserId", method=RequestMethod.POST) 
public void searchByUserId (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	long id = Long.parseLong(request.getParameter("id"));
	List <Rental> rentalList = rentalRepo.findByUser(userRepo.findById(id));
	List <Book> bookList = new ArrayList<>();
	for (Rental rental : rentalList) {
		bookList.add(rental.getBook());
	}
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response);
}

@RequestMapping(value="/searchByAuthor", method=RequestMethod.POST) 
public void searchByAuthor (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List <Book> bookList = bookRepo.findByAuthor(request.getParameter("author"));
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); 
}

@RequestMapping(value="/searchByTitle", method=RequestMethod.POST) 
public void searchByTitle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List <Book> bookList = bookRepo.findByTitle(request.getParameter("title"));
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); 
}

@RequestMapping(value="/searchByDate", method=RequestMethod.POST) 
public void searchByDate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	java.sql.Date sqlDate = java.sql.Date.valueOf(request.getParameter("dateOfAcquisition"));
	java.sql.Date fixedSqlDate = new java.sql.Date(sqlDate.getTime()+24*60*60*1000);
	List <Book> bookList = bookRepo.findByDateOfAcquisition(fixedSqlDate);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); 
}

@RequestMapping(value="/searchByStatus", method=RequestMethod.POST) 
@ResponseBody
public void searchByStatus (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Boolean isBorrowed = Boolean.parseBoolean(request.getParameter("isBorrowed"));
	List <Book> bookList = bookRepo.findByIsBorrowed(isBorrowed);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response); 
}

@RequestMapping(value="/searchByReleaseDate", method=RequestMethod.POST) 
@ResponseBody
public void searchByReleaseDate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int releaseDate = Integer.parseInt(request.getParameter("releaseDate"));
	List <Book> bookList = bookRepo.findByReleaseDate(releaseDate);
	request.setAttribute("bookList", bookList);
	request.getRequestDispatcher("/admin/home").forward(request, response);
}

}
