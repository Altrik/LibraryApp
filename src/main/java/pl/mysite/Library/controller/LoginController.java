package pl.mysite.Library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
				User userTest = userRepo.findByLogin(login);
				String salt = userTest.getSalt();
				User user = userRepo.findByLoginAndPassword(login, BCrypt.hashpw(password, salt));
			if (user!=null) {
				if (BCrypt.checkpw(password, user.getPassword())) {			
					HttpSession session = request.getSession();
					session.setAttribute("login", login);
					session.setAttribute("password", user.getPassword());
					session.setAttribute("Id", user.getId());
					session.setAttribute("isAdmin", user.isIs_Admin());
					if (user.isIs_Admin()==true) {
						request.getRequestDispatcher("/admin/home").forward(request, response);
					} else {
						request.getRequestDispatcher("/user/borrowedBooks").forward(request, response);
					}
				}
			}
		}
		response.sendRedirect("http://localhost:8080/Library/");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST) 
	public void registerUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String salt = BCrypt.gensalt();
		String password = BCrypt.hashpw(request.getParameter("password"), salt);
		String email = request.getParameter("email");
		User user = new User (login, password, salt, email);
		userRepo.save(user);
		response.sendRedirect("http://localhost:8080/Library/");
	}
	
	@RequestMapping(value="/loginExists/{login}", method=RequestMethod.GET)
	@ResponseBody
	public Boolean doesLoginExist (@PathVariable String login) {
		if (userRepo.findByLogin(login)!=null) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value="/emailExists/{email}", method=RequestMethod.GET)
	@ResponseBody
	public Boolean doesEmailExist (@PathVariable String email) {
		if (userRepo.findByEmail(email.replaceAll("\\$", "\\."))!=null) {
			return true;
		}
		return false;
	}
}
