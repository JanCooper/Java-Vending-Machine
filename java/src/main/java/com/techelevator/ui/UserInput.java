package com.techelevator.ui;
import java.util.Scanner;

public class UserInput {
	private static Scanner scanner;
	
	public String getInput() {
		scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
}
