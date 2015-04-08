package com.baseballanalysis.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseballanalysis.model.Individual;
import com.baseballanalysis.model.NameValue;
import com.baseballanalysis.model.NameValues;
import com.baseballanalysis.utils.DatabaseConnection;
import com.baseballanalysis.utils.Queries;

@RestController
public class PlayerController extends BaseballController {
	@RequestMapping("/getPlayerBirthCountryStats")
	public @ResponseBody ArrayList<NameValue> getPlayerBirthCountryStats(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		return getNameValuePair(Queries.playerBirthCountry, teams, startYear,
				endYear);
	}

	@RequestMapping("/getPlayerBirthStateStats")
	public @ResponseBody ArrayList<NameValue> getPlayerBirthStateStats(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		return getNameValuePair(Queries.playerBirthState, teams, startYear,
				endYear);
	}

	@RequestMapping("/getPlayerBirthCityStats")
	public @ResponseBody ArrayList<NameValue> getPlayerBirthCityStats(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		return getNameValuePair(Queries.playerBirthCity, teams, startYear,
				endYear);
	}

	@RequestMapping("/searchPlayer")
	public @ResponseBody ArrayList<Individual> searchPlayer(
			@RequestParam String key, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		String finalQuery = String.format(Queries.searchPlayer, startYear,
				endYear, key, key);
		System.out.println(finalQuery);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(finalQuery)
					.executeQuery();
			ArrayList<Individual> individuals = new ArrayList<Individual>();
			while (res.next()) {
				Individual individual = new Individual();
				individual.setPlayerId(res.getString(1));
				individual.setName(res.getString(2));
				individuals.add(individual);
			}
			connection.close();
			return individuals;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getPlayerWeightGroup")
	public @ResponseBody ArrayList<NameValues> getPlayerWeightGroup(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		return getNameValuesPair(Queries.battingWeightGroups, teams, startYear,
				endYear);
	}

	@RequestMapping("/getPlayerHeightGroup")
	public @ResponseBody ArrayList<NameValues> getPlayerHeightGroup(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		return getNameValuesPair(Queries.battingHeightGroups, teams, startYear,
				endYear);
	}

	private ArrayList<NameValue> getNameValuePair(String query, String teams,
			int startYear, int endYear) {
		String finalQuery = String.format(query, startYear, endYear, teams);
		System.out.println(finalQuery);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(finalQuery)
					.executeQuery();
			ArrayList<NameValue> nameValues = new ArrayList<NameValue>();
			while (res.next()) {
				NameValue nameValue = new NameValue();
				nameValue.setName(res.getString(1));
				nameValue.setValue(res.getInt(2));
				nameValues.add(nameValue);
			}
			connection.close();
			return nameValues;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<NameValues> getNameValuesPair(String query, String teams,
			int startYear, int endYear) {
		String finalQuery = String.format(query, startYear, endYear, teams);
		System.out.println(finalQuery);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(finalQuery)
					.executeQuery();
			ArrayList<NameValues> nameValuess = new ArrayList<NameValues>();
			while (res.next()) {
				NameValues nameValue = new NameValues();
				nameValue.setName(res.getString(1));
				nameValue.setValues(new int[] { res.getInt(2), res.getInt(3),
						res.getInt(4) });
				nameValuess.add(nameValue);
			}
			connection.close();
			return nameValuess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
