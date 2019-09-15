package com.optimum.employeedashboard.model;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class CustomPasswordGenerator {
	
	private static PasswordGenerator gen = new PasswordGenerator();

	// generic method to generate random password that matches acceptance criteria
	public static String generatePassword() {
		
		// uses Passay library to generate password according to rules
		
		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
	            return "ERROR_CODE";
	        }
	 
	        public String getCharacters() {
	            return "!@#$%^&*()_+";
	        }
		};
		
	    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(3);
	 
	    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(3);
	 
	    CharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(3);
		
		
		CharacterRule splCharRule = new CharacterRule(specialChars);
	    splCharRule.setNumberOfCharacters(1);
	    
	    
	    String password = gen.generatePassword(12, splCharRule, digitRule, upperCaseRule, lowerCaseRule);
	    // Set to generate alpha-numeric password of length 12
		// with at least 1 special character
	    return password;
		
	}
}
