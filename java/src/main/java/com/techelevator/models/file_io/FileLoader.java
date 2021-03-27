package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.ui.DisplayOutput;

public  class FileLoader {
	private  Scanner scanner;
	private DisplayOutput display = new DisplayOutput();
	
	public List<String> getFileContent(File filePath){
		scanner = null;
		List<String> returnValue = new ArrayList<String>();
		
		try {
			scanner = new Scanner(filePath);
			
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				returnValue.add(line);
			}
		} catch (FileNotFoundException e) {
			display.display("Could not find requested file.");
		}	finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		return returnValue;
	}

}
