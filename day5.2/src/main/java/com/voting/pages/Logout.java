package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;

import com.voting.dao.CandidateDao;
import com.voting.dao.CandidateDaoImpl;
import com.voting.dao.UserDao;
import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private CandidateDao candidateDao;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. set content type
		resp.setContentType("text/html");
		//2. get PrintWriter
		try (PrintWriter pw=resp.getWriter()) 
		{
			//3. get HttpSession from WC
			HttpSession session=req.getSession();
			//4. get user details from session
			User user=(User) session.getAttribute("user_details");
			if(user != null)
			{
				//5. greeting mesg
				pw.print("<h5> Hello "+user.getFirstName()+" "+user.getLastName()+"</h5>");
				//6. invoke user dao's method - change voting status
				String updateVotingStatus = userDao.updateVotingStatus(user.getUserId());
				//get candidate id from request parameter
				int cId=Integer.parseInt(req.getParameter("candidateId"));
				
				//8.invoke candidate dao's method  - increment votes
				String incrementVotesStatus = candidateDao.incrementCandidateVotes(cId);
				//9. render messages
				pw.print("<h5> You have voted ....</h5>");
				pw.print("<h5> "+incrementVotesStatus+"</h5>");
				//10. invalidate session
				session.invalidate();
				//11. logout mesg
				pw.print("<h5> You have logged out here....</h5>");				
			}
					
		} catch (Exception e) {
			throw new ServletException("err in do-post : "+getClass(), e);
		}
	}

	@Override
	public void destroy() {
		try {
			userDao.cleanUp();
			candidateDao.cleanUp();
		} catch (Exception e) {
			System.out.println("error in destroy - " + getClass() + " " + e);
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			//1. get servlet context
			ServletContext context=getServletContext();
			//2. get global (ctx) init params from context
			String dbURL=context.getInitParameter("db_url");
			String userName=context.getInitParameter("user_name");
			String password=context.getInitParameter("password");			
	
			// create daos
			userDao = new UserDaoImpl(dbURL,userName,password);
			candidateDao = new CandidateDaoImpl(dbURL,userName,password);
		} catch (Exception e) {
			throw new ServletException("err in init -" + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set resp cont type
		response.setContentType("text/html");
		// 2. get writer - to send text resp
		try (PrintWriter pw = response.getWriter()) {
			//3. get HttpSession from WC
			HttpSession session=request.getSession();
			//4. get user details
			User user=(User) session.getAttribute("user_details");
			if(user != null)
			{
				//5. greeting mesg
				pw.print("<h5> Hello "+user.getFirstName()+" "+user.getLastName()+"</h5>");
				pw.print("<h5> You have already voted !!!!!!</h5>");
				//6. Invalidate HttpSession
				session.invalidate();
				//7. logout mesg
				pw.print("<h5> You have logged out here....</h5>");
				
			}
		}

	}

}
