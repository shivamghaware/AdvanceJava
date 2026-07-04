package com.voting.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/voter_dashboard")
public class VoterDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set resp cont type
		response.setContentType("text/html");
		// 2. get writer - to send text resp
		try (PrintWriter pw = response.getWriter()) {
			pw.print("In voter dashboard...<br/>");
			// 3. get cookies from request header
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				pw.print("""
						<h5> Hello ,
						"""+cookies[0].getValue()+"</h5>");
			} else {
				pw.print("""
						<h5> No Cookies , Session Tracking Failed!!!!!!</h5>
						""");
			}
		}

	}

}
