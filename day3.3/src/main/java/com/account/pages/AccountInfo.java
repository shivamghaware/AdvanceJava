package com.account.pages;

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
 * Servlet implementation class AccountInfo
 */
@WebServlet(urlPatterns = "/accountInfo",loadOnStartup = 2)
public class AccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountdao;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter pw = response.getWriter()) {
			String id = request.getParameter("acctId");
			Account acc = accountdao.getById(Integer.parseInt(id));

			if (acc != null) {
				pw.write("<h3>" + acc.toString() + "</h3>");
			} 
			else {
				pw.write("<h3> Not Found!!</h3>");
			}
			
		} catch (Exception e) {
			throw new ServletException("Exception in:" + getClass(), e);
		}
	}

	@Override
	public void destroy() {
		try {
			accountdao.cleanup();
		} catch (Exception e) {
			throw new RuntimeException("err in destroy " + getClass(), e);
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			accountdao = new AccountDaoImp();
		} catch (Exception e) {
			throw new ServletException("init exception", e);
		}
	}
	
	

}
