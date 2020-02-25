package pl.mysite.Library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.mysite.Library.entity.User;
import pl.mysite.Library.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String loginPage () {
		return "loginPage";
	}

	@RequestMapping(value="/", method=RequestMethod.POST) 
	public void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
	
		if (login!=null && password!=null) {
			User user = userRepo.findByLoginAndPassword(login, password);
			if (user!=null) {
				Boolean isAdmin = user.isIs_Admin();
				
				HttpSession session = request.getSession();
				session.setAttribute("login", login);
				session.setAttribute("password", password);
				session.setAttribute("isAdmin", isAdmin);
				if (isAdmin==true) {
					request.getRequestDispatcher("/admin/home").forward(request, response);
				} else {
					request.getRequestDispatcher("/user/home").forward(request, response);
				}
				
			}
		}
		response.sendRedirect("http://localhost:8080/Library/");
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
	
}
