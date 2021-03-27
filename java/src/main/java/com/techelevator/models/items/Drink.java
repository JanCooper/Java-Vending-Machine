package com.techelevator.models.items;

public class Drink  extends Item {
	private String slotID;
	private final String message = "Glug Glug, Yum!";

	public Drink (String name, String price) {
		super(name, price);
	}
	public Drink ( String name, String price, String slotID) {
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
