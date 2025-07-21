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


//class is a custom error
public class ValidationException extends Exception 
{
//it is a special kind of error that must be handled  
    public ValidationException(String message) 
    {
//First Constructor(Accepts an error message)
        super(message); 
    }
    public ValidationException(String message, Throwable cause) 
    {
//Second Constructor(Accepts an error message and the root cause of the error)
        super(message, cause);
    }
}