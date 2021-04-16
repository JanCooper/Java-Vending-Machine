package com.techelevator.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.file_io.Logger;
import com.techelevator.ui.*;
import com.techelevator.ui.helper.*;

public class Purchase {
	private List<String> purchaseMenu;
	private BigDecimal inputBalance;
	private Inventory inventory;
	private BigDecimal purchasePrice;
	private Menu menu = new Menu();
	private DisplayOutput display = new DisplayOutput();
	private UserInput userInput = new UserInput();
	private VendingMachine vendingMachine;
	private Logger log;
	
	public Purchase(Inventory inventory, VendingMachine vendingMachine) {
		this.inventory = inventory;
		this.vendingMachine = vendingMachine;
		this.log = log;
		inputBalance = new BigDecimal("0.00");
		purchasePrice = new BigDecimal("0.00");
		setPurchaseMenu();
	}

	////////////////////////////////////////////////////////////////////////////
	
	private void setPurchaseMenu() {
		purchaseMenu = new ArrayList<String>(
				Arrays.asList(
					"(1) Feed Money",
					"(2) Select Product",
					"(3) Finish Transaction\n"
				));		
	}
	
	private void setInputBalance(BigDecimal input) {
		inputBalance = inputBalance.add(input);
	}
	
	private void setPurchasePrice(BigDecimal price) {
		purchasePrice = purchasePrice.add(price);
	}
	
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	
	public List<String> getPurchaseMenu(){
		return purchaseMenu;
	}
	
	public BigDecimal getInputBalance() {
		inputBalance = inputBalance.setScale(2, RoundingMode.HALF_UP);
		return inputBalance;
	}
	
