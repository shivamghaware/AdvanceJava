package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.dto.PartyVotes;
import com.voting.entites.Candidate;

public interface CandidateDao extends BaseDao {
	List<Candidate> getAllCandiates() throws SQLException;
	
	String incrementCount(int id) throws SQLException;
	
	List<Candidate> getTop2Candidates() throws SQLException;
	
	List<PartyVotes> getPartyWiseVotes() throws SQLException;
}
