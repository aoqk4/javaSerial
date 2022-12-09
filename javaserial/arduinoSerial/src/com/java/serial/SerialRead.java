package com.java.serial;

import java.io.BufferedReader;
import java.io.InputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialRead implements Runnable {

	InputStream inS;
	BufferedReader bufferedReader;

	public SerialRead(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public void run() {
		while (true) {
			try {

				if (this.bufferedReader.ready()) {
					String result = this.bufferedReader.readLine();
					
					JSONParser jsonParse = new JSONParser();
					JSONObject jsonObj = (JSONObject) jsonParse.parse(result);
					
					System.out.println(jsonObj.get("temp"));
//					System.out.print(jsonObj.get("level"));
//					System.out.print(jsonObj.get("light"));
//					System.out.print(jsonObj.get("ph"));
//					System.out.println(jsonObj.get("state"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		int len = -1;
//		
//		try {
//			while((len = this.inS.read(buffer)) > -1) {
//				String s = new String(buffer, 0, len);
//				if (len != 0) {
//					
//					String result = new DataProc(s).getData();
//					
//					System.out.print(result);
//					

//				}
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
	}
}