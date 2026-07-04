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
import com.catalog.dao.ProductDao;
import com.catalog.dao.ProductDaoImp;
import com.catalog.entities.Product;

@WebServlet(urlPatterns ="/products",loadOnStartup = 3)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao productdao;

	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext context=config.getServletContext();
			String dbURL=context.getInitParameter("dbURL");
			String userName=context.getInitParameter("userName");
			String password=context.getInitParameter("password");
			productdao=new ProductDaoImp(dbURL,userName,password);
		} catch (Exception e) {
			throw new ServletException("Exception in-" + getClass(), e);
		}
	}

	public void destroy() {
		try {
			if (productdao != null) {
				productdao.cleanUp();
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception in-" + getClass(), e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId=request.getParameter("categoryId");
			List<Product> products=productdao.getProductsByCategory(Long.parseLong(categoryId));
			request.setAttribute("product_details", products);
			
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/products.jsp");
			rd.forward(request, response);
					
		} catch (Exception e) {
			throw new ServletException("Exception in-" + getClass(), e);
		}
	}

}
