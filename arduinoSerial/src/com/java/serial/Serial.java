package com.java.serial;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class Serial {
	boolean Connect(String port) {
		CommPort commPort = null;
		
		SerialPort serialPort = null;
		
		try {
			CommPortIdentifier com = CommPortIdentifier.getPortIdentifier(port);
			
			if (com.isCurrentlyOwned()) {
				System.out.println("Already Used!");
				return false;
			}
			else {
				commPort = com.open(this.getClass().getName(), 2000);
				
				if (commPort instanceof SerialPort) {
					serialPort = (SerialPort)commPort;
					
					serialPort.setSerialPortParams(9600, // Serial begin 한 바운드 레이트?
												   SerialPort.DATABITS_8, // 여기서부터 오류 제어비트 전송하기 위해서
												   SerialPort.STOPBITS_1, 
												   SerialPort.PARITY_NONE);
				}
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				
				(new Thread(new SerialRead(bufferedReader))).start();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	
}
