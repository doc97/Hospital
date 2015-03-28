package com.tint.hospital;

public class LoggingSystem {

	public LoggingSystem() {
	}
	
	public static void log(String system, String message, boolean err) {
		if(err)
			System.err.println("[" + system + "]: " + message);
		else
			System.out.println("[" + system + "]: " + message);
	}

}
