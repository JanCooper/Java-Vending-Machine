package com.techelevator.ui;

import java.util.List;
import java.util.Scanner;

public class DisplayOutput {
	private Scanner scanner;
	
	public void display(List<String> output) {
		for (String string : output) {
			System.out.println(string);
		}
	}
	
	public void display(String output) {
		System.out.println(output);
	}
}
