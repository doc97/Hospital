package com.tint.hospital.utils;

public class LoggingSystem {

	public LoggingSystem() {
	}
	
	public static void log(String system, String message) {
		System.out.println("[" + system + "]: " + message);
	}
	
	public static void error(String system, String message) {
		System.err.println("[" + system + "]: " + message);
	}
}
