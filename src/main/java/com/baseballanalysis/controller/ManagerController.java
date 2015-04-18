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
		System.out.println("ques="+Queries.managerPerformance);
		return getNameValuesPair(Queries.managerPerformance, manager, startYear,
				endYear);
	}
	
	
	@RequestMapping("/getManagerTeamPerformace")
	public @ResponseBody ArrayList<NameValues> getManagerTeamPerformace(
			@RequestParam String manager, @RequestParam int startYear,
			@RequestParam int endYear, HttpServletResponse response) {

		setResposeObject(response);
		System.out.println("ques="+Queries.managerPerformance);
		return getNameValuesPair(Queries.managerPerformance, manager, startYear,
				endYear);
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
