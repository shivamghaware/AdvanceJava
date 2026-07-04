package com.voting.pages;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(value = "/", loadOnStartup = 1)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// routing logic
		//1. get servlet path (url pattern)
		String path=request.getServletPath();
		System.out.println("path - "+path);
		String dispatcherTarget=null;
		switch (path) {
		case "/": //forward the clnt in the SAME request to login.jsp
			dispatcherTarget="/WEB-INF/views/login.jsp";			
			break;

		default:
			break;
		}
		//forward
		RequestDispatcher rd=request.getRequestDispatcher(dispatcherTarget);
		rd.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
