package Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListeners implements ITestListener {
	
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test FAILED: " + result.getName());
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        
        if (driver != null) {
            String testName = result.getName();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            
            File targetDirectory = new File("./screenshots");
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(targetDirectory + "/" + fileName);
            
            try {
                FileHandler.copy(sourceFile, destFile);
                System.out.println("Screenshot saved successfully to: " + destFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test PASSED: " + result.getName());
    }

}
