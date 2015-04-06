package com.baseballanalysis.model;

public class PlayerSalary {
	private String playerid;
	private int salary;
	private String teamId;
	private int yearId;

	public String getPlayerid() {
		return playerid;
	}

	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

}
