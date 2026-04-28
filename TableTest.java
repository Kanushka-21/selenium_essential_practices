import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class TableTest {
    public static void main(String[] args) throws Exception {
        // Create driver instance
        WebDriver driver = new ChromeDriver();
        
        // Open tables page
        driver.get("https://the-internet.herokuapp.com/tables");
        Thread.sleep(1000);
        
        // Find the first table
        WebElement table = driver.findElement(By.id("table1"));
        
        // Find all rows in table body
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        
        // Get first row data (skip header)
        WebElement firstRow = rows.get(1);
        List<WebElement> cells = firstRow.findElements(By.tagName("td"));
        
        // Get cell values
        String lastName = cells.get(0).getText();
        String firstName = cells.get(1).getText();
        String email = cells.get(2).getText();
        
        // Print what we found
        System.out.println("Found: " + lastName + " " + firstName + " " + email);
        
        // Verify data - checking if we got valid data
        if (lastName.length() > 0 && firstName.length() > 0) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        
        // Close browser
        driver.quit();
    }
}
