package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection conn = null;
	
	//Private constructor PREVENTS us from ever instantiating this class
	private ConnectionUtil() {
		super();
	}
	public static Connection getConnection() {
		
		/*
		 * We will be using DriverManager to obtain our connection to the database
		 * We will need to provide some crediential information:
		 *	-Connection String: jdbc:oracle:thin:@ENDPPOINT:PORT:sid
		 *	jdbc.oracle.thin.@ENDPOINT:1531:ORCL
		 * username
		 * password
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@training.cmhvzaqtbcms.us-east-2.rds.amazonaws.com:1521:ORCL",
							"beaver",
							"chew"); //HARD CODED PASSWORD
				// If you push this to GitHub, EVERYONE can see your password 
				// VERY UNSAFE
				//Recommend: USING enviroment variables instead
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Did not foind ORALCE Dataad");
		}
		
		
		return conn;
	}

}
