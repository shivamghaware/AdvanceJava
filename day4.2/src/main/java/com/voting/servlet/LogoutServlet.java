package com.voting.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.voting.dao.CandidateDao;
import com.voting.dao.CantidateDaoImpl;
import com.voting.dao.UserDao;
import com.voting.dao.UserDaoImpl;
import com.voting.entites.User;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet(urlPatterns="/logout",loadOnStartup = 4)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private CandidateDao candidateDao;
	
	// this will be invoked when user cast's vote

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try (PrintWriter pw=resp.getWriter()) 
		{
			HttpSession session=req.getSession();
			User user=(User) session.getAttribute("user_details");
			if(user != null)
			{
				pw.print("<h5> Hello "+user.getFirstName()+" "+user.getLastName()+"</h5>");
				String updateVotingStatus = userDao.updateVotingStatus(user.getUserId());
				int cId=Integer.parseInt(req.getParameter("candidateId"));
			
				String incrementVotesStatus = candidateDao.incrementCount(cId);
				pw.print("<h5> You have voted ....</h5>");
				pw.print("<h5> "+incrementVotesStatus+"</h5>");
				session.invalidate();
				pw.print("<h5> You have logged out here....</h5>");				
			}
					
		} catch (Exception e) {
			throw new ServletException("err in do-post : "+getClass(), e);
		}
	}

	@Override
	public void destroy() {
		try {
			userDao.cleanup();
			candidateDao.cleanup();
		} catch (Exception e) {
			System.out.println("error in destroy - " + getClass() + " " + e);
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			// create daos
			userDao = new UserDaoImpl();
			candidateDao = new CantidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init -" + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	// will be invoked if voter has already voted
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
