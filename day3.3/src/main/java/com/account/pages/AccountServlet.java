package com.account.pages;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.account.dao.AccountDao;
import com.account.dao.AccountDaoImp;
import com.entities.Account;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet(urlPatterns = "/account_details", loadOnStartup = 1)
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String acctIdParam = request.getParameter("acctId");
		RequestDispatcher rd = request.getRequestDispatcher("/account_details");
		RequestDispatcher rd2 = request.getRequestDispatcher("/accountInfo");

		if (acctIdParam == null || acctIdParam.trim().isEmpty()) {
			rd.forward(request, response);
			return;
		}
		rd2.forward(request, response);				
			
	}

}
