package com.catalog.pages;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.catalog.dao.CategoryDaoImp;
import com.catalog.entities.Category;
import com.catalog.dao.CategoryDao;

@WebServlet(urlPatterns = "/home",loadOnStartup = 2 )
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categorydao;

	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext context=config.getServletContext();
			String dbURL=context.getInitParameter("dbURL");
			String userName=context.getInitParameter("userName");
			String password=context.getInitParameter("password");
			categorydao = new CategoryDaoImp(dbURL,userName,password);
		} catch (Exception e) {
			throw new ServletException("Exception in " + getClass(), e);
		}
	}

	public void destroy() {
		try {
			if (categorydao != null) {
			    categorydao.cleanUp();
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception in " + getClass(), e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Category> categories=categorydao.getAllCategories();
			request.setAttribute("category_details", categories);
			
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/home.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Exception in " + getClass(), e);
		}
	
	}

}
