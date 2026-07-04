package com.catalog.pages;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ="/",loadOnStartup = 1)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		String dispatcherTarget=null;
		
		switch (path) {
		case "/": {
			dispatcherTarget="/home";
			break;
		}
		case "/products": {
			dispatcherTarget="/products";
			break;
		}
		default:
			dispatcherTarget="/home";
		}
		
		RequestDispatcher rd=request.getRequestDispatcher(dispatcherTarget);
		rd.forward(request, response);
	}

}
