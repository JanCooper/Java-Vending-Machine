package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import com.techelevator.application.VendingMachine;

public class VendingMachineTests {

	VendingMachine vendingMachine;
	String MESSAGE;
	BigDecimal BALANCE_UPDATE = new BigDecimal("1.00");
	
	public void setup() {
		vendingMachine = new VendingMachine();
	}
	
	public void VendingMachine_setBalance_updatesZeroBalance(){		
		// arrange
		MESSAGE = "Vending machine balance should equal $1.00 after update.";
		BigDecimal expected = new BigDecimal("1.00");
		
		// act
		vendingMachine.setBalance(BALANCE_UPDATE);
		BigDecimal actual = vendingMachine.getBalance();
		
		// assert
	assertEquals(MESSAGE,  expected, actual);
	}
	
	public void VendingMachine_setBalance_updatesNonZeroBalance(){		
		// arrange
		MESSAGE = "Vending machine balance should equal $3.00 after updates.";
		BigDecimal expected = new BigDecimal("3.00");
		
		// act
		vendingMachine.setBalance(BALANCE_UPDATE);
		vendingMachine.setBalance(BALANCE_UPDATE);
		vendingMachine.setBalance(BALANCE_UPDATE);
		BigDecimal actual = vendingMachine.getBalance();
		
		// assert
	assertEquals(MESSAGE,  expected, actual);
	}
}
