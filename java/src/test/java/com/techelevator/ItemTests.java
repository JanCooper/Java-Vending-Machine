package com.techelevator;

import com.techelevator.models.items.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ItemTests {

	Chip CHIP;
	Gum GUM;
	Drink DRINK;
	Candy CANDY;
	String MESSAGE;
	
	@Before
	public void startup() {
		CHIP = new Chip("bbq", "0.50", "1A");
	}	
	
	@Test
	public void item_dispenseItem_removesOneFromQuantity() {
		// arrange
		MESSAGE = "Dispensing an Item should remove 1 from quantity.";
		String expected = "4";
				
		// act
		CHIP.dispenseItem();
		String actual = CHIP.getQuantity();
		
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void item_dispenseItem_doesNotRemoveOneIfQuantityIsZero() {
		// arrange
		MESSAGE = "Dispensing an Item should not make quantity a negative number.";
		String expected = "Sold Out";
				
		// act
		for (int i = 0; i < 10; i++) {
			CHIP.dispenseItem();
		}
		String actual = CHIP.getQuantity();
		
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void item_getQuantity_ReturnsFiveBeforeAnyPurchases() {
		// arrange
		MESSAGE = "Item should have quantity 5 before any purchases.";
		String expected = "5";
				
		// act
		String actual = CHIP.getQuantity();
		
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void item_getQuantity_ReturnsSoldOutIfQuantityIsZero() {
		// arrange
		MESSAGE = "Item should return Sold Out if the quantity is zero.";
		String expected = "Sold Out";
				
		// act		
		for (int i = 0; i < 5; i++) {
			CHIP.dispenseItem();
		}
		String actual = CHIP.getQuantity();
		
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
}
