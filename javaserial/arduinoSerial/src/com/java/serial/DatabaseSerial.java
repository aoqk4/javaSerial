package com.java.serial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseSerial {
	private String DB_DRIVER = null;

	private String DB_URL = null;

	private String DB_USER = null;

	private String DB_PASSWORD = null;

	private PreparedStatement pstmt;

	private Connection connection;

	DatabaseSerial(String driver, String url, String user, String password) {

		if (this.connection == null) {

			this.DB_DRIVER = driver;

			this.DB_URL = url;

			this.DB_USER = user;

			this.DB_PASSWORD = password;
		}
	}

	public boolean connectDB() {

		try {
			Class.forName(DB_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	public void disconnectDB(ResultSet rs) {
		try {
			this.connection.close();

			if (rs != null) {
				rs.close();
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
			this.connection = null;
			this.pstmt = null;
		}
	}

	public PreparedStatement getStatement(String sql) {

		try {
			if (this.connection == null) {
				this.connectDB();
			}
			this.pstmt = connection.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public ResultSet executeDB(PreparedStatement pstmt, boolean dbState) {
		try {
			if (dbState == false) {
				this.connectDB();
			}

			if (pstmt != null) {
				this.pstmt = pstmt;
				return this.pstmt.executeQuery();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
