package utils;

import base.Base_page;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// This listener handles test events and generates a separate ExtentReport for each functionality
public class ReportListener implements ITestListener {

    // Map to hold ExtentReports instances per functionality and browser
    private static Map<String, ExtentReports> reportsMap = new HashMap<>();

    // Map to hold active test nodes
    private static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();

    // Timestamp for report file naming
    private static String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());

    // Helper method to extract clean functionality name from test class name
    private String getFunctionalityName(ITestResult result) {
        String simpleName = result.getTestClass().getRealClass().getSimpleName();
        if (simpleName.endsWith("Tests")) {
            return simpleName.substring(0, simpleName.length() - 5);
        }
        return simpleName;
    }

    // Helper method to extract browser name from system property or test context
    private String getBrowserName(ITestResult result) {
        String sysBrowser = System.getProperty("browser");
        if (sysBrowser != null && !sysBrowser.trim().isEmpty()) {
            return sysBrowser.toLowerCase().trim();
        }
        String browser = null;
        if (result.getTestContext() != null && result.getTestContext().getCurrentXmlTest() != null) {
            browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        }
        if (browser == null || browser.trim().isEmpty()) {
            return "chrome";
        }
        return browser.toLowerCase().trim();
    }

    // This method is called when a test method starts
    @Override
    public synchronized void onTestStart(ITestResult result) {
        String functionality = getFunctionalityName(result);
        String browser = getBrowserName(result);
        String key = functionality + "_" + browser;

        // Initialize a new ExtentReports instance if not already created for this functionality
        ExtentReports extent = reportsMap.get(key);
        if (extent == null) {
            String currentTimestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
            String reportPath = "reports/" + functionality + "_" + browser + "_" + currentTimestamp + ".html";
            extent = ExtentManager.createInstance(functionality, browser, reportPath);
            reportsMap.put(key, extent);
        }

        // Create test entry in the report
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.assignCategory(functionality);
        test.assignDevice(browser);
        testNode.set(test);
    }

    // This method is called when a test passes
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        if (testNode.get() != null) {
            testNode.get().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        }
        flushReport(result);
    }

    // This method is called when a test fails
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        if (testNode.get() != null) {
            testNode.get().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
            testNode.get().log(Status.FAIL, result.getThrowable());

            // Capture and attach screenshot on failure
            try {
                Object testObject = result.getInstance();
                if (testObject instanceof Base_page) {
                    WebDriver driver = ((Base_page) testObject).driver;
                    if (driver != null) {
                        String screenshotRelativePath = ExtentManager.captureScreenshot(driver, result.getMethod().getMethodName());
                        testNode.get().addScreenCaptureFromPath(screenshotRelativePath, "Failure Screenshot");
                    }
                }
            } catch (Exception e) {
                System.err.println("Could not capture screenshot on failure: " + e.getMessage());
            }
        }
        flushReport(result);
    }

    // This method is called when a test is skipped
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        if (testNode.get() != null) {
            testNode.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
            if (result.getThrowable() != null) {
                testNode.get().log(Status.SKIP, result.getThrowable());
            }
        }
        flushReport(result);
    }

    // Helper method to flush current report to disk
    private void flushReport(ITestResult result) {
        String functionality = getFunctionalityName(result);
        String browser = getBrowserName(result);
        String key = functionality + "_" + browser;
        ExtentReports extent = reportsMap.get(key);
        if (extent != null) {
            extent.flush();
        }
    }

    // This method is called when the test suite or test tag finishes
    @Override
    public synchronized void onFinish(ITestContext context) {
        for (ExtentReports extent : reportsMap.values()) {
            extent.flush();
        }
    }
}
