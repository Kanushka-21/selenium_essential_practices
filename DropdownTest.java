import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DropdownTest {
    public static void main(String[] args) throws Exception {
        // Create driver instance
        WebDriver driver = new ChromeDriver();

        // Open dropdown page
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Thread.sleep(1000);

        // Click dropdown to open it
        driver.findElement(By.id("dropdown")).click();
        Thread.sleep(500);

        // Click Option 1 from dropdown
        driver.findElement(By.xpath("//option[@value='1']")).click();
        Thread.sleep(1000);

        // Get value of dropdown after selection
        String selectedValue = driver.findElement(By.id("dropdown")).getAttribute("value");

        // Verify selection
        if (selectedValue.equals("1")) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        // Close browser
        driver.quit();
    }
}
