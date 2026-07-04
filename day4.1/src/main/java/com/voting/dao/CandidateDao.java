package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.dto.PartyVote;
import com.voting.entities.Candidate;

public interface CandidateDao extends BaseDao {
	List<Candidate> getAllCandidates() throws SQLException;

	String incrementCandidateVotes(int candidateId) throws SQLException;
	
	List<Candidate> getTop2Candidates() throws SQLException;
	
	List<PartyVote> getPartyWiseAnalysis() throws SQLException;
}
