package com.admissions.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.admissions.pojos.Student;

/**
 * Servlet implementation class AdmissionResult
 */
@WebServlet("/result")
public class AdmissionResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set resp cont type
		response.setContentType("text/html");
		// 2. get PW

		PrintWriter pw = response.getWriter();
		// 3. Get student details from request scope
		Student studentDetails = (Student) request.getAttribute("student_details");
		// 4. render the response
		pw.print("<h5> Hello " + studentDetails.getFirstName() + " " + studentDetails.getLastName() + "</h5>");
		if (studentDetails.isAdmitted())
			pw.print("<h5> Congratulations !, You are admitted in Course " + studentDetails.getChosenCourse()
					+ " </h5>");
		else
			pw.print("<h5> Sorry  !, You can't be  admitted in Course " + studentDetails.getChosenCourse() + " </h5>");

	}

}
