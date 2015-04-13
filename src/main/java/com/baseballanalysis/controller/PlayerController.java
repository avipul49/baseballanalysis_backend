package com.baseballanalysis.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseballanalysis.model.Batting;
import com.baseballanalysis.model.Individual;
import com.baseballanalysis.model.NameValue;
import com.baseballanalysis.model.NameValues;
import com.baseballanalysis.model.Pitching;
import com.baseballanalysis.utils.DatabaseConnection;
import com.baseballanalysis.utils.Queries;

@RestController
public class PlayerController extends BaseballController {

	public static String battingManagerTeam = "select b.yearid,b.playerid, b.teamid, m.playerid as managerid, mm.namegiven, b.r, b.hr from batting b, managers m,  master mm  where  mm.playerid = m.playerid and m.teamid = b.teamid and m.yearid = b.yearid and b.yearid >= %s and b.yearid <= %s and b.playerid = '%s'";
	public static String pitchingManagerTeam = "select p.yearid,p.playerid, p.teamid, m.playerid as managerid, mm.namegiven, p.so, p.sho from pitching p, managers m, master mm where  mm.playerid = m.playerid and m.teamid = p.teamid and m.yearid = p.yearid and p.yearid >= %s and p.yearid <= %s and p.playerid = '%s'";
	public static String searchPlayer = "select playerid, CONCAT(namefirst,CONCAT(' ',namelast)) as name from master where playerid in (select distinct playerid from %s where yearid>=%s and yearid<=%s) and (LOWER(namefirst) like LOWER('%%%s%%') or LOWER(namelast) like LOWER('%%%s%%')) and ROWNUM < 100";

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
		String finalQuery = String.format(PlayerController.searchPlayer,
				"appearances", startYear, endYear, key, key);
		System.out.println(finalQuery);
		return getSearchResult(finalQuery);
	}

	@RequestMapping("/searchBatter")
	public @ResponseBody ArrayList<Individual> searchBatter(
			@RequestParam String key, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		String finalQuery = String.format(PlayerController.searchPlayer,
				"batting", startYear, endYear, key, key);
		System.out.println(finalQuery);
		return getSearchResult(finalQuery);
	}

	@RequestMapping("/searchPitcher")
	public @ResponseBody ArrayList<Individual> searchPitcher(
			@RequestParam String key, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		String finalQuery = String.format(PlayerController.searchPlayer,
				"pitching", startYear, endYear, key, key);
		System.out.println(finalQuery);
		return getSearchResult(finalQuery);
	}

	@RequestMapping("/searchManager")
	public @ResponseBody ArrayList<Individual> searchManager(
			@RequestParam String key, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		String finalQuery = String.format(PlayerController.searchPlayer,
				"managers", startYear, endYear, key, key);
		System.out.println(finalQuery);
		return getSearchResult(finalQuery);
	}

	private ArrayList<Individual> getSearchResult(String finalQuery) {
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

	@RequestMapping("/getPitchingWeightGroup")
	public @ResponseBody ArrayList<NameValues> getPitchingWeightGroup(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		return getNameValuesPair(Queries.pitchingWeightGroups, teams,
				startYear, endYear);
	}

	@RequestMapping("/getPitchingHeightGroup")
	public @ResponseBody ArrayList<NameValues> getPitchingHeightGroup(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		return getNameValuesPair(Queries.pitchingHeightGroups, teams,
				startYear, endYear);
	}

	@RequestMapping("/getBattingManagerTeamDetails")
	public @ResponseBody ArrayList<Batting> getPitchingManagerTeamDetails(
			@RequestParam String playerid, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		String finalQuery = String.format(PlayerController.battingManagerTeam,
				startYear, endYear, playerid);
		System.out.println(finalQuery);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(finalQuery)
					.executeQuery();
			ArrayList<Batting> battings = new ArrayList<Batting>();
			while (res.next()) {
				Batting batting = new Batting();
				batting.setYearId(res.getInt(1));
				batting.setPlayerId(res.getString(2));
				batting.setTeamId(res.getString(3));
				batting.setManagerId(res.getString(4));
				batting.setManagerName(res.getString(5));
				batting.setRuns(res.getInt(6));
				batting.setHomeRuns(res.getInt(7));
				battings.add(batting);
			}
			connection.close();
			return battings;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getPitchingManagerTeamDetails")
	public @ResponseBody ArrayList<Pitching> getBattingManagerTeamDetails(
			@RequestParam String playerid, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		setResposeObject(response);
		String finalQuery = String.format(PlayerController.pitchingManagerTeam,
				startYear, endYear, playerid);
		System.out.println(finalQuery);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(finalQuery)
					.executeQuery();
			ArrayList<Pitching> pitchingData = new ArrayList<Pitching>();
			while (res.next()) {
				Pitching pitching = new Pitching();
				pitching.setYearId(res.getInt(1));
				pitching.setPlayerId(res.getString(2));
				pitching.setTeamId(res.getString(3));
				pitching.setManagerId(res.getString(4));
				pitching.setManagerName(res.getString(5));
				pitching.setStrikeouts(res.getInt(6));
				pitching.setShutouts(res.getInt(7));
				pitchingData.add(pitching);
			}
			connection.close();
			return pitchingData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
