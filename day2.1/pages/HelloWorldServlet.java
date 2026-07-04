package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * uses map internally
 * key /hello
 * value pages.HelloWorldServlet
 * 
 * */

@WebServlet("/first")
public class HelloWorldServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// lazy servlet
		System.out.println("init   :" + Thread.currentThread());
	}

	@Override
	public void destroy() {
		System.out.println("destroy :" + Thread.currentThread());
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("do-get" + Thread.currentThread());

		res.setContentType("text/html");

		try (PrintWriter pw = res.getWriter()) {
			pw.print("<h4>Welcome to Servlets!</h4>");
			pw.print("<h5>Time: " + LocalDateTime.now() + "</h5>");
		}

		// 1. set response content type- text/html

		// 2.get the writer to send text response to the client
		/*
		 * Method of ServletResponse public PrintWriter getWriter()throws IOException
		 * 
		 * for binary file public ServletOutputStream getOutputStream() throws
		 * IOException
		 * 
		 */
	}
}
