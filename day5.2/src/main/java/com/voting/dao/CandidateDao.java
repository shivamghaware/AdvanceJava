package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.dtos.PartyVotes;
import com.voting.entities.Candidate;

public interface CandidateDao extends BaseDao {
	List<Candidate> getAllCandidates() throws SQLException;

	String incrementCandidateVotes(int candidateId) throws SQLException;
	
	//top 2
	List<Candidate> getTop2Candidates() throws SQLException;
	
	//party wise votes
	List<PartyVotes> getPartywiseAnalysis() throws SQLException;;
}
