package com.java.serial;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Timestamp;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialRead implements Runnable {

	InputStream inS;
	BufferedReader bufferedReader;
	PreparedStatement pstmt;

	public SerialRead(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public void run() {
		
		// for DB
//		DatabaseSerial db = new DatabaseSerial("org.mariadb.jdbc.Driver",
//				"jdbc:mariadb://112.220.17.34:36246/sunwt", "wt", "wt123!@#");
//		
//		boolean temp = db.connectDB();
		
		
		HttpURLConnection httpConn = null;
		
        JSONObject responseJson = null;
		
		try {
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				
				// for DB
//				ResultSet rs = null;

				if (this.bufferedReader.ready()) {
					String result = this.bufferedReader.readLine();

					// 측정 시간 체크
					java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
			        System.out.println(timestamp); // 생성한 timestamp 출력
			        
			        JSONObject jsonObj = null;
					
					if (result != null) {
						JSONParser jsonParse = new JSONParser();
						jsonObj = (JSONObject) jsonParse.parse(result);
						jsonObj.put("timestamp", timestamp);
					}
					
					URL url = new URL("http://localhost:3000/api/hello");
					httpConn = (HttpURLConnection)url.openConnection();
					
					httpConn.setRequestMethod("PUT");
					httpConn.setRequestProperty("Content-Type", "application/json");
					httpConn.setRequestProperty("Transfer-Encoding", "chunked");
					httpConn.setRequestProperty("Connection", "keep-alive");
					httpConn.setDoOutput(true);
					
					// send PUT
					if (httpConn != null && jsonObj != null) {
						int responseCode = httpConn.getResponseCode();
						
						if (responseCode == 200) {
							StringBuilder sb = new StringBuilder();
			                sb.append(jsonObj);
						}
					}
					
					// for DB
//					String sql = "INSERT INTO DEVICE_SENSOR (DEVICE_ID, SENSOR_ID, SENSOR_ST, SENSOR_VAL, CREATED_AT)"
//							+ " VALUES"
//							+ " ("
//							+ jsonObj.get("portNum") + ","
//							+ "?, "
//							+ "1, "
//							+ "?, "
//							+ "? "
//							+ ")";
					
//					String sql = "INSERT INTO wt_sensor (LIGHT_IN, WATER_QLTY, WATER_LV, TEMPERATURE, CREATED_AT)"
//							+ " VALUES"
//							+ " ("
//							+ "?, "
//							+ "?, "
//							+ "?, "
//							+ "?, "
//							+ "? "
//							+ ")";
					
//					this.pstmt = db.getStatement(sql);
//					pstmt.setTimestamp(5, timestamp);
					
//					if (jsonObj.get("light").toString() != "") {
//						this.pstmt.setString(1, jsonObj.get("light").toString());
//					}
//					if (jsonObj.get("ph").toString() != "") {
//						this.pstmt.setString(2, jsonObj.get("ph").toString());
//					}
//					if (jsonObj.get("level").toString() != "") {
//						this.pstmt.setString(3, jsonObj.get("level").toString());
//					}
//					if (jsonObj.get("temp").toString() != "") {
//						this.pstmt.setString(4, jsonObj.get("temp").toString());
//					}
//					rs = db.executeDB(this.pstmt, temp);
					
//					if (jsonObj.get("light").toString() != "") {
//						this.pstmt.setInt(1, 8);
//						this.pstmt.setString(2, jsonObj.get("light").toString());
//					}
//					rs = db.executeDB(this.pstmt, temp);
//					if (jsonObj.get("ph").toString() != "") {
//						this.pstmt.setInt(1, 9);
//						this.pstmt.setString(2, jsonObj.get("ph").toString());
//					}
//					rs = db.executeDB(this.pstmt, temp);
//					if (jsonObj.get("level").toString() != "") {
//						this.pstmt.setInt(1, 10);
//						this.pstmt.setString(2, jsonObj.get("level").toString());
//					}
//					rs = db.executeDB(this.pstmt, temp);
//					if (jsonObj.get("temp").toString() != "") {
//						this.pstmt.setInt(1, 11);
//						this.pstmt.setString(2, jsonObj.get("temp").toString());
//					}
//					rs = db.executeDB(this.pstmt, temp);
					
					// pstsmt close db close
//					db.disconnectDB(rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}