package com.voting.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.voting.utils.DBUtils.getConnection;

import com.voting.entities.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	//fields
	private Connection connection;
	private PreparedStatement pst1,pst2;
	
	public CandidateDaoImpl() throws SQLException{
		// get cn form DBUtils
		connection=getConnection();
		//pst1
		pst1=connection.prepareStatement("select * from candidates");
		//update votes
		pst2=connection.prepareStatement("update candidates set votes=votes+1 where id=?");
		System.out.println("candidate dao created !");
	}
	

	@Override
	public void cleanUp() throws SQLException {
		//close psts
		if(pst1 != null)
		{
			pst1.close();
		}
		if(pst2 != null)
		{
			pst2.close();
		}
		//close cn
		if(connection != null)
		{
			connection.close();
		}
		System.out.println("candidate dao cleaned up !");

	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		List<Candidate> candidates=new ArrayList<>();
		//exec query  -> RestultSet -> process it
		try(ResultSet rst=pst1.executeQuery()) {
			while(rst.next())
			{
				//candidateId, String name, String partyName, int votes
				candidates.add(new Candidate(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		return candidates;
	}


	@Override
	public String incrementCandidateVotes(int candidateId) throws SQLException {
		// set IN param
		pst2.setInt(1, candidateId);
		int updateCount=pst2.executeUpdate();
		if(updateCount == 1)
			return "Votes updated !";
		return "Updating votes failed !!!!!!!!";
	}
	

}
