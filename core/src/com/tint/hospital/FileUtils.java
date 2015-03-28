package com.tint.hospital;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	/**
	* Reads contents of file into a @String
	* @param filename - path to file
	* @return the string containing the file content
	*/
	public static String readFromFile(String filename) {
		BufferedReader reader;
		StringBuilder builder = new StringBuilder();
		String line;
		try {
			reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) {
				builder.append(line + "/n");
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}