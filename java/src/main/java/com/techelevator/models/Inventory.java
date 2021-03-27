package com.techelevator.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.techelevator.models.items.*;
import com.techelevator.models.file_io.FileLoader;

public class Inventory {
	private List<Item> inventory;
	
	public Inventory (File file) {
		loadInventory(file);
	}

	public List<Item> getInventory(){
		return inventory;
	}

	public void loadInventory(File file) {		
		FileLoader fileLoader = new FileLoader();
		List<String> fileContent = fileLoader.getFileContent(file);
		inventory = new ArrayList<Item>();
		
		for (String string : fileContent) {
			String[] line = string.split("\\|");
			
			String slotID = line[0];
			String name = line[1];
			String price = line[2];
			String type = line[3];
			
			switch(type) {
				case "Chip" : inventory.add(new Chip(name, price, slotID));
					break;
				case "Candy" : inventory.add(new Candy(name, price, slotID));
					break;
				case "Drink" : inventory.add(new Drink(name, price, slotID));
					break;
				default : inventory.add(new Gum(name, price, slotID));
					break;
			}		
		}
	}
	
	public List<String> getInventoryList(){
		List<String> inventoryList = new ArrayList<>();
		List<Item> inventory = getInventory();
		
		for (Item item : inventory) {
			inventoryList.add(String.format("%s     %s",item.getName(), item.getQuantity()).toString());
		}		
		return inventoryList;
	}
	
	public List<String> getPurchaseItemList(){
		List<String> purchaseOptionList = new ArrayList<>();
		List<Item> inventory = getInventory();
		
		for (Item item : inventory) {
			purchaseOptionList.add(String.format("%s     %s (%s)     $%s",item.getSlotID(), item.getName(), item.getQuantity(), item.getPrice()));
		}		
		return purchaseOptionList;
	}
	
	public String dispenseItem(String name) {
		String dispense = " ";
		for (Item item : inventory) {
			if(item.getName().equals(name) && !item.getQuantity().equals("Sold Out")) {
				dispense = item.getMessage();
				item.dispenseItem();
			}
		}
		return dispense;
	}
}
