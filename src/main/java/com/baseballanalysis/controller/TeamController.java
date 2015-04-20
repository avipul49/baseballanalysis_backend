package com.baseballanalysis.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseballanalysis.model.Individual;
import com.baseballanalysis.model.NameValues;
import com.baseballanalysis.model.Team;
import com.baseballanalysis.model.TeamOrientationStats;
import com.baseballanalysis.utils.DatabaseConnection;
import com.baseballanalysis.utils.Queries;

@Controller
@RestController
public class TeamController extends BaseballController {

	@RequestMapping("/getTeamPerformance")
	public @ResponseBody ArrayList<Team> getTeamPerformance(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);

		String query = String.format(Queries.teamPerformance, startYear,
				endYear, teams);
		System.out.println(query);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(query).executeQuery();
			ArrayList<Team> fetchedTeams = new ArrayList<Team>();
			while (res.next()) {
				Team team = new Team();
				team.setYearId(res.getInt(1));
				team.setLgId(res.getString(2));
				team.setTeamId(res.getString(3));
				team.setName(res.getString(4));
				team.setGamesPlayed(res.getInt(5));
				team.setWins(res.getInt(6));
				team.setLosses(res.getInt(7));
				team.setRunsScored(res.getInt(8));
				team.setAtBats(res.getInt(9));
				team.setHits(res.getInt(10));
				team.setHomeruns(res.getInt(11));
				team.setStrikeOuts(res.getInt(12));
				team.setCaughtStealing(res.getInt(13));
				team.setRunsAllowed(res.getInt(14));
				team.setEarnedRuns(res.getInt(15));
				team.setCompleteGames(res.getInt(16));
				team.setShutouts(res.getInt(17));
				team.setHitsAllowed(res.getInt(18));
				team.setHomerunsAllowed(res.getInt(19));
				team.setFieldingPercentage(res.getDouble(20) * 100);
				team.setAttendance(res.getInt(21));
				team.setStrikeOutsByPitchers(res.getInt(22));
				team.setRank(res.getInt(23));
				fetchedTeams.add(team);
			}
			connection.close();
			return fetchedTeams;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getTeamOrientation")
	public @ResponseBody ArrayList<TeamOrientationStats> getTeamOrientation(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		String query = String.format(Queries.teamOrientationBattingPitching,
				startYear, endYear, teams, startYear, endYear, teams);
		System.out.println(query);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(query).executeQuery();
			ArrayList<TeamOrientationStats> fetchedTeams = new ArrayList<TeamOrientationStats>();
			while (res.next()) {
				TeamOrientationStats team = new TeamOrientationStats();
				team.setTeamId(res.getString(1));
				team.setBattingRank(res.getInt(2));
				team.setPitchingRank(res.getInt(3));
				team.setRuns(res.getInt(4));
				team.setOut(res.getInt(5));
				fetchedTeams.add(team);
			}
			connection.close();
			return fetchedTeams;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getTeamPlayingStyle")
	public @ResponseBody ArrayList<Individual> getTeamPlayingStyle(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		String query = String.format(Queries.teamTendency, startYear, endYear,
				teams);
		System.out.println(query);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(query).executeQuery();
			ArrayList<Individual> individuals = new ArrayList<Individual>();
			while (res.next()) {
				Individual individual = new Individual();
				individual.setPlayerId(res.getString(1));
				individual.setName(res.getString(2));
				individual.setBattingStyle(res.getString(3).charAt(0));
				individual.setThrowingStyle(res.getString(3).charAt(0));
				individuals.add(individual);
			}
			connection.close();
			return individuals;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getPlayerOrigin")
	public @ResponseBody String getPlayerOrigin(@RequestParam String teams,
			@RequestParam int startYear, @RequestParam int endYear,
			HttpServletResponse response) {

		setResposeObject(response);
		return "{\"cols\":[{\"type\":\"string\",\"label\":\"State\"},{\"type\":\"number\",\"label\":\"Player Count\"}],"
				+ "\"rows\":[{\"c\":[{\"v\":\"US-AK\"},{\"v\":7}]},{\"c\":[{\"v\":\"US-AL\"},{\"v\":220}]},"
				+ "{\"c\":[{\"v\":\"US-AR\"},{\"v\":99}]},{\"c\":[{\"v\":\"US-AZ\"},{\"v\":71}]},{\"c\":[{\"v\":\"US-CA\"},{\"v\":1446}]},{\"c\":[{\"v\":\"US-CO\"},{\"v\":54}]},"
				+ "{\"c\":[{\"v\":\"US-CT\"},{\"v\":95}]},{\"c\":[{\"v\":\"US-DC\"},{\"v\":45}]},{\"c\":[{\"v\":\"US-DE\"},{\"v\":29}]},{\"c\":[{\"v\":\"US-FL\"},{\"v\":328}]},{\"c\":[{\"v\":\"US-GA\"},{\"v\":214}]},{\"c\":[{\"v\":\"US-HI\"},{\"v\":25}]},{\"c\":[{\"v\":\"US-IA\"},{\"v\":142}]},{\"c\":[{\"v\":\"US-ID\"},{\"v\":17}]},{\"c\":[{\"v\":\"US-IL\"},{\"v\":601}]},{\"c\":[{\"v\":\"US-IN\"},{\"v\":189}]},{\"c\":[{\"v\":\"US-KS\"},{\"v\":136}]},{\"c\":[{\"v\":\"US-KY\"},{\"v\":131}]},{\"c\":[{\"v\":\"US-LA\"},{\"v\":161}]},{\"c\":[{\"v\":\"US-MA\"},{\"v\":307}]},{\"c\":[{\"v\":\"US-MD\"},{\"v\":160}]},{\"c\":[{\"v\":\"US-ME\"},{\"v\":40}]},{\"c\":[{\"v\":\"US-MI\"},{\"v\":304}]},{\"c\":[{\"v\":\"US-MN\"},{\"v\":114}]},{\"c\":[{\"v\":\"US-MO\"},{\"v\":330}]},{\"c\":[{\"v\":\"US-MS\"},{\"v\":136}]},{\"c\":[{\"v\":\"US-MT\"},{\"v\":14}]},{\"c\":[{\"v\":\"US-NC\"},{\"v\":263}]},{\"c\":[{\"v\":\"US-ND\"},{\"v\":11}]},{\"c\":[{\"v\":\"US-NE\"},{\"v\":69}]},{\"c\":[{\"v\":\"US-NH\"},{\"v\":22}]},{\"c\":[{\"v\":\"US-NJ\"},{\"v\":256}]},{\"c\":[{\"v\":\"US-NM\"},{\"v\":17}]},{\"c\":[{\"v\":\"US-NV\"},{\"v\":21}]},{\"c\":[{\"v\":\"US-NY\"},{\"v\":538}]},{\"c\":[{\"v\":\"US-OH\"},{\"v\":517}]},{\"c\":[{\"v\":\"US-OK\"},{\"v\":161}]},{\"c\":[{\"v\":\"US-OR\"},{\"v\":80}]},{\"c\":[{\"v\":\"US-PA\"},{\"v\":647}]},{\"c\":[{\"v\":\"US-RI\"},{\"v\":39}]},{\"c\":[{\"v\":\"US-SC\"},{\"v\":121}]},{\"c\":[{\"v\":\"US-SD\"},{\"v\":26}]},{\"c\":[{\"v\":\"US-TN\"},{\"v\":186}]},{\"c\":[{\"v\":\"US-TX\"},{\"v\":584}]},{\"c\":[{\"v\":\"US-UT\"},{\"v\":24}]},{\"c\":[{\"v\":\"US-VA\"},{\"v\":188}]},{\"c\":[{\"v\":\"US-VT\"},{\"v\":18}]},{\"c\":[{\"v\":\"US-WA\"},{\"v\":138}]},{\"c\":[{\"v\":\"US-WI\"},{\"v\":146}]},{\"c\":[{\"v\":\"US-WV\"},{\"v\":66}]},{\"c\":[{\"v\":\"US-WY\"},{\"v\":12}]}]"
				+ "}";
	}

	@RequestMapping(value = "/postTry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Team[] postTry(@RequestBody Team[] teams) {
		System.out.println(teams);
		return teams;
	}
	
	@RequestMapping("/getTeamAchievementAward")
	public @ResponseBody ArrayList<NameValues> getTeamAchievementAward(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		System.out.println("ques="+Queries.teamAwardsComp);
		return getNameValuesPairSingle(Queries.teamAwardsComp, teams, startYear,
				endYear);
	}
	
	private ArrayList<NameValues> getNameValuesPairSingle(String query, String teams,
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
				nameValue.setValues(new int[] { res.getInt(2)});
				nameValuess.add(nameValue);
			}
			connection.close();
			return nameValuess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/getTeamAgeGroup")
	public @ResponseBody ArrayList<NameValues> getTeamAgeGroup(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		System.out.println("teams="+teams+"startyear"+startYear+"end"+endYear);
		setResposeObject(response);
		System.out.println("ques="+Queries.teamAgeGroup);
		return getNameValuesPairSingle(Queries.teamAgeGroup, teams, startYear,
				endYear);
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
