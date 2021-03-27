package com.techelevator.models.items;

public class Chip extends Item {
	private String slotID;
	private final String message = "Crunch Crunch, Yum!";
	
	public Chip (String name, String price) {
		super(name, price);
	}
	public Chip ( String name, String price, String slotID) {
		super(name, price);
		this.slotID = slotID;
	}
	
	@Override
	public String getSlotID () {
		return slotID;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
