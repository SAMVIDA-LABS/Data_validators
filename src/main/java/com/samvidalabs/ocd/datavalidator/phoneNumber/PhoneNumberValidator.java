package com.samvidalabs.ocd.datavalidator.phoneNumber;

/**
 * ValidationConfigLoader.java
 *
 * Version: 1.0.0
 * Date: 2024-11-20
 * Author: Samvida Labs - Ricky Jain
 *
 * Description: A highly configurable library for password validation.
 */


import java.util.regex.Pattern;
//class checks if a phone number following the E.164 format
public class PhoneNumberValidator 
{
//Allows numbers to start with +, followed by digits (up to 15)
	 private static final String E164_REGEX = "^\\+?[1-9]\\d{1,14}$";
//Defines the pattern for valid phone numbers 
	 private static final Pattern e164Pattern = Pattern.compile(E164_REGEX);
//Checks if a phone number matches the E.164 format
	    public boolean isValid(String phoneNumber) 
           {
	        return e164Pattern.matcher(phoneNumber).matches();
	    }
//Throws a ValidationException if the phone number is invalid
	    public void validate(String phoneNumber) throws ValidationException 
            {
	        if (!isValid(phoneNumber)) 
                {
	            throw new ValidationException("Invalid phone number format.");
	        }
	        
	    }
}
