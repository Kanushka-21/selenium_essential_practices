import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertsTest extends BaseTest {

    @Test
    public void testAlertHandling() throws Exception {
        // Navigate to alerts page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(1000);

        // Click button to trigger alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(500);

        // Switch to alert
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        // Accept alert
        alert.accept();
        Thread.sleep(1000);

        // Assert alert message
        Assert.assertTrue(alertText.contains("I am a JS Alert"),
                "Alert message not found!");
    }
}
