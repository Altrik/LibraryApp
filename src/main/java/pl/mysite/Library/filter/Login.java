package pl.mysite.Library.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pl.mysite.Library.entity.User;
import pl.mysite.Library.repository.UserRepository;

//@WebFilter("/home/*")
public class Login implements Filter {
	
	@Autowired
	UserRepository userRepo;
	
    public Login() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		System.out.println("Filter works");

		HttpSession session = httpRequest.getSession();
		String login = (String) session.getAttribute("login");
		String password = (String) session.getAttribute("password");
		if (login!=null && password!=null) {
			chain.doFilter(request, response);
			User user = userRepo.findByLoginAndPassword(login, password);
			System.out.println(user);
			if (user!=null) {//Zrobiæ admin nie admin
				chain.doFilter(request, response);
			} else {
				httpResponse.sendRedirect("http://localhost:8080/Library/login");
			}
		}
		chain.doFilter(request, response);
		System.out.println("Redirect inc");
		httpResponse.sendRedirect("http://localhost:8080/Library/");*/
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
