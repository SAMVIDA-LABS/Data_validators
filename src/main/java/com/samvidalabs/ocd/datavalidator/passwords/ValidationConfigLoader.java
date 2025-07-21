package com.samvidalabs.ocd.datavalidator.passwords;


import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

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
 * ValidationConfigLoader.java
 *
 * Version: 1.0.0
 * Date: 2024-11-18
 * Author: Samvida Labs - Ricky Jain
 *
 * Description: A highly configurable library for password validation.
 */
//loads default password validation rules from a JSON file
public class ValidationConfigLoader 
 {

    public static JSONObject loadDefaultConfig() 
       {
           try (InputStream is = ValidationConfigLoader.class.getResourceAsStream("/password-rules.json"))
           {
            //Converts it to JSON using JSONTokener
            return new JSONObject(new JSONTokener(is));
           }
           //if the file can't be loaded, it throws a RuntimeException
           catch (Exception e) 
           {
            throw new RuntimeException("Failed to load default configuration", e);
           }
     }
}
