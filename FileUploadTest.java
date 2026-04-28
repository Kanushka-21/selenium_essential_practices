import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileWriter;

public class FileUploadTest {
    public static void main(String[] args) throws Exception {
        // Create a test file to upload
        File testFile = new File("test_file.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("This is a test file for upload");
        writer.close();

        // Create driver instance
        WebDriver driver = new ChromeDriver();

        // Open file upload page
        driver.get("https://the-internet.herokuapp.com/upload");
        Thread.sleep(1000);

        // Find file input element
        WebElement fileInput = driver.findElement(By.id("file-upload"));

        // Send file path to input
        String filePath = testFile.getAbsolutePath();
        fileInput.sendKeys(filePath);
        Thread.sleep(500);

        // Find and click submit button
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(2000);

        // Check if upload successful message appears
        WebElement uploadMessage = driver.findElement(By.tagName("h3"));
        String messageText = uploadMessage.getText();

        // Verify success
        if (messageText.contains("File Uploaded")) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        // Delete test file
        testFile.delete();

        // Close browser
        driver.quit();
    }
}
