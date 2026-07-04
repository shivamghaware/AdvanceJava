package com.voting.dto;

public class PartyVote {
	private String partyname;
	private int votes;
	
	public String getPartyname() {
		return partyname;
	}
	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public PartyVote(String partyname, int votes) {
		this.partyname = partyname;
		this.votes = votes;
	}
	@Override
	public String toString() {
		return "PartyVote [partyname=" + partyname + ", votes=" + votes + "]";
	}
		
	
}
