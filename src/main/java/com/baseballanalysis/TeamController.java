package com.baseballanalysis;

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

import com.baseballanalysis.model.Team;
import com.baseballanalysis.utils.DatabaseConnection;
import com.baseballanalysis.utils.Queries;

@Controller
@RestController
public class TeamController {

	@RequestMapping("/getTeamPerformance")
	public @ResponseBody ArrayList<Team> getTeamPerformance(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET, POST, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept");

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
				team.setTeamId(res.getString(2));
				team.setName(res.getString(3));
				team.setGamesPlayed(res.getInt(4));
				team.setWins(res.getInt(5));
				team.setLosses(res.getInt(6));
				team.setLgId(res.getString(7));
				fetchedTeams.add(team);
			}
			connection.close();
			return fetchedTeams;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/postTry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Team[] postTry(@RequestBody Team[] teams) {
		System.out.println(teams);
		return teams;
	}
}
