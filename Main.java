import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create driver instance
        WebDriver driver = new ChromeDriver();

        // Open login page
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);

        // Find username field and enter tomsmith
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        // Find password field and enter password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // Find and click submit button
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for login to complete
        Thread.sleep(2000);

        // Get message from page
        String message = driver.findElement(By.id("flash")).getText();

        // Check if login successful
        if (message.contains("You logged into a secure area!")) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        // Close browser
        driver.quit();
    }
}
