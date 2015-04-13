package com.baseballanalysis.model;

public class Pitching extends Player {
	private int shutouts;
	private int strikeouts;

	public int getShutouts() {
		return shutouts;
	}

	public void setShutouts(int shutouts) {
		this.shutouts = shutouts;
	}

	public int getStrikeouts() {
		return strikeouts;
	}

	public void setStrikeouts(int strikeouts) {
		this.strikeouts = strikeouts;
	}

}
