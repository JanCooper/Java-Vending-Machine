package com.techelevator.models.items;

import java.math.BigDecimal;

import com.techelevator.ui.DisplayOutput;

public abstract class Item {
	protected  String name;
	protected  BigDecimal price;	
	protected int quantity;
	DisplayOutput display = new DisplayOutput();
	
	public Item (String name, String price) {
		this.name = name;
		this.price =  new BigDecimal(price);
		quantity = 5;
	}

	public  abstract String getSlotID();
	public abstract String getMessage();
	
	public String getName() {
		return name;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	public String getQuantity() {
		String returnValue = "";
		if(quantity == 0) {
			returnValue = "Sold Out";
		} else {
			returnValue = String.valueOf(quantity);
		}
		return returnValue;
	}
	
	public void dispenseItem() {
		if(!getQuantity().equals("Sold Out") && Integer.parseInt(getQuantity()) > 0) {
			quantity--;
			display.display(getMessage());
		}		
	}
}
