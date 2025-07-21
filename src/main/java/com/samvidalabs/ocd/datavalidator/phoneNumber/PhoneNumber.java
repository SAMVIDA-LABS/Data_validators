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



public class PhoneNumber 
{
//These variables store parts of a phone number
    private String countryCode;
    private String nationalSignificantNumber;
    private String extension;
    private String carrierCode;

    // Getter for countryCode
    public String getCountryCode() 
    {
        return countryCode;
    }

    // Setter for countryCode
    public void setCountryCode(String countryCode) 
    {
        this.countryCode = countryCode;
    }

    // Getter for nationalSignificantNumber
    public String getNationalSignificantNumber() 
    {
        return nationalSignificantNumber;
    }

    // Setter for nationalSignificantNumber
    public void setNationalSignificantNumber(String nationalSignificantNumber) 
   {
        this.nationalSignificantNumber = nationalSignificantNumber;
    }

    // Getter for extension
    public String getExtension() 
    {
        return extension;
    }

    // Setter for extension
    public void setExtension(String extension) 
  {
        this.extension = extension;
    }

    // Getter for carrierCode
    public String getCarrierCode() 
   {
        return carrierCode;
    }

    // Setter for carrierCode
    public void setCarrierCode(String carrierCode) 
    {
        this.carrierCode = carrierCode;
    }

    @Override
    public String toString() 
    {
        return "PhoneNumber{" +
                "countryCode='" + countryCode + '\'' +
                ", nationalSignificantNumber='" + nationalSignificantNumber + '\'' +
                ", extension='" + extension + '\'' +
                ", carrierCode='" + carrierCode + '\'' +
                '}';
    }
}
