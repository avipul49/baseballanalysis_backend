package com.baseballanalysis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseballanalysis.model.PlayerSalary;
import com.baseballanalysis.utils.DatabaseConnection;
import com.baseballanalysis.utils.Queries;

@RestController
public class SalaryController {
	@RequestMapping("/getPlayerSalaries")
	public @ResponseBody ArrayList<PlayerSalary> getTeamPerformance(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET, POST, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept");

		String query = String.format(Queries.playerSalary, startYear, endYear,
				teams);
		System.out.println(query);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(query).executeQuery();
			ArrayList<PlayerSalary> playerSalaries = new ArrayList<PlayerSalary>();
			while (res.next()) {
				PlayerSalary playerSalary = new PlayerSalary();
				playerSalary.setPlayerid(res.getString(1));
				playerSalary.setSalary(res.getInt(2));
				playerSalaries.add(playerSalary);
			}
			connection.close();
			return playerSalaries;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
