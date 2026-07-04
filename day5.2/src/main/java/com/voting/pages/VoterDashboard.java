package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.voting.dao.CandidateDao;
import com.voting.dao.CandidateDaoImpl;
import com.voting.dao.UserDaoImpl;
import com.voting.entities.Candidate;
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
@WebServlet("/voter_dashboard")
public class VoterDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDao candidateDao;

	@Override
	public void destroy() {
		try {
			candidateDao.cleanUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
			candidateDao = new CandidateDaoImpl(dbURL,userName,password);

			System.out.println("init of " + getClass() + " success");
		} catch (Exception e) {
			throw new ServletException("err in init " + getClass(), e);
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
			// 3. Get HttpSession from WC
			HttpSession hs=request.getSession();
			System.out.println("From voter dashboard - Session new "+hs.isNew());//false
			System.out.println("Session ID "+hs.getId());//same session id
			//4. get user details from HttpSession
			User userDetails=(User)hs.getAttribute("user_details");
			if(userDetails != null) {
				pw.print("<h5> Hello , "+userDetails.getFirstName()+" "+userDetails.getLastName()+"</h5>");
				//5. Invoke dao's method to get candidate list
				List<Candidate> candidates = candidateDao.getAllCandidates();
				//6 render form
				pw.print("<form action='logout' method='post'>");
				for(Candidate c :candidates)
				{
					//radio button - <input type='radio' name='candidateId' value='....'> name & party />
					pw.print("<input type='radio' name='candidateId' value='"+c.getCandidateId()+"'>"									
							+c.getName()+" "+c.getPartyName()+"<br/>"
							);
				}
				//submit btn
				pw.print("<input type='submit' value='Vote'/>");
				pw.print("</form>");
			}		
			
			else {
				pw.print("""
						<h5> No Cookies , Session Tracking Failed!!!!!!</h5>
						""");
			}
		} catch (Exception e) {
			throw new ServletException("err in do-get of "+getClass(), e);
		}

	}

}
