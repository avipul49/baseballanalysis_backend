package com.baseballanalysis.model;

public class Team {
	private String lgId;
	private String teamId;
	private String name;
	private int gamesPlayed;
	private int wins;
	private int losses;
	private int yearId;

	public Team() {
	}

	public String getLgId() {
		return lgId;
	}

	public void setLgId(String lgId) {
		this.lgId = lgId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public String getName() {
		return name;
	}

	public void setName(String teamName) {
		this.name = teamName;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
}
