package com.baseballanalysis.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseballanalysis.utils.DatabaseConnection;

@RestController
public class RecordsController extends BaseballController {
	@RequestMapping("/getNextRecord")
	public @ResponseBody Result getPlayerSalariesOverTheYears(
			@RequestParam int index, HttpServletResponse response) {

		setResposeObject(response);
		try {
			Connection connection = DatabaseConnection.getConnection();
			CallableStatement cstmt = connection
					.prepareCall("{CALL next_record(?,?)}");
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, index);
			cstmt.executeUpdate();
			Result result = new Result();
			result.setRecord(cstmt.getString(1));
			connection.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getDatabaseDetails")
	public @ResponseBody DatabaseDetails getDatabaseDetails(
			HttpServletResponse response) {

		setResposeObject(response);
		try {
			Connection connection = DatabaseConnection.getConnection();
			CallableStatement cstmt = connection
					.prepareCall("{CALL get_database_details(?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.INTEGER);

			cstmt.executeUpdate();
			DatabaseDetails result = new DatabaseDetails();
			result.setRecords(cstmt.getInt(1));
			result.setTeams(cstmt.getInt(2));
			result.setPlayers(cstmt.getInt(3));
			result.setManagers(cstmt.getInt(4));
			connection.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static class DatabaseDetails {
		private int records;
		private int teams;
		private int players;
		private int managers;

		public int getRecords() {
			return records;
		}

		public void setRecords(int records) {
			this.records = records;
		}

		public int getTeams() {
			return teams;
		}

		public void setTeams(int teams) {
			this.teams = teams;
		}

		public int getPlayers() {
			return players;
		}

		public void setPlayers(int players) {
			this.players = players;
		}

		public int getManagers() {
			return managers;
		}

		public void setManagers(int managers) {
			this.managers = managers;
		}

	}

	static class Result {
		private String record;

		public String getRecord() {
			return record;
		}

		public void setRecord(String record) {
			this.record = record;
		}
	}
}
