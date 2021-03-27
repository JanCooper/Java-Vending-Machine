package com.techelevator.models.items;;

public class Candy extends Item {
	private String slotID;
	private final String message = "Munch Munch, Yum!";
	

	public Candy (String name, String price) {
		super(name, price);
	}
	public Candy ( String name, String price, String slotID) {
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
