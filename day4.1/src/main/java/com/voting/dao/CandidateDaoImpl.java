package com.voting.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.voting.utils.DBUtils.getConnection;

import com.voting.dto.PartyVote;
import com.voting.entities.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	//fields
	private Connection connection;
	private PreparedStatement pst1,pst2,pst3,pst4;
	
	public CandidateDaoImpl() throws SQLException{
		// get cn form DBUtils
		connection=getConnection();
		//pst1
		pst1=connection.prepareStatement("select * from candidates");
		//update votes
		pst2=connection.prepareStatement("update candidates set votes=votes+1 where id=?");
		
		pst3=connection.prepareStatement("select * from candidates order by votes desc limit 2");
		
		pst4=connection.prepareStatement("select party,sum(votes) from candidates group by party");
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
		
		if(pst3 != null)
		{
			pst3.close();
		}
		
		if(pst4 != null)
		{
			pst4.close();
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


	@Override
	public List<Candidate> getTop2Candidates() throws SQLException {
		List<Candidate> toptwo=new ArrayList<>();
		
		try(ResultSet rst=pst3.executeQuery()) {
			while(rst.next())
			{
				//candidateId, String name, String partyName, int votes
				toptwo.add(new Candidate(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		System.out.println(toptwo.get(0).toString());
		System.out.println(toptwo.get(1).toString());
		return toptwo;
	}


	@Override
	public List<PartyVote> getPartyWiseAnalysis() throws SQLException {
		List<PartyVote> partywisevotes=new ArrayList<>();
		try(ResultSet rst=pst4.executeQuery()) {
			while(rst.next())
			{
				//candidateId, String name, String partyName, int votes
				partywisevotes.add(new PartyVote(rst.getString(1),rst.getInt(2)));
			}
		}
		partywisevotes.forEach(System.out::println);
		return partywisevotes;
	}
	
	
	
	
	

}
