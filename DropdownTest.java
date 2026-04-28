import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownTest extends BaseTest {

    @Test
    public void testSelectDropdown() throws Exception {
        // Navigate to dropdown page
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Thread.sleep(1000);

        // Click dropdown
        driver.findElement(By.id("dropdown")).click();
        Thread.sleep(500);

        // Select option
        driver.findElement(By.xpath("//option[@value='1']")).click();
        Thread.sleep(1000);

        // Get selected value
        String selectedValue = driver.findElement(By.id("dropdown")).getAttribute("value");

        // Assert result
        Assert.assertEquals(selectedValue, "1", "Dropdown selection failed!");
    }
}
