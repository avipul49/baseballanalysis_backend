package com.baseballanalysis.controller;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseballanalysis.utils.DatabaseConnection;
import com.baseballanalysis.utils.Queries;

@RestController
public class RecordsController extends BaseballController {
	@RequestMapping("/getNextRecord")
	public @ResponseBody Result getPlayerSalariesOverTheYears(
			@RequestParam int index, HttpServletResponse response) {

		setResposeObject(response);
		int recordIndex = index % Queries.records.length;

		// (int) (Math.random() * Queries.records.length);//
		System.out.println(Queries.records[recordIndex]);
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet res = connection.prepareStatement(
					Queries.records[recordIndex]).executeQuery();
			Result result = new Result();

			if (res.next()) {
				result.setRecord(res.getString(1));
			}
			connection.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
