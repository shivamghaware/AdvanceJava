package com.voting.dtos;

public class PartyVotes {
	private String partyName;
	private int sumOfVotes;
	public PartyVotes(String partyName, int sumOfVotes) {
		super();
		this.partyName = partyName;
		this.sumOfVotes = sumOfVotes;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public int getSumOfVotes() {
		return sumOfVotes;
	}
	public void setSumOfVotes(int sumOfVotes) {
		this.sumOfVotes = sumOfVotes;
	}
	
}
