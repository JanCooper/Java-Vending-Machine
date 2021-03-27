package com.techelevator;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Inventory;
import com.techelevator.models.Purchase;

public class PurchaseTests {

	Purchase purchase;
	Inventory inventory;
	VendingMachine vendingMachine = new VendingMachine();
	String MESSAGE;
	File FILE_PATH = new File(System.getProperty("user.dir"), "\\data\\test\\InventoryTestsData.csv");
	BigDecimal ADD_1 = new BigDecimal("1.00");
	BigDecimal ADD_5 = new BigDecimal("5.00");
	BigDecimal ADD_10 = new BigDecimal("10.00");
	
	
	@Before
	public void setup() {
		inventory = new Inventory(FILE_PATH);
		purchase = new Purchase(inventory, vendingMachine);
	}
	
	@Test
	public void purchase_addMoney_InputShouldIncreaseInputBalance() {
		// arrange
		MESSAGE = "Should increase input balance by$1.00";
		BigDecimal expected = new BigDecimal("1.00");
		
		// act 
		purchase.addMoney(ADD_1);
		BigDecimal actual = purchase.getInputBalance();

		// assert
		assertEquals(MESSAGE, expected, actual);		
	}
	/*
	@Test
	public void purchase_dispenseItem_shouldRemove1FromCorrectItem() {
		// arrange
		MESSAGE = "Should remove 1 from correct item quantity remaining";
		String expected = "4";
		
		// act 
		purchase.dispenseItem("Chips");
		BigDecimal actual = purchase.getInputBalance();

		// assert
		assertEquals(MESSAGE, expected, actual);		
	}
	*/
	
	
	
}
