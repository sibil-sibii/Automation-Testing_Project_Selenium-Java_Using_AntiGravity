package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// This utility class manages ExtentReports creation and screenshot capturing
public class ExtentManager {

    // This method creates and configures an ExtentReports instance for a specific functionality and browser
    public static ExtentReports createInstance(String functionality, String browser, String reportFilePath) {
        // Ensure reports directory exists
        File reportsDir = new File("reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
        sparkReporter.config().setDocumentTitle(functionality + " Test Report");
        sparkReporter.config().setReportName(functionality + " Automation Suite");
        sparkReporter.config().setTheme(Theme.STANDARD);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Functionality", functionality);
        extent.setSystemInfo("Browser", browser);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }

    // This method captures a screenshot and saves it inside reports/screenshots/ folder
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String destinationPath = "reports/screenshots/" + fileName;

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(destinationPath);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }

        // Return relative path so Extent HTML report can display it properly
        return "screenshots/" + fileName;
    }
}

