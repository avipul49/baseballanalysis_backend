package com.baseballanalysis.model;

public class Team {
	private String lgId;
	private String teamId;
	private String name;
	private int gamesPlayed;
	private int wins;
	private int losses;
	private int yearId;
	private int runsScored;
	private int atBats;
	private int hits;
	private int homeruns;
	private int strikeOuts;
	private int caughtStealing;
	private int opponentRunsScored;
	private int runsAllowed;
	private int earnedRuns;
	private int completeGames;
	private int shutouts;
	private int hitsAllowed;
	private int homerunsAllowed;
	private double fieldingPercentage;
	private int attendance;
	private int strikeOutsByPitchers;
	private int rank;
	
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

	public int getRunsScored() {
		return runsScored;
	}

	public void setRunsScored(int runsScored) {
		this.runsScored = runsScored;
	}

	public int getAtBats() {
		return atBats;
	}

	public void setAtBats(int atBats) {
		this.atBats = atBats;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getHomeruns() {
		return homeruns;
	}

	public void setHomeruns(int homeruns) {
		this.homeruns = homeruns;
	}

	public int getStrikeOuts() {
		return strikeOuts;
	}

	public void setStrikeOuts(int strikeOuts) {
		this.strikeOuts = strikeOuts;
	}

	public int getCaughtStealing() {
		return caughtStealing;
	}

	public void setCaughtStealing(int caughtStealing) {
		this.caughtStealing = caughtStealing;
	}

	public int getOpponentRunsScored() {
		return opponentRunsScored;
	}

	public void setOpponentRunsScored(int opponentRunsScored) {
		this.opponentRunsScored = opponentRunsScored;
	}

	public int getRunsAllowed() {
		return runsAllowed;
	}

	public void setRunsAllowed(int runsAllowed) {
		this.runsAllowed = runsAllowed;
	}

	public int getCompleteGames() {
		return completeGames;
	}

	public void setCompleteGames(int completeGames) {
		this.completeGames = completeGames;
	}

	public int getHitsAllowed() {
		return hitsAllowed;
	}

	public void setHitsAllowed(int hitsAllowed) {
		this.hitsAllowed = hitsAllowed;
	}

	public int getHomerunsAllowed() {
		return homerunsAllowed;
	}

	public void setHomerunsAllowed(int homerunsAllowed) {
		this.homerunsAllowed = homerunsAllowed;
	}

	public double getFieldingPercentage() {
		return fieldingPercentage;
	}

	public void setFieldingPercentage(double d) {
		this.fieldingPercentage = d;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public int getShutouts() {
		return shutouts;
	}

	public void setShutouts(int shutouts) {
		this.shutouts = shutouts;
	}

	public int getEarnedRuns() {
		return earnedRuns;
	}

	public void setEarnedRuns(int earnedRuns) {
		this.earnedRuns = earnedRuns;
	}

	public int getStrikeOutsByPitchers() {
		return strikeOutsByPitchers;
	}

	public void setStrikeOutsByPitchers(int strikeOutsByPitchers) {
		this.strikeOutsByPitchers = strikeOutsByPitchers;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
