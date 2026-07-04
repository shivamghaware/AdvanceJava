package com.voting.dto;

public class PartyVotes {
	private String partyName;
	private int voteCount;
	
	public PartyVotes(String partyName, int voteCount) {
		super();
		this.partyName = partyName;
		this.voteCount = voteCount;
	}
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "PartyVotes [partyName=" + partyName + ", voteCount=" + voteCount + "]";
	}	
}
