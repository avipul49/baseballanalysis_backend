package com.baseballanalysis.controller;

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
public class SalaryController extends BaseballController {
	@RequestMapping("/getPlayerSalaries")
	public @ResponseBody ArrayList<PlayerSalary> getTeamPerformance(
			@RequestParam String teams, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);

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

	@RequestMapping("/getPlayerSalariesOverTheYears")
	public @ResponseBody ArrayList<PlayerSalary> getPlayerSalariesOverTheYears(
			@RequestParam String playerid, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		String query = String.format(Queries.playerSalariesOverTheYears,
				playerid, startYear, endYear);
		System.out.println(query);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(query).executeQuery();
			ArrayList<PlayerSalary> playerSalaries = new ArrayList<PlayerSalary>();
			while (res.next()) {
				PlayerSalary playerSalary = new PlayerSalary();
				playerSalary.setYearId(res.getInt(1));
				playerSalary.setTeamId(res.getString(2));
				playerSalary.setPlayerid(res.getString(3));
				playerSalary.setSalary(res.getInt(4));
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
