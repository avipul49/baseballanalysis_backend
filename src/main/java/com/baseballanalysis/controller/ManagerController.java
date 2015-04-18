package com.baseballanalysis.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseballanalysis.model.NameValues;
import com.baseballanalysis.model.Team;
import com.baseballanalysis.utils.DatabaseConnection;
import com.baseballanalysis.utils.Queries;

@RestController
public class ManagerController extends BaseballController {

	@RequestMapping("/getManagerAgeMaturity")
	public @ResponseBody ArrayList<NameValues> getManagerAgeMaturity(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		System.out.println("ques="+Queries.managerPerformance);
		return getNameValuesPair(Queries.managerPerformance, teams, startYear,
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
	@RequestMapping("/getManagerMaturityAwards")
	public @ResponseBody ArrayList<NameValues> getManagerMaturityAwards(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		System.out.println("ques="+Queries.managerAwards);
		return getNameValuesPairSingle(Queries.managerAwards, teams, startYear,
				endYear);
	}
	

	
	@RequestMapping("/getManagerLoyalty")
	public @ResponseBody ArrayList<NameValues> getManagerLoyalty(
			@RequestParam String manager, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		System.out.println("ques="+Queries.managerTenure);
		return getNameValuesPairSingle(Queries.managerTenure, manager, startYear,
				endYear);
	}
	
	
	@RequestMapping("/getManagerTeamPerformace")
	public @ResponseBody ArrayList<NameValues> getManagerTeamPerformace(
			@RequestParam String manager, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);

		String query = String.format(Queries.managerTeamPerf, startYear,
				endYear, manager);
		System.out.println(query);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(query).executeQuery();
			ArrayList<Team> fetchedTeams = new ArrayList<Team>();
			ArrayList<NameValues> nameValuess = new ArrayList<NameValues>();
			while (res.next()) {
				NameValues nameValue = new NameValues();
				nameValue.setName(res.getString(1));
				nameValue.setValues(new int[] { res.getInt(2), res.getInt(3),
						res.getInt(4),res.getInt(5),res.getInt(6),res.getInt(7),res.getInt(8),
						res.getInt(9),res.getInt(10),res.getInt(11),res.getInt(12),res.getInt(13)
						,res.getInt(14) ,res.getInt(15),res.getInt(16),res.getInt(17)});
				nameValuess.add(nameValue);
			}
			
			/*while (res.next()) {
				Team team = new Team();
			
				team.setName(res.getString(1));
				team.setGamesPlayed(res.getInt(2));
				team.setWins(res.getInt(3));
				team.setLosses(res.getInt(4));
				team.setRunsScored(res.getInt(5));
				team.setAtBats(res.getInt(6));
				team.setHits(res.getInt(7));
				team.setHomeruns(res.getInt(8));
				team.setStrikeOuts(res.getInt(9));
				team.setCaughtStealing(res.getInt(10));
				team.setRunsAllowed(res.getInt(11));
				team.setEarnedRuns(res.getInt(12));
				team.setCompleteGames(res.getInt(13));
				team.setShutouts(res.getInt(13));
				team.setHitsAllowed(res.getInt(14));
				team.setHomerunsAllowed(res.getInt(15));
				team.setFieldingPercentage(res.getDouble(16) * 100);
				team.setAttendance(res.getInt(17));
				team.setStrikeOutsByPitchers(res.getInt(18));
				team.setRank(res.getInt(19));
				fetchedTeams.add(team);
			}*/
			connection.close();
			return nameValuess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@RequestMapping("/getPlayerManager")
	public @ResponseBody ArrayList<NameValues> getPlayerManager(
			@RequestParam String player, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		System.out.println("ques="+Queries.managerPerformance);
		return getNameValuesPair(Queries.managerPerformance, player, startYear,
				endYear);
	}
	
	
	
	
}
