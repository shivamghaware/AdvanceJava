package com.voting.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.voting.dao.CandidateDao;
import com.voting.dao.CandidateDaoImpl;
import com.voting.dto.PartyVote;
import com.voting.entities.Candidate;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet(value="/admin_dashboard",loadOnStartup=3)
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDao candidateDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. set resp cont type
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		//2. get writer - to send text resp
		try(PrintWriter pw=response.getWriter()) {
			List<Candidate> candidates=candidateDao.getTop2Candidates();
			List<PartyVote> partvotes=candidateDao.getPartyWiseAnalysis();
			
			pw.print("In admin dashboard...");
			pw.print("<br>");
			pw.print("<br>");
			
			
			if(candidates!=null) {
				pw.print("Top Two Candidates");
				pw.print("<br>");
				for(Candidate c:candidates) {
					pw.print(c.getCandidateId()+"    "+c.getName()+"    "+c.getPartyName()+"    "+c.getVotes());
					pw.print("<br>");
					pw.print("<br>");
				}
				pw.print("<br>");
			}
			
			if(partvotes!=null) {
				pw.print("Party Wise Result");
				pw.print("<br>");
				pw.print("<br>");
				for(PartyVote p:partvotes) {
					pw.print(p.getPartyname()+"   "+p.getVotes());
					pw.print("<br>");
				}
				pw.print("<br>");
			}
			session.invalidate();
			
		}catch (Exception e) {
			throw new ServletException("exception in servlet"+getClass(),e);
		}
		
	}

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
			candidateDao = new CandidateDaoImpl();
			System.out.println("init of " + getClass() + " success");
		} catch (Exception e) {
			throw new ServletException("err in init " + getClass(), e);
		}
	}
	
	

}
