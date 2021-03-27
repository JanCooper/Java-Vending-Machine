package com.techelevator;

import com.techelevator.models.Inventory;
import com.techelevator.models.items.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InventoryTests {

	Inventory inventory;
	String MESSAGE;
	String ITEM_NAME = "Chips";
	File FILE_PATH = new File(System.getProperty("user.dir"), "\\data\\test\\InventoryTestsData.csv");
	Chip CHIP = new Chip("Chips", "3.00", "Slot1");
	Candy CANDY = new Candy("Candy", "1.80", "Slot2");
	Drink DRINK = new Drink("Drink", "1.25", "Slot3");
	Gum GUM = new Gum("Gum", "0.85", "Slot4");
	List<Item> FILE_CONTENT = new ArrayList<Item>(Arrays.asList(
			CHIP,
			CANDY,
			DRINK,
			GUM
		));
	Iterator<Item> EXPECTED_ITERATOR;
	Iterator<Item> ACTUAL_ITERATOR;	
	
	@Before
	public void setup() {
		inventory = new Inventory(FILE_PATH);
	}

	@Test
	public void inventory_loadInventory_returnsListOfItems() {
		// arrange
		MESSAGE = "Inventory should consist of a list of items.";
		List<Item> expectedList = FILE_CONTENT;
		EXPECTED_ITERATOR = expectedList.iterator();
		Item expectedItem = (Item) EXPECTED_ITERATOR.next();
		Class<? extends Item> expected = expectedItem.getClass();
				
		// act

		List<Item> actualList = inventory.getInventory();
		ACTUAL_ITERATOR = actualList.iterator();
		Item actualItem = (Item) ACTUAL_ITERATOR.next();
		Class<? extends Item> actual = actualItem.getClass();
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_loadInventory_inventoryContainsSameObjectClasses() {
		// arrange
		MESSAGE = "Inventory should contain a Chip, Candy, Drink, and Gum object.";
		List<String> expectedList = new ArrayList<String>(
				Arrays.asList(
						"class com.techelevator.models.items.Chip",
						"class com.techelevator.models.items.Candy",
						"class com.techelevator.models.items.Drink",
						"class com.techelevator.models.items.Gum"
				));
			
		// act
		List<Item> actual = inventory.getInventory();	
		List<String> actualList = new ArrayList<String>(
				Arrays.asList(
						actual.get(0).getClass().toString(),
						actual.get(1).getClass().toString(),
						actual.get(2).getClass().toString(),
						actual.get(3).getClass().toString()	
					));

		// assert
		assertArrayEquals(expectedList.toArray(), actualList.toArray());
	}	

	@Test
	public void inventory_getInventoryList_returnsListOfStrings() {
		// arrange
		MESSAGE = "InventoryList should consist of a list of Strings.";
		String expected = "class java.lang.String";
				
		// act
		List<String> actualList = inventory.getInventoryList();
		String actual = actualList.get(0).getClass().toString();
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getInventoryList_stringsInListContainItemName() {
		// arrange
		MESSAGE = "InventoryList strings should contain the item name.";
		boolean expected = true;
				
		// act
		List<String> actualList = inventory.getInventoryList();
		boolean actual = actualList.get(0).contains("Chips");
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getInventoryList_stringsInListContainItemQuantity() {
		// arrange
		MESSAGE = "InventoryList strings should contain the item quantity.";
		boolean expected = true;
				
		// act
		List<String> actualList = inventory.getInventoryList();
		boolean actual = actualList.get(0).contains("5");
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getInventoryList_stringsInListContainSpace() {
		// arrange
		MESSAGE = "InventoryList strings should contain a space between item and quantity.";
		boolean expected = true;
				
		// act
		List<String> actualList = inventory.getInventoryList();
		boolean actual = actualList.get(0).contains(" ");
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getPurchaseItemList_returnsListOfStrings() {
		// arrange
		MESSAGE = "PurchaseOptionList should consist of a list of Strings.";
		String expected = "class java.lang.String";
				
		// act
		List<String> actualList = inventory.getPurchaseItemList();
		String actual = actualList.get(0).getClass().toString();
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getPurchaseItemList_stringsInListContainItemName() {
		// arrange
		MESSAGE = "InventoryList strings should contain the item name.";
		boolean expected = true;
				
		// act
		List<String> actualList = inventory.getPurchaseItemList();
		boolean actual = actualList.get(0).contains("Chips");
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getPurchaseItemList_stringsInListContainItemQuantity() {
		// arrange
		MESSAGE = "InventoryList strings should contain the item quantity.";
		boolean expected = true;
				
		// act
		List<String> actualList = inventory.getPurchaseItemList();
		boolean actual = actualList.get(0).contains("(5)");
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getPurchaseItemList_stringsInListContainSlotID() {
		// arrange
		MESSAGE = "InventoryList strings should contain the SlotID.";
		boolean expected = true;
				
		// act
		List<String> actualList = inventory.getPurchaseItemList();
		boolean actual = actualList.get(0).contains("Slot1");
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_getPurchaseItemList_stringsInListContainPrice() {
		// arrange
		MESSAGE = "InventoryList strings should contain the Price.";
		boolean expected = true;
				
		// act
		List<String> actualList = inventory.getPurchaseItemList();
		boolean actual = actualList.get(0).contains("$3.00");
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	@Test
	public void inventory_dispenseItem_dispensesFromCorrectItem() {
		// arrange
		MESSAGE = "Should update quantity on selected item.";
		String expected = "4";
		
		// act
		inventory.dispenseItem(ITEM_NAME);
		String actual = "";
		List<Item> newInventory = inventory.getInventory();
		for (Item item : newInventory) {
			if (item.getName().equals(ITEM_NAME)) {
				actual = item.getQuantity();
			}
		}
		
		// assert
		assertEquals(MESSAGE, expected, actual);
	}	
	/* changed to string return value
	@Test
	public void inventory_dispenseItem_returnsMessageIfQuantityNotSoldOut() {
		// arrange
		MESSAGE = "Returns \"Crunch Crunch, Yum!\" if the quantity is not sold out.";
		String expected = "Crunch Crunch, Yum!";
		
		// act
		String actual = inventory.dispenseItem(ITEM_NAME);
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}

	@Test
	public void inventory_dispenseItem_returnsFalseIfQuantityIsSoldOut() {
		// arrange
		MESSAGE = "Returns false if the quantity is sold out.";
		boolean expected = false;
		
		// act
		for (int i = 1; i <= 5; i++) {
			inventory.dispenseItem(ITEM_NAME);			
		}
		boolean actual = inventory.dispenseItem(ITEM_NAME);
				
		// assert
		assertEquals(MESSAGE, expected, actual);
	}	
*/
}

