package com.techelevator.application;

import com.techelevator.models.Inventory;
import com.techelevator.models.Purchase;
import com.techelevator.models.file_io.Logger;
import com.techelevator.ui.*;
import com.techelevator.ui.helper.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO add Sales Report
public class VendingMachine 
{
	private Inventory inventory;
	private List<String> mainMenu;
	private BigDecimal balance;
	
	private File file = new File(System.getProperty("user.dir"), "\\data\\main\\vendingmachine.csv");
	private DisplayOutput display = new DisplayOutput();
	private Menu menu = new Menu();
	private Purchase purchase;
	private Logger log;
	
	////////////////////////////////////////////////////////////////////////////
	
	public VendingMachine() {
		setMainMenu();
		this.balance = new BigDecimal("0.00");
		this.inventory = new Inventory(file);
	}
	
	////////////////////////////////////////////////////////////////////////////
    
	private void setMainMenu(){
		mainMenu = new ArrayList<String>(
			Arrays.asList(	
				"(1) Display Vending Machine Items",
				"(2) Purchase",
				"(3) Exit"
			));
	}
	
	private void setPurchase() {
		purchase = new Purchase(inventory, this);
	}
	
	public void setBalance(BigDecimal input) {
		balance = balance.add(input);
	}
	
	public  List<String> getMainMenu(){
		return mainMenu;
	}
	
	public  Inventory getInventory()
	{
		return  inventory;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public Logger getLog() {
		return log;
	}
	
	////////////////////////////////////////////////////////////////////////////
	
    public void run()  {
		log = new Logger();
    	activateMainMenu();
    }
        
    public void activateMainMenu() {
    	setMainMenu();
    	int userChoice = menu.menuInOut(getMainMenu(), "Please choose 1, 2, or 3");
    	
    	if(VerifyInput.verifyMainMenu(userChoice) == -1) {
			activateMainMenu();
		}    	
    	getMainMenuOption(userChoice);
    }
    
	public void getMainMenuOption(int option){		
		switch (option) {
		case 1 : 
			display.display(inventory.getInventoryList()); // TODO verify quantity 0 shows as Sold Out
			getTruncatedMenuOption();			
			break;
		case 2 : 
			createPurchase();
			purchase.activatePurchaseMenu();
			break;
		default : exit();
			break;
		}
	}
    
    public void  getTruncatedMenuOption() {
    	List<String> newMainMenu = getMainMenu();
    	newMainMenu.remove(0);
    	display.display(" ");
		int userChoice = menu.menuInOut(newMainMenu, "Please choose 2 or 3");
		if (VerifyInput.verifyMainMenu(userChoice) == -1) {
				getTruncatedMenuOption();
			}	  	
		getMainMenuOption(userChoice);    	
    }
    
    private void createPurchase() {
    	setPurchase();
    	purchase.activatePurchaseMenu();
    }
    
    public void exit() {
    	display.display("Thank you for shopping.\nPlease come again!");
    	System.exit(0);
    }
}
