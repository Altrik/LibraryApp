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
import javax.servlet.http.HttpSession;


@WebFilter("/admin/*")
public class Login implements Filter {

	
    public Login() {
    }
    
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		HttpSession session = httpRequest.getSession();
		String login = (String) session.getAttribute("login");
		String password = (String) session.getAttribute("password");
		Boolean isAdmin	=	(Boolean) session.getAttribute("isAdmin");
		
		if (login!=null && password!=null && isAdmin==true) {
			chain.doFilter(request, response);	
		} else {
			request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
		}
		//request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
	}

}
