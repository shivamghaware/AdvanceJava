package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;

import com.voting.dao.UserDao;
import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
/*
 * Map key - /login (url-pattern) Value - com.voting.pages.LoginServlet
 */
@WebServlet(value = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	/**
	 * @see Servlet#init()
	 */
	/*
	 * while overriding | implementing method - can't add new or broader checked
	 * exception
	 */
	@Override
	public void init() throws ServletException {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			// how to inform WC that init has failed - abort servlet life cycle? -
			// Centralized exception handling in Servlets
			/*
			 * public ServletException(String message,Throwable rootCause)
			 */
			throw new ServletException("error in init  of " + getClass(), e);
		}
		System.out.println(getClass() + " init success !");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		//clean up dao
		try {
			userDao.cleanUp();
		} catch (Exception e) {
			throw new RuntimeException("err in destroy "+getClass(),e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set response content type
		response.setContentType("text/html");
		// 2. Get Writer to send text resp to the clnt
		try (PrintWriter pw = response.getWriter()) {
			// 3. get email & password from client request
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// 4. invoke dao's method for user authentication
			User user = userDao.autheticateUser(email, password);
			// 5 null checking
			if (user == null) {
				// invalid login -> retry link
				pw.print("""
						<h5> Invalid Login Please 
						<a href='login.html'>Retry</a>
						</h5>
						""");
			} else {
				//valid login
//				pw.print("""
//						<h5>
//						 Login Successful ,
//						</h5>
//						""");
//				pw.print("<h5> User Details"+user+"</h5>");	
				//1. Create a Cookie to hold valid user name
				Cookie cookie1=new Cookie("user_name", user.getFirstName());
				//2. Add cookie to response header
				response.addCookie(cookie1);
				//role based authorization
				if(user.getRole().equals("admin"))
				{
//					pw.print("redirecting to admin dashboard....");
//					pw.flush();
					//redirect to admin dashboard
					response.sendRedirect("admin_dashboard");
				} else {
					//voter : user
					if(user.isVoted())
					{
						//logout
						response.sendRedirect("logout");
					} else {
						//voter dashboard - candidate list
						response.sendRedirect("voter_dashboard");//temp redirect resp is sent to the client
						/*
						 * 	WC-discards the PW buffer
						 * sends temp redirect response
						 * SC:302 | Header:location-voter_dashboard | cookie: usernameo-abc
						 * body empty
						 * 
						 * 
						 * browser
						 * cookies accepted
						 * redirects request to the server
						 * URL- http://localhost:port/day3.1/voter_dashboard	
						 * */
					}
					
				}
				
			}

		} //JVM - WC -> pw.flush() -> pw.close() -> renders the resp
		catch (Exception e) {
			throw new ServletException("err in do-post of " + getClass(), e);
		}

	}

}
