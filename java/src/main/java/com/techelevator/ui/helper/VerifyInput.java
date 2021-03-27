package com.techelevator.ui.helper;

import java.math.BigDecimal;
import java.util.Arrays;
import com.techelevator.ui.DisplayOutput;

public abstract class VerifyInput {
	private static DisplayOutput display = new DisplayOutput();
	private static String inputNumberString;
	
	public static int verifyNumber(String inputNumber){
		inputNumberString = inputNumber;
		int verified = -1;  
		
		try {
			verified = Integer.parseInt(inputNumber);
		} catch (NumberFormatException e) {
			display.display(String.format("%s is not a valid number.", inputNumber));
		}
		return verified;
	}
	
	public static int verifyMainMenu(int userInput) {
		int returnValue = -1;
		boolean choice = userInput > 0 && userInput < 4;
		if (choice) {
			returnValue = userInput;
		} else {
			display.display(String.format("%s is not a valid menu option. Please try again.", String.valueOf(inputNumberString)));
		}
		return returnValue;
	}
	
	public static int verifyPurchaseMenu(int userInput) {
		int returnValue = -1;

			boolean choice = userInput > 0 && userInput < 4;
			if (choice) {
				returnValue = userInput;
			} else {
				display.display(String.format("%s is not a valid menu option. Please try again.", String.valueOf(inputNumberString)));
			}
		return returnValue;
	}
	
	public static BigDecimal verifyMoney(String userChoice) {
		String[] acceptedDollarAmount = new String[] {"1", "2", "5", "10"};
		String convertedChoice = userChoice + ".00";
		BigDecimal returnValue = null;
	
		if(Arrays.asList(acceptedDollarAmount).contains(userChoice)) {
			try {
				returnValue = new BigDecimal(convertedChoice);
			} catch (NumberFormatException e) {
				display.display(String.format("Cannot convert %s into a valid dollar amount. Please try again.", userChoice));
			}
		} else {
			display.display(String.format("%s is not an accepted dollar value.", userChoice));
		}
		
		return returnValue;
	}
	
	public static String verifySlotID(String userChoice) {
		String[] acceptedSlotIDs = new String[] {
						"A1",
						"A2",
						"A3",
						"A4",
						"B1",
						"B2",
						"B3",
						"B4",
						"C1",
						"C2",
						"C3",
						"C4",
						"D1",
						"D2",
						"D3",
						"D4"
		};	
		String returnValue = " 2";
		boolean isValid = false;
		
		for (String id : acceptedSlotIDs) {
			if(id.equalsIgnoreCase(userChoice)) {
				returnValue = userChoice.toUpperCase();
				isValid = true;
			}			
		}
		
		if(!isValid) {
			display.display(String.format("%s is not a valid Item choice. Please try again.", userChoice));
		}
		return returnValue;
	}
}
