import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;

public class AlertsTest {
    public static void main(String[] args) throws Exception {
        // Create driver instance
        WebDriver driver = new ChromeDriver();

        // Open alerts page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(1000);

        // Click button to trigger alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(500);

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Get alert text
        String alertText = alert.getText();

        // Accept the alert (click OK)
        alert.accept();
        Thread.sleep(1000);

        // Verify alert was handled
        if (alertText.contains("I am a JS Alert")) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        // Close browser
        driver.quit();
    }
}