	public Logger getLog() {
		return log;
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	public void activatePurchaseMenu() {
		BigDecimal balance = getInputBalance();
    	int userChoice = menu.purchaseMenuInOut(getPurchaseMenu(), "Please choose 1, 2, or 3", balance);
    	int verifiedChoice = VerifyInput.verifyPurchaseMenu(userChoice);
    	if( verifiedChoice == -1) {
    		activatePurchaseMenu();
		} 
    	getPurchaseMenuOption(userChoice);
    }
	
	public void getPurchaseMenuOption(int option){		
		switch (option) {
		case 1 :  
			BigDecimal userInputMoney = getMoneyFromUser();
			addMoney(userInputMoney);	
			activatePurchaseMenu();
			break;
		case 2 : 
			String slotID = selectProduct();
			dispenseItem(slotID);
			activatePurchaseMenu();
			break;
		default : finishTransaction();
			break;
		}
	}
	
	public BigDecimal getMoneyFromUser() { // TODO add logging		
		display.display("Please add money in values of 1, 2, 5, or 10");
		String input = userInput.getInput();

		BigDecimal verifiedInput = VerifyInput.verifyMoney(input);
		BigDecimal returnValue = verifiedInput;
		
		if (verifiedInput == null) {
			return getMoneyFromUser();
		} 
		return returnValue;		
	}
	
	public void addMoney(BigDecimal userInput) {
		BigDecimal originalBalance = getInputBalance();
		setInputBalance(userInput);
		BigDecimal newBalance = getInputBalance();
		log("FEED MONEY:", originalBalance, newBalance);
	}
	
	public String selectProduct() { // TODO verify quantity 0 shows as Sold Out
		String userChoice = "";
		List<String> currentItemList = inventory.getPurchaseItemList();
		userChoice = menu.makePurchaseInOut(currentItemList, 	"Please enter Slot ID.");
		if(userChoice.equals(" ")) {
			activatePurchaseMenu();
		}			
		return userChoice;
	}

	public void dispenseItem(String slotID){	 // TODO add logging
		List<String> currentItemList = inventory.getPurchaseItemList();
		BigDecimal itemCost = new BigDecimal("0.00");
		String name;
		
		for (String item : currentItemList) {
			String[] itemArray = item.split("     ");
			
			if(itemArray[0].equals(slotID)) {
				String[] nameArray = itemArray[1].split(" \\(");
				name = nameArray[0];
				
				String itemPrice = itemArray[2].substring(1);
				BigDecimal price = null;
				
				try {
					price = new BigDecimal(itemPrice);
				} catch (NumberFormatException e ) {
					display.display("Cannot format price");
				}	
				
				if(getInputBalance().compareTo(price) < 0) {
					display.display(String.format("You do not have $%s to purchase %s", price, name));
					display.display("Please add money to continue");
					activatePurchaseMenu();
				}
				if(	!inventory.dispenseItem(name).equals(" ")) {
					BigDecimal originalBalance = getInputBalance();
					BigDecimal cost = price.multiply(new BigDecimal("-1.00"));
					itemCost = itemCost.add(cost);	
					setInputBalance(itemCost);
					setPurchasePrice(itemCost);
					BigDecimal newBalance = getInputBalance();
					
					String logEntry = String.format("%d %d",name, slotID);
					log(logEntry, originalBalance, newBalance);
					
					display.display(String.format("%s costs $%s", name, price));
					display.display(String.format("Money remaining: $%s",newBalance ));
				} else {
					display.display(String.format("%s is sold out. Returning to purchase menu.", name));
					activatePurchaseMenu();
				}
			}
		}
	}
	
	public void finishTransaction() { // TODO add logging
		BigDecimal currentBalance = getInputBalance();
		BigDecimal totalChangeReturned = new BigDecimal("0.00");
		BigDecimal zero = new BigDecimal("0.00");
		BigDecimal quarters = new BigDecimal("0.25");
		BigDecimal dimes = new BigDecimal("0.10");
		BigDecimal nickels = new BigDecimal("0.05");
		BigDecimal pennies = new BigDecimal("0.01");
		
		LinkedHashMap<String, BigDecimal>changeOptions = new LinkedHashMap<String, BigDecimal>();
				changeOptions.put("quarters", quarters);
				changeOptions.put("dimes", dimes);
				changeOptions.put("nickels", nickels);
				changeOptions.put("pennies", pennies);

		for (Map.Entry<String, BigDecimal>  coin : changeOptions.entrySet()) {
			BigDecimal returnAmount = new BigDecimal("0.00");
			String key = coin.getKey();
			BigDecimal value = coin.getValue();
			
			if(currentBalance.compareTo(zero) > 0) {
				String returnString = "";
				BigDecimal needCoins = currentBalance.divide(value);
				BigDecimal remainder = currentBalance.remainder(value);
				
				int length = needCoins.toString().length();
				int compare = remainder.compareTo(zero);
	
				if(compare > 0) {
					returnString = needCoins.toString().substring(0, length - 2);
				} else {
					returnString = needCoins.toString();
				}
				returnAmount = returnAmount.add(value.multiply(new BigDecimal(returnString)));
				currentBalance = remainder;				
			}
			totalChangeReturned = totalChangeReturned.add(returnAmount);
				
			if(returnAmount.compareTo(zero) > 0) {
				display.display(String.format("Returning $%s in %s", returnAmount, key));
			}			
		}
		display.display("Total change returned: $" + totalChangeReturned );
	
		BigDecimal changeBalance = getInputBalance().multiply(new BigDecimal("-1.00"));
		setInputBalance(changeBalance);
		BigDecimal newBalance = getInputBalance();
		
		log("GIVE CHANGE:", currentBalance, newBalance);
		
		vendingMachine.setBalance(purchasePrice);
		vendingMachine.activateMainMenu();
	}
	
	private void log(String logString, BigDecimal originalBalance, BigDecimal newBalance) {
		Logger updateLog = getLog();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String timestamp = dateFormat.format(new Date()).toString();
		String logEntry = String.format("%s %s $%s $%s", timestamp, logString, originalBalance, newBalance);
		updateLog.writeToFile(logEntry);        
	}
}
