package com.techelevator.models.exceptions;

public class InputVerificationException  extends Exception {
	private String message;

	public InputVerificationException(String userInput, String message) {
		super(message);
		this.message = String.format("(%s) %s", userInput, message);
	}
	
    @Override
    public String getMessage()
    {
    	return message;
    }
}
