package com.voting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.voting.dbutils.VoterUtils;
import com.voting.dto.PartyVotes;
import com.voting.entites.Candidate;

public class CantidateDaoImpl implements CandidateDao {
	private Connection connection;
	private PreparedStatement pst1, pst2, pst3, pst4;

	public CantidateDaoImpl() throws SQLException {
		connection = VoterUtils.getSqlConnection();

		pst1 = connection.prepareStatement("select * from candidates");

		pst2 = connection.prepareStatement("update candidates set votes=votes+1 where id=?");

		pst3 = connection.prepareStatement("select * from candidates order by votes desc limit 2");

		pst4 = connection.prepareStatement("select party,sum(votes) from candidates group by party");
	}

	@Override
	public void cleanup() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
		if (pst2 != null) {
			pst2.close();
		}
		if (pst3 != null) {
			pst3.close();
		}
		if (pst4 != null) {
			pst4.close();
		}
		if (connection != null) {
			connection.close();
		}

	}

	@Override
	public List<Candidate> getAllCandiates() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();

		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				candidates.add(new Candidate(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		return candidates;
	}

	@Override
	public String incrementCount(int id) throws SQLException {
		pst2.setInt(1, id);
		int updateCount = pst2.executeUpdate();
		if (updateCount == 1)
			return "Votes updated !";
		return "Updating votes failed !!!!!!!!";
	}

	@Override
	public List<Candidate> getTop2Candidates() throws SQLException {
		List<Candidate> toptwo = new ArrayList<>();

		try (ResultSet rst = pst3.executeQuery()) {
			while (rst.next()) {
				toptwo.add(new Candidate(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		return toptwo;
	}

	@Override
	public List<PartyVotes> getPartyWiseVotes() throws SQLException {
		List<PartyVotes> partywisevotes=new ArrayList<>();
		try(ResultSet rst=pst4.executeQuery()) {
			while(rst.next())
			{
				partywisevotes.add(new PartyVotes(rst.getString(1),rst.getInt(2)));
			}
		}
		return partywisevotes;
	}

}
