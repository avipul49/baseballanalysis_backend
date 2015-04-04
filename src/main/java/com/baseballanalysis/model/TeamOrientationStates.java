package com.baseballanalysis.model;

public class TeamOrientationStates {
	private String teamId;
	private int battingRank;
	private int pitchingRank;
	private int runs;
	private int out;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public int getBattingRank() {
		return battingRank;
	}

	public void setBattingRank(int battingRank) {
		this.battingRank = battingRank;
	}

	public int getPitchingRank() {
		return pitchingRank;
	}

	public void setPitchingRank(int pitchingRank) {
		this.pitchingRank = pitchingRank;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getOut() {
		return out;
	}

	public void setOut(int out) {
		this.out = out;
	}

}
