package com.spring.website;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLConnectionTest {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/mydb";
	private String user = "root";
	private String pw = "wldnrwldnr1";

	Logger logger = LoggerFactory.getLogger(MySQLConnectionTest.class);
	@Test
	public void testConnection() throws Exception {
		Class.forName(driver);
		try (Connection con = DriverManager.getConnection(url, user, pw)) {
			logger.info("연결");
			//System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
