package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.techelevator.models.exceptions.InputVerificationException;
import com.techelevator.ui.helper.VerifyInput;

import junit.framework.AssertionFailedError;

public class VerifyInputTests {
	
	String MESSAGE;
	String INPUT_NUMBER = "1";
	String CORRECT_MONEY_1 = "1";
	String CORRECT_MONEY_5 = "5";
	String CORRECT_SLOTID_A1 = "A1";
	String CORRECT_SLOTID_D4 = "D4";
	String CORRECT_SLOTID_B3 = "B3";
	
	int CORRECT_MENU_OPTION_3 = 3;
	int CORRECT_MENU_OPTION_1 = 1;
	
	@Test
	public void verifyInput_verifyNumber_inputIsNumber() {
		// Arrange 
		MESSAGE = "Input is a number";
		int expected = 1;
		
		// Act
		int actual = VerifyInput.verifyNumber(INPUT_NUMBER);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	
	
	@Test
	public void verifyInput_verifyMainMenu_inPutIsValidMenuOption_1() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input 1 is a valid Main Menu option";
		int expected = 1;
		
		// Act
		int actual = VerifyInput.verifyMainMenu(CORRECT_MENU_OPTION_1);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	

	@Test
	public void verifyInput_verifyMainMenu_inPutIsValidMenuOption_3() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input 3 is a valid Main Menu option";
		int expected = 3;
		
		// Act
		int actual = VerifyInput.verifyMainMenu(CORRECT_MENU_OPTION_3);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	

	@Test
	public void verifyInput_verifyPurchaseMenu_inPutIsValidMenuOption_3() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input 3 is a valid Purchase Menu option";
		int expected = 3;
		
		// Act
		int actual = VerifyInput.verifyPurchaseMenu(CORRECT_MENU_OPTION_3);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	
	
	@Test
	public void verifyInput_verifyPurchaseMenu_inPutIsValidMenuOption_1() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input 1 is a valid Purchase Menu option";
		int expected = 1;
		
		// Act
		int actual = VerifyInput.verifyPurchaseMenu(CORRECT_MENU_OPTION_1);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	
	
	@Test
	public void verifyInput_verifyMoney_inPutIsValidMoneyOption_1() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input 1 is a valid Purchase Menu option";
		BigDecimal expected = new BigDecimal("1.00");
		
		// Act
		BigDecimal actual = VerifyInput.verifyMoney(CORRECT_MONEY_1);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	
	
	@Test
	public void verifyInput_verifyMoney_inPutIsValidMoneyOption_5() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input 5 is a valid Purchase Menu option";
		BigDecimal expected = new BigDecimal("5");
		
		// Act
		BigDecimal actual = VerifyInput.verifyMoney(CORRECT_MONEY_5);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	
	
	@Test
	public void verifyInput_verifySlotID_inPutIsValidSlotIDOption_A1() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input A1 is a valid slot ID option";
		String expected = "A1";
		
		// Act
		String actual = VerifyInput.verifySlotID(CORRECT_SLOTID_A1);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	
	
	@Test
	public void verifyInput_verifySlotID_inPutIsValidSlotIDOption_D4() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input D4 is a valid slot ID option";
		String expected = "D4";
		
		// Act
		String actual = VerifyInput.verifySlotID(CORRECT_SLOTID_D4);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}
	
	
	@Test
	public void verifyInput_verifySlotID_inPutIsValidSlotIDOption_B3() throws InputVerificationException {
		// Arrange 
		MESSAGE = "Input B3 is a valid slot ID option";
		String expected = "B3";
		
		// Act
		String actual = VerifyInput.verifySlotID(CORRECT_SLOTID_B3);
		
		// Assert
		assertEquals(MESSAGE, expected, actual);
	}	
}
