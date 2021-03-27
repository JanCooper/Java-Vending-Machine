package com.techelevator.ui.helper;

import java.math.BigDecimal;
import java.util.List;
import com.techelevator.ui.DisplayOutput;
import com.techelevator.ui.UserInput;

public class Menu {
	DisplayOutput display = new DisplayOutput();
	UserInput userInput = new UserInput();

	public int menuInOut(List<String> menu, String firstLine) {
		display.display(firstLine);
		display.display(" ");
		display.display(menu);
		String input = userInput.getInput();
		int userChoice = 	VerifyInput.verifyNumber(input);
		return userChoice;
	}
	
	public int purchaseMenuInOut(List<String> menu, String firstLine, BigDecimal inputBalance) {
		display.display(firstLine);
		display.display(" ");
		display.display(menu);
    	display.display("Current Money Provided: $" + inputBalance);
		String input = userInput.getInput();
		int userChoice = 	VerifyInput.verifyNumber(input);
		return userChoice;	
	}
	
	public String makePurchaseInOut(List<String> itemList, String firstLine){
		display.display(firstLine);
		display.display(" ");
		display.display(itemList);
		String input = userInput.getInput();		
		String userChoice = VerifyInput.verifySlotID(input);
		return userChoice;	
	}

}
