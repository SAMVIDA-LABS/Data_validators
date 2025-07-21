package com.samvidalabs.ocd.datavalidator.exceptions;

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
* ValidationException.java
*
* Version: 1.0.0
* Date: 2024-11-18
* Author: Samvida Labs - Ricky Jain
*
* Description: A highly configurable library for password validation.
*/

      // ValidationException class is a custom error in Java that helps handle validation failures
      //Error that must be handled(extends Exception)
      public class ValidationException extends Exception 
     {
            //It is used for object serialization
	    private static final long serialVersionUID = 7175301533524764297L;

	    public ValidationException(String message, Throwable cause) 
            {
                //Allows passing a message and cause when throwing the error
                super(message, cause);
             }
       }
