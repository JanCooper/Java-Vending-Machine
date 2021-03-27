package com.techelevator.models.items;

public class Gum extends Item {
	private String slotID;
	private final String message = "Chew Chew, Yum!";
	
	public Gum (String name, String price) {
		super(name, price);
	}
	public Gum ( String name, String price, String slotID) {
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
