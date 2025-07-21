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

public class PhoneNumberParser 
{
//class extracts details from a phone number string
    public PhoneNumber parse(String phoneNumber) 
{
//Creates a PhoneNumber object to store parsed data
        PhoneNumber number = new PhoneNumber();
        
        // Example logic to parse the phone number
        if (phoneNumber.startsWith("+")) 
         {
            // Split by space if you expect a space between country code and national number
            String[] parts = phoneNumber.split(" ");
            number.setCountryCode(parts[0]); // This will always work
            
            // Check if there's a national number
            if (parts.length > 1) 
            {
                number.setNationalSignificantNumber(parts[1]);
            } 
              else 
              {
                // Handle the case where the national number is not provided
                number.setNationalSignificantNumber(""); // or throw an exception or log a warning
                System.out.println("Warning: National number is missing.");
             }
        }
        
        return number;
    }
}