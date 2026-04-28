# Selenium Essential Practices

This project demonstrates the evolution of Selenium test automation from basic scripts to a professional TestNG framework. The project includes five automated tests for the-internet.herokuapp.com website.

## Project Overview

This is an automation testing project that shows how to build a scalable and maintainable test suite using Selenium WebDriver and TestNG framework. The tests automate various scenarios like login, dropdown selection, alert handling, table data reading, and file uploads.

## Testing Journey

### Stage 1: Basic Selenium (Initial Approach)

The first approach used raw Selenium with a main() method. Each test opened a browser, performed actions, and verified results using simple if/print statements.

Example:
```java
public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button")).click();
        
        WebElement message = driver.findElement(By.id("flash"));
        if (message.getText().contains("You logged in")) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }
        
        driver.quit();
    }
}
```

Issues with this approach:
- Code repetition across all tests
- Manual verification with if/print statements
- No test framework or organization
- Difficult to run multiple tests together
- No professional reporting

### Stage 2: Individual Test Files (Before TestNG)

Created five separate test files for different scenarios:
- LoginTest.java - Tests login functionality
- DropdownTest.java - Tests dropdown selection
- AlertsTest.java - Tests JavaScript alert handling
- TableTest.java - Tests table data extraction
- FileUploadTest.java - Tests file upload functionality

Each file still used main() method and manual if/print verification. The code was more organized but still had duplication and no framework support.

### Stage 3: TestNG Framework (Professional Grade)

Converted all tests to use TestNG framework with proper structure and organization.

#### BaseTest.java - Foundation Class

Created a base test class that handles setup and teardown for all tests:

```java
public class BaseTest {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

This class eliminates code duplication. All test classes inherit from BaseTest and automatically get setup and teardown methods.

#### LoginTest.java - Test Implementation

```java
public class LoginTest extends BaseTest {
    
    @Test
    public void testLoginSuccess() throws Exception {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);
        
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(2000);
        
        String message = driver.findElement(By.id("flash")).getText();
        
        Assert.assertTrue(message.contains("You logged into a secure area!"),
                "Login message not found!");
    }
}
```

#### DropdownTest.java - Test Implementation

```java
public class DropdownTest extends BaseTest {
    
    @Test
    public void testSelectDropdown() throws Exception {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Thread.sleep(1000);
        
        driver.findElement(By.id("dropdown")).click();
        Thread.sleep(500);
        
        driver.findElement(By.xpath("//option[@value='1']")).click();
        Thread.sleep(1000);
        
        String selectedValue = driver.findElement(By.id("dropdown")).getAttribute("value");
        
        Assert.assertEquals(selectedValue, "1", "Dropdown selection failed!");
    }
}
```

#### AlertsTest.java - Test Implementation

```java
public class AlertsTest extends BaseTest {
    
    @Test
    public void testAlertHandling() throws Exception {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(1000);
        
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(500);
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Thread.sleep(1000);
        
        Assert.assertTrue(alertText.contains("I am a JS Alert"),
                "Alert message not found!");
    }
}
```

#### TableTest.java - Test Implementation

```java
public class TableTest extends BaseTest {
    
    @Test
    public void testReadTableData() throws Exception {
        driver.get("https://the-internet.herokuapp.com/tables");
        Thread.sleep(1000);
        
        WebElement table = driver.findElement(By.id("table1"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        
        WebElement firstRow = rows.get(1);
        List<WebElement> cells = firstRow.findElements(By.tagName("td"));
        
        String lastName = cells.get(0).getText();
        String firstName = cells.get(1).getText();
        String email = cells.get(2).getText();
        
        Assert.assertTrue(lastName.length() > 0, "Last name is empty!");
        Assert.assertTrue(firstName.length() > 0, "First name is empty!");
        Assert.assertTrue(email.length() > 0, "Email is empty!");
    }
}
```

#### FileUploadTest.java - Test Implementation

```java
public class FileUploadTest extends BaseTest {
    
    @Test
    public void testFileUpload() throws Exception {
        File testFile = new File("test_file.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("This is a test file for upload");
        writer.close();
        
        driver.get("https://the-internet.herokuapp.com/upload");
        Thread.sleep(1000);
        
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        String filePath = testFile.getAbsolutePath();
        fileInput.sendKeys(filePath);
        Thread.sleep(500);
        
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(2000);
        
        WebElement uploadMessage = driver.findElement(By.tagName("h3"));
        String messageText = uploadMessage.getText();
        
        Assert.assertTrue(messageText.contains("File Uploaded"), 
            "File upload failed!");
        
        testFile.delete();
    }
}
```

#### testng.xml - Test Configuration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-current.dtd">
<suite name="Selenium Test Suite">
    <test name="Automation Tests">
        <classes>
            <class name="LoginTest"/>
            <class name="DropdownTest"/>
            <class name="AlertsTest"/>
            <class name="TableTest"/>
            <class name="FileUploadTest"/>
        </classes>
    </test>
</suite>
```

## Key Improvements with TestNG

1. Code Reuse - Setup and teardown code is in BaseTest.java, not repeated in each test
2. Professional Assertions - Uses Assert.assertTrue() and Assert.assertEquals() instead of if/print
3. Organized Structure - Test classes inherit from BaseTest and use @Test annotation
4. Configuration Management - testng.xml file centrally manages all tests
5. Framework Support - TestNG provides test execution, reporting, and lifecycle management
6. Scalability - Easy to add new tests without duplicating code
7. Professional Reporting - Generates HTML and XML reports automatically

## How to Run Tests

Compile all test files:
```bash
javac -cp ".;lib/*" BaseTest.java LoginTest.java DropdownTest.java AlertsTest.java TableTest.java FileUploadTest.java
```

Run all tests using TestNG:
```bash
java -cp ".;lib/*" org.testng.TestNG testng.xml
```

Expected output:
```
[TestNG] Running:
  testng.xml

===============================================
Selenium Test Suite
Total tests run: 5, Passes: 5, Failures: 0, Skips: 0
===============================================
```

## Project Structure

```
selenium_essential_practices/
├── BaseTest.java           (Base class with setup/teardown)
├── LoginTest.java          (Login functionality test)
├── DropdownTest.java       (Dropdown selection test)
├── AlertsTest.java         (Alert handling test)
├── TableTest.java          (Table data reading test)
├── FileUploadTest.java     (File upload test)
├── testng.xml              (TestNG configuration)
└── lib/                    (Dependencies)
    ├── selenium-*.jar
    ├── testng-7.9.0.jar
    ├── jcommander-1.82.jar
    └── slf4j-*.jar
```

## Dependencies

- Selenium WebDriver 4.43.0
- TestNG 7.9.0
- JCommander 1.82
- SLF4J 2.0.9
- Java 21

## What This Project Demonstrates

1. Evolution from basic Selenium to professional TestNG framework
2. Object-oriented design with BaseTest inheritance pattern
3. Proper test organization and structure
4. Professional assertions and test verification
5. Configuration management with testng.xml
6. Best practices for automation testing
7. Scalable and maintainable test suite design

## Test Scenarios

The project includes tests for the following scenarios on https://the-internet.herokuapp.com/:

1. Login - Tests user authentication with valid credentials
2. Dropdown - Tests dropdown element selection
3. Alerts - Tests JavaScript alert handling and acceptance
4. Table - Tests reading data from HTML tables
5. File Upload - Tests file upload functionality

All tests pass successfully with the current implementation.