import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileWriter;

public class FileUploadTest extends BaseTest {
    
    @Test
    public void testFileUpload() throws Exception {
        // Create test file
        File testFile = new File("test_file.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("This is a test file for upload");
        writer.close();
        
        // Navigate to upload page
        driver.get("https://the-internet.herokuapp.com/upload");
        Thread.sleep(1000);
        
        // Upload file
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        String filePath = testFile.getAbsolutePath();
        fileInput.sendKeys(filePath);
        Thread.sleep(500);
        
        // Submit form
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(2000);
        
        // Get upload message
        WebElement uploadMessage = driver.findElement(By.tagName("h3"));
        String messageText = uploadMessage.getText();
        
        // Assert upload success
        Assert.assertTrue(messageText.contains("File Uploaded"), 
            "File upload failed!");
        
        // Cleanup
        testFile.delete();
    }
}
