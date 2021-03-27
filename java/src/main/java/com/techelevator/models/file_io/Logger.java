package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	private static PrintWriter writer;
	private static File log;
	
	public Logger() {
		createLogFile();
	}
	
	static void createLogFile() {
		writer = null;
		log = new File(System.getProperty("user.dir"), "log.txt");		
		try {			
			if(log.exists()) {
				// overwrite existing file if it exists from previous run of program
				writer = new PrintWriter(new FileOutputStream(log, false));
			} else {
				log.createNewFile();
			}			
			System.out.println(log.toString() + " has been created.");			
		} catch (IOException e) {
			System.out.println("Cannot find path or directory. Please contact Administrator.");			
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	public void writeToFile ( String input) {
		PrintWriter writer = null;
		
		try {
			// append to existing file
			writer = new PrintWriter(new FileOutputStream(log, true));
			writer.println(input);		
		} catch (FileNotFoundException e) {
			System.out.println("Cannot write to file. Please contact Administrator.");
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
}
