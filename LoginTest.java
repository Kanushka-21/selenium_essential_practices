import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess() throws Exception {
        // Navigate to login page
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);

        // Enter credentials
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // Click login
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(2000);

        // Get message
        String message = driver.findElement(By.id("flash")).getText();

        // Assert result (REAL VALIDATION)
        Assert.assertTrue(message.contains("You logged into a secure area!"),
                "Login message not found!");
    }
}
