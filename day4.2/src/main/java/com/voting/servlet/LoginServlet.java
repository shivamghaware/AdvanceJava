package com.voting.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.voting.dao.UserDao;
import com.voting.dao.UserDaoImpl;
import com.voting.entites.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns="/login",loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Exception in:" + getClass(), e);
		}
	}

	public void destroy() {
		try {
			userDao.cleanup();
		} catch (Exception e) {
			throw new RuntimeException("Exception in:" + getClass(), e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw=response.getWriter()){
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			
			User user = userDao.authenticateUser(email, password);
			
			if (user == null) {
				pw.print("""
						<h5> Invalid Login Please 
						<a href='login.html'>Retry</a>
						</h5>
						""");
			}else {
				
				HttpSession oldSession = request.getSession();
			    if (oldSession != null) {
			        oldSession.invalidate();
			    }
				
			    HttpSession session = request.getSession(true);
			    
			    session.setAttribute("user_detail", user);
			    
				if(user.getRole().equals("admin")) {
					response.sendRedirect("admin_dashboard");
				}else if(user.isVoted()) {
					response.sendRedirect("logout");
				}else {
					response.sendRedirect("voter_dashboard");
				}
			}
			
		} catch (Exception e) {
			throw new ServletException("Exception in:"+getClass(),e);
		}
	}

}
