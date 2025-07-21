package com.samvidalabs.ocd.datavalidator.passwords;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.samvidalabs.ocd.datavalidator.exceptions.ValidationException;

/*
 * MIT License
 *
 * Copyright (c) 2024 Samvida Labs Private Limited
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * PasswordValidator.java
 *
 * Version: 1.0.0
 * Date: 2024-11-18
 * Author: Samvida Labs - Ricky Jain
 *
 * Description: A highly configurable library for password validation.
 */


//It is used to loads password validation rules from a JSON file
public class PasswordValidator 
{

    private JSONObject defaultConfig;
    //This ia a Constructor
    public PasswordValidator() 
    {
        this.defaultConfig = loadDefaultConfig();
    }
//Handles Errors if the file can't be loaded it throws an Error
    public JSONObject loadDefaultConfig() 
    {
        try 
        {
            return new JSONObject(new JSONTokener(getClass().getResourceAsStream("/password-rules.json")));
         }
         catch (Exception e) 
          {
            throw new RuntimeException("Failed to load default configuration", e);
           }
      }
//This method checks if a password meets validation rules
    public ValidationResult validate(String password, String configPath) throws ValidationException 
    {
        JSONObject config = configPath != null ? loadConfig(configPath) : this.defaultConfig;
        return validatePassword(password, config);
    }

    private JSONObject loadConfig(String configPath) throws ValidationException 
    {
        try 
           {
 //Converts the content into a JSON object
            String content = new String(Files.readAllBytes(Paths.get(configPath)));
            return new JSONObject(content);
           } 
            catch (Exception e) 
            {
//if something goes wrong, it throws a ValidationException Errors
            throw new ValidationException("Failed to load validation config from " + configPath, e);
            }
     }

    private ValidationResult validatePassword(String password, JSONObject config) 
     {
        ValidationResult result = new ValidationResult();

        int minLength = config.optInt("minLength", 8);
        int minUppercase = config.optInt("minUppercase", 1);
        int minLowercase = config.optInt("minLowercase", 1);
        int minDigits = config.optInt("minDigits", 1);
        int minSpecialChar = config.optInt("minSpecialChar", 1);

        int uppercaseCount = countMatches(password, "[A-Z]");
        int lowercaseCount = countMatches(password, "[a-z]");
        int digitCount = countMatches(password, "\\d");
        int specialCharCount = countMatches(password, "[!@#$%^&*(),.?\":{}|<>]");
        int totalCharCount = uppercaseCount + lowercaseCount + digitCount + specialCharCount;

        // Validate overall minimum length
        if (password.length() < minLength) 
        {
            result.addError("Password must be at least " + minLength + " characters long.");
        }

        // Validate minimum uppercase letters
        if (uppercaseCount < minUppercase) 
        {
            result.addError("Password must contain at least " + minUppercase + " uppercase letters.");
        }

        // Validate minimum lowercase letters
        if (lowercaseCount < minLowercase)
        {
            result.addError("Password must contain at least " + minLowercase + " lowercase letters.");
        }

        // Validate minimum digits
        if (digitCount < minDigits) 
        {
            result.addError("Password must contain at least " + minDigits + " digits.");
        }

        // Validate minimum special characters
        if (specialCharCount < minSpecialChar) {
            result.addError("Password must contain at least " + minSpecialChar + " special characters.");
        }

        // Validate total minimum character counts
        if (totalCharCount < minLength) 
        {
            result.addError("Password must meet the total minimum character requirements.");
        }

        // Check for disallowed characters
        String disallowedChars = config.optString("disallowedCharacters", "");
        if (!disallowedChars.isEmpty()) 
           {
            String escapedDisallowedChars = "[" + Pattern.quote(disallowedChars) + "]";
            if (password.matches(".*" + escapedDisallowedChars + ".*")) 
             {
                result.addError("Password contains disallowed characters: " + disallowedChars);
             }
         }

        result.setValid(result.getErrors().isEmpty());
        return result;
    }

    private int countMatches(String str, String regex) 
    {
        return (int) str.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .filter(s -> s.matches(regex))
                .count();
    }
}
