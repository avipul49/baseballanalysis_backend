package com.baseballanalysis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "gagan",
				"Samsung&G4$s");
	}

}
