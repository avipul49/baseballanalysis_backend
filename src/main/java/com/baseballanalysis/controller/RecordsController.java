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
