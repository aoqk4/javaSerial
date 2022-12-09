package com.java.serial;

public class Main {
	public static void main(String[] args) {
		try {
			(new Serial()).Connect("COM8");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
