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

@WebFilter("/admin/*")
public class Login implements Filter {
	
	@Autowired
	UserRepository userRepo;
	
    public Login() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("Filter works");
		//chain.doFilter(request, response);
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		String login = (String) session.getAttribute("login");
		String password = (String) session.getAttribute("password");
		
		System.out.println(login);
		System.out.println(password);
		
		if (login!=null && password!=null) {
			User user = userRepo.findByLoginAndPassword(login, password);
			if (user!=null) {//Zrobiæ admin nie admin
				chain.doFilter(request, response);
			} else {
				System.out.println("Redirect1");
				request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
			}
		}
		System.out.println("Redirect2");
		request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
	    //SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

}
