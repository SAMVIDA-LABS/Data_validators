**DataValidator Library**

**DataValidator** is a highly configurable and lightweight Java library for validating data, with a primary focus on password validation. It allows developers to define custom validation rules through a JSON configuration file and perform robust checks for a variety of requirements.

-----
**Features**

- **Configurable Rules**: Supports custom rules for minimum length, uppercase, lowercase, digits, special characters, and disallowed characters.
- **Dynamic Validation**: Reads validation rules from a JSON file, making it flexible and adaptable.
- **Lightweight**: Minimal dependencies and optimized for performance.
- **Extensible**: Can be extended to validate other data types in future versions.
-----
**Installation**

**Maven**

Add the following dependency to your pom.xml:

<dependency>

`    `<groupId>com.samvidalabs.ocd</groupId>

`    `<artifactId>datavalidator</artifactId>

`    `<version>0.0.1-SNAPSHOT</version>

</dependency>


**Manual Installation**

1. Clone the repository:

git clone <https://github.com/samvidalabs/datavalidator.git>

1. Build the JAR file:

mvn clean package

1. Include the generated JAR file (target/datavalidator-0.0.1-SNAPSHOT.jar) in your project dependencies.


### **Usage**
#### **1. Default Configuration**
The library includes a default configuration (password-rules.json) with the following rules:

{

`  `"minLength": 8,

`  `"minUppercase": 1,

`  `"minLowercase": 1,

`  `"minDigits": 1,

`  `"minSpecialChar": 1,

`  `"disallowedCharacters": "{}[]<>"

}


You can use the default configuration without any additional setup:

PasswordValidator validator = new PasswordValidator();

String password = "StrongP@ssword1";

ValidationResult result = validator.validate(password, null);

if (result.isValid()) {

`    `System.out.println("Password is valid!");

} else {

`    `System.out.println("Password validation failed:");

`    `result.getErrors().forEach(System.out::println);

}



#### **2. Custom Configuration**
To use a custom JSON configuration, specify the file path:

String customConfigPath = "path/to/custom-rules.json";

PasswordValidator validator = new PasswordValidator();

String password = "CustomP@ss1";

ValidationResult result = validator.validate(password, customConfigPath);

if (result.isValid()) {

`    `System.out.println("Password is valid!");

} else {

`    `System.out.println("Password validation failed:");

`    `result.getErrors().forEach(System.out::println);

}



Sample custom JSON file:

{

`  `"minLength": 10,

`  `"minUppercase": 2,

`  `"minLowercase": 2,

`  `"minDigits": 2,

`  `"minSpecialChar": 1,

`  `"disallowedCharacters": "!@#$%^"

}




3. #### **Handling Validation Results**
The ValidationResult object contains the validation status and error messages:

if (!result.isValid()) {

`    `result.getErrors().forEach(error -> System.out.println("Error: " + error));

}





### **Configuration Parameters**

|**Parameter**|**Description**|**Default Value**|
| :-: | :-: | :-: |
|minLength|Minimum total length of the password|8|
|minUppercase|Minimum number of uppercase letters required|1|
|minLowercase|Minimum number of lowercase letters required|1|
|minDigits|Minimum number of numeric digits required|1|
|minSpecialChar|Minimum number of special characters required|1|
|disallowedCharacters|Characters that are not allowed in the password|"{}[]<>"|




**Examples**

**Valid Password**

For the default configuration:

- Input: Password@123
- Output:

Password is valid!


#### **Invalid Password**
For the custom configuration:

{

`  `"minLength": 10,

`  `"minUppercase": 2,

`  `"minLowercase": 2,

`  `"minDigits": 2,

`  `"minSpecialChar": 1,

`  `"disallowedCharacters": "!@#$%^"

}


·  Input: WeakPass1

·  Output:

Password validation failed:

\- Password must be at least 10 characters long.

\- Password must contain at least 2 uppercase letters.

\- Password must contain at least 2 digits.


### **Building the Project**
To build the library, run:

mvn clean package


The JAR file will be available in the target directory.



### **Running Tests**
To execute the test cases:

mvn test


Ensure all tests pass before deploying:

\-------------------------------------------------------

` `T E S T S

\-------------------------------------------------------

Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

\-------------------------------------------------------




### **Future Enhancements**
- Add support for validating other data types (e.g., emails, phone numbers).
- Provide built-in integration with popular frameworks like Spring Boot.
- Support asynchronous validation for large-scale applications.

### **License**
This project is licensed under the MIT License. See the LICENSE file for details or follow: https://opensource.org/licenses/MIT

