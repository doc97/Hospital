package com.tint.hospital.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	/**
	 * Reads contents of file into a @String
	 * @param filename - path to file
	 * @return the string containing the file content
	 * @throws IOException 
	*/
	public static String readFromFile(String filename, boolean lines) throws IOException {
		BufferedReader reader;
		StringBuilder builder = new StringBuilder();
		String line;
		reader = new BufferedReader(new FileReader(filename));
		while((line = reader.readLine()) != null) {
			builder.append(line + (lines ? "/n" : " "));
		}
		reader.close();
		
		
		return builder.toString();
	}
}