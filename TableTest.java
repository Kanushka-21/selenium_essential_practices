import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TableTest extends BaseTest {
    
    @Test
    public void testReadTableData() throws Exception {
        // Navigate to tables page
        driver.get("https://the-internet.herokuapp.com/tables");
        Thread.sleep(1000);
        
        // Find the first table
        WebElement table = driver.findElement(By.id("table1"));
        
        // Find all rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        
        // Get first data row (skip header)
        WebElement firstRow = rows.get(1);
        List<WebElement> cells = firstRow.findElements(By.tagName("td"));
        
        // Extract data
        String lastName = cells.get(0).getText();
        String firstName = cells.get(1).getText();
        String email = cells.get(2).getText();
        
        // Assert data exists
        Assert.assertTrue(lastName.length() > 0, "Last name is empty!");
        Assert.assertTrue(firstName.length() > 0, "First name is empty!");
        Assert.assertTrue(email.length() > 0, "Email is empty!");
    }
}
