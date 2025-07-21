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

//class formats phone numbers in different ways
public class PhoneNumberFormatter 
{
            //Converts the phone number to the international format (E.164),including the country code
	    public String formatToE164(PhoneNumber phoneNumber) 
            {
	        return phoneNumber.getCountryCode() + phoneNumber.getNationalSignificantNumber();
	    }
            //Formats the number based on a specific region
	    public String formatForRegion(PhoneNumber phoneNumber, String region) 
            {
	        // Logic to format based on region
	        return "Formatted for " + region; // Placeholder
	    }
}
