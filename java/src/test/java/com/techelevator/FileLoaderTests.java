package com.techelevator;

import com.techelevator.models.file_io.FileLoader;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;

public class FileLoaderTests {

	FileLoader fileLoader;
	String MESSAGE;
	File FILE_PATH = new File(System.getProperty("user.dir"), "\\data\\test\\FileLoaderTestsData.txt");
	List<String> FILE_CONTENT;
	
	@Before
	public void setup() {
		fileLoader = new FileLoader();
		
		FILE_CONTENT = new ArrayList<String>(Arrays.asList(
				"Git will not save empty directories,",
				"so this file is a placeholder",
				"to create the directory structure",
				"until the directory has contents."
				));
	}
	
	@Test
	public void fileLoader_getFileContent_getsContent() {
		// arrange
		MESSAGE = "File contents should be the same as FILE_CONTENT List<String>.";
		List<String> expected = FILE_CONTENT;
		
		// act
		List<String> actual = fileLoader.getFileContent(FILE_PATH);
		
		// assert
		assertEquals(MESSAGE, expected.toString(), actual.toString());
	}
	
	@Test
	public void fileLoader_getFileContent_lengthOfListEquals4() {
		// arrange
		MESSAGE = "Return should be List<String> of length 4.";
		int expected = 4;
		
		// act
		int actual = fileLoader.getFileContent(FILE_PATH).size();
		
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
}
