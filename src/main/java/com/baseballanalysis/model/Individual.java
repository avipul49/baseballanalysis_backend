package com.baseballanalysis.model;

import java.sql.Date;

public class Individual {
	private String playerId;
	private String birthYear;
	private String birthMonth;
	private String birthDay;
	private String birthCountry;
	private String birthState;
	private String birthCity;
	private String name;
	private double weight;
	private double height;
	private char battingStyle;
	private char throwingStyle;
	private Date debut;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public String getBirthState() {
		return birthState;
	}

	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public char getBattingStyle() {
		return battingStyle;
	}

	public void setBattingStyle(char batingStyle) {
		this.battingStyle = batingStyle;
	}

	public char getThrowingStyle() {
		return throwingStyle;
	}

	public void setThrowingStyle(char throwingStyle) {
		this.throwingStyle = throwingStyle;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

}
