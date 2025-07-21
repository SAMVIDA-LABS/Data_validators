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

import java.util.Locale;
import java.util.ResourceBundle;
//class helps manage language translations for an application
public class LocalizationManager
 {
       //load language-specific messages from a file
	private ResourceBundle messages;
//initializes the correct language settings(Constructor)
    public LocalizationManager(Locale locale) 
    {
        messages = ResourceBundle.getBundle("messages/messages", locale);
    }
//Retrieves a specific message based on a key
    public String getMessage(String key) 
    {
        return messages.getString(key);
    }
}
