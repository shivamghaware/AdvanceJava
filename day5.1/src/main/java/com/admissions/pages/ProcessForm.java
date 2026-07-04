package com.admissions.pages;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.StubNotFoundException;

import com.admissions.pojos.Course;
import com.admissions.pojos.Student;

/**
 * Servlet implementation class ProcessForm
 */
@WebServlet("/process_admission")
public class ProcessForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. set resp cont type
		response.setContentType("text/html");
		//2. get PW
		try(PrintWriter pw=response.getWriter()) {
			//3. get request paramters from the client
			String firstName=request.getParameter("fn");
			String lastName=request.getParameter("ln");
			int studentScore=Integer.parseInt(request.getParameter("score"));
			Course selectedCourse=Course.valueOf(request.getParameter("course").toUpperCase());
			//4. Create student pojo
			Student student=new Student(firstName, lastName, studentScore, selectedCourse);
			//5 check  for score & grant | reject admission
			if(student.getScore() >= selectedCourse.getMinScore())
				student.setAdmitted(true);
			//6. Store student details under request scope
			request.setAttribute("student_details", student);
			pw.print("from 1st page ...");
			pw.flush(); //Not required to be called explicitly 
			//7. Get RequestDispatcher - to dispatch the SAME request to the next page
			RequestDispatcher rd=request.getRequestDispatcher("result");
			//8. forward|include the client to the next page in the SAME request
			rd.include(request, response);
			System.out.println("control came back to the 1st page....");
			pw.print("<h5>contents after include....</h5>");
		}
	}

}
