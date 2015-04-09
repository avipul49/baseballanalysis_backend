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
			// ResultSet res = connection.prepareStatement(
			// Queries.records[recordIndex]).executeQuery();
			// Result result = new Result();
			//
			// if (res.next()) {
			// result.setRecord(res.getString(1));
			// }

			//CREATE OR REPLACE PROCEDURE next_record(record_found OUT VARCHAR2,i IN number)
			//AS position number;
			//BEGIN
			//    position := MOD(i,4);
			//    IF position = 0 THEN
			//      Select CONCAT('Most runs ever scored by any player is <b>',CONCAT(Total_runs,CONCAT('</b>, scored by <b>',CONCAT(Namegiven,'</b>.')))) into record_found from (Select Master.Namegiven, TR.Total_runs from Master, (Select PlayerID, sum(NVL(R,0))as Total_Runs from Batting group by PlayerID) TR where Master.PlayerID = TR.PlayerID order by TR.Total_runs DESC) where ROWNUM = 1;
			//    ELSIF position = 1 THEN
			//      Select CONCAT('<b>',CONCAT(Namegiven,CONCAT('</b> ',CONCAT('has the record of highest Strikeouts in the history of game with total of <b>',CONCAT(Total_outs,'</b>.'))))) into record_found from (Select Master.Namegiven, TOuts.Total_outs from Master, (Select PlayerID, sum(NVL(SO,0))as Total_Outs from Pitching group by PlayerID) TOuts where Master.PlayerID = TOuts.PlayerID order by TOuts.Total_outs DESC) where ROWNUM = 1;
			//		ELSIF position = 2 THEN
			//      Select CONCAT('Youngest person ever to manage a team is <b>',CONCAT(Namegiven,CONCAT('</b>, He was <b>',CONCAT(Age,' </b> at the time.')))) into record_found from ( Select M.Namegiven, A.Debut-M.Birthyear as Age from Master M, (Select PlayerID, min(YearID) as Debut from Managers where PLYRMGR = 'N'  group by PlayerID) A where A.PlayerId = M.PlayerID order by Age) where ROWNUM =1;
			//		ELSIF position = 3 THEN
			//    	select CONCAT('<b>',CONCAT(m.Namegiven,CONCAT('</b>',CONCAT(' has the received the highest salary ever in <b>',CONCAT(s.yearid,CONCAT('</b>, which was <b>$',CONCAT(s.salary,'</b>.'))))))) into record_found from "Salaries" s,APPEARANCES a,master m where a.playerid = m.playerid and a.playerid = s.playerid and salary >= all(select salary from "Salaries") and ROWNUM = 1;
			//    END IF;
			//END;
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
