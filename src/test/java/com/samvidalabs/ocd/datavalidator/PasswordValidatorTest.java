package com.samvidalabs.ocd.datavalidator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samvidalabs.ocd.datavalidator.exceptions.ValidationException;
import com.samvidalabs.ocd.datavalidator.passwords.PasswordValidator;
import com.samvidalabs.ocd.datavalidator.passwords.ValidationResult;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


/*
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
* PasswordValidatorTest.java
*
* Version: 1.0.0
* Date: 2024-11-18
* Author: Samvida Labs - Ricky Jain
*
* Description: A highly configurable library for password validation.
*/

class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
    }

    @Test
    void testValidPasswordDefaultConfig() throws ValidationException {
        // Given a valid password
        String validPassword = "ValidP@ss123";

        // When validating the password
        ValidationResult result = validator.validate(validPassword, null);

        // Then the password should be valid
        assertThat(result.isValid()).isTrue();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    void testPasswordTooShort() throws ValidationException {
        // Given a password shorter than the minimum length
        String shortPassword = "P@1";

        // When validating the password
        ValidationResult result = validator.validate(shortPassword, null);

        // Then the password should be invalid and include the correct error
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).contains("Password must be at least 8 characters long.");
    }

    @Test
    void testMissingUppercaseLetters() throws ValidationException {
        // Given a password with no uppercase letters
        String noUppercase = "password123!";

        // Retrieve the configured minimum uppercase letters
        int minUppercase = validator.loadDefaultConfig().optInt("minUppercase", 1);

        // When validating the password
        ValidationResult result = validator.validate(noUppercase, null);

        // Then the password should be invalid and include the correct error
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).contains("Password must contain at least " + minUppercase + " uppercase letters.");
    }


    @Test
    void testMissingSpecialCharacters() throws ValidationException {
        // Given a password with no special characters
        String noSpecialChar = "Password123";

        // When validating the password
        ValidationResult result = validator.validate(noSpecialChar, null);

        // Then the password should be invalid and include the correct error
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).contains("Password must contain at least 1 special characters.");
    }

    @Test
    void testCustomConfigurationValidation() throws Exception {
        // Given a custom configuration JSON file
        String customConfigPath = Paths.get("src/main/resources/test-password-rules.json").toString();

        // And a password that violates the custom configuration
        String invalidPassword = "Short1";

        // When validating the password
        ValidationResult result = validator.validate(invalidPassword, customConfigPath);

        // Then the password should be invalid
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).contains("Password must contain at least 2 uppercase letters.");
        assertThat(result.getErrors()).contains("Password must contain at least 2 special characters.");
    }

    @Test
    void testDisallowedCharacters() throws ValidationException {
        // Given a password with disallowed characters
        String disallowedPassword = "Password{123}";

        // When validating the password
        ValidationResult result = validator.validate(disallowedPassword, null);

        // Then the password should be invalid
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).contains("Password contains disallowed characters: {}[]<>");
    }

    @Test
    void testEmptyPassword() throws ValidationException {
        // Given an empty password
        String emptyPassword = "";

        // When validating the password
        ValidationResult result = validator.validate(emptyPassword, null);

        // Then the password should be invalid
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).contains("Password must be at least 8 characters long.");
    }

    @Test
    void testInvalidConfigFile() {
        // Given an invalid configuration file path
        String invalidPath = "nonexistent/path/to/config.json";

        // When validating the password
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> validator.validate("Password123!", invalidPath)
        );

        // Then a ValidationException should be thrown
        assertThat(exception.getMessage()).contains("Failed to load validation config");
    }

    @Test
    void testValidPasswordWithCustomConfig() throws Exception {
        // Given a custom configuration JSON file
        String customConfigPath = Paths.get("src/main/resources/test-password-rules.json").toString();

        // And a password that meets the custom configuration
        String validPassword = "Cust0mP@s5!";

        // When validating the password
        ValidationResult result = validator.validate(validPassword, customConfigPath);

        // Then the password should be valid
        assertThat(result.isValid()).isTrue();
        assertThat(result.getErrors()).isEmpty();
    }
}
