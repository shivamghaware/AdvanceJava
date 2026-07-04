package com.calcuate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		int num1=Integer.parseInt(request.getParameter("num1"));
		int num2=Integer.parseInt(request.getParameter("num2"));
		String operation=request.getParameter("action");
		
		if(operation!=null && operation!="") {
			try(PrintWriter pw=response.getWriter()){
				switch (operation) {
				case "add": {	
					pw.write("<h3> Addition is:"+(num1+num2)+"</h3>");
					break;
				}
				case "subtract": {	
					pw.write("<h3> subtract is:"+(num1-num2)+"</h3>");
					break;
				}
				case "multiply": {	
					pw.write("<h3> multiply is:"+(num1*num2)+"</h3>");
					break;
				}
				case "divide": {	
					pw.write("<h3> divide is:"+(num1/num2)+"</h3>");
					break;
				}
				default:
					pw.write("<h3> cannot perform the operstion</h3>");
				}
			}
		}
	}

}
