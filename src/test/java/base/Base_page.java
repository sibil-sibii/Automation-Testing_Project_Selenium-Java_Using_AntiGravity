package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.ReportListener;

import java.io.File;
import java.time.Duration;

// This base class initializes WebDriver and handles browser setup and teardown for each test class
@Listeners(ReportListener.class)
public class Base_page {

    // Public driver accessible by test classes and report listener
    public WebDriver driver;

    // Public config reader accessible across the framework
    public ConfigReader config;

    // This setup method launches the requested browser, maximizes window, and navigates to the URL
    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("chrome") String browser) {
        // Load configuration properties
        config = new ConfigReader();

        // Check if browser is overridden via System property (-Dbrowser=firefox)
        String sysBrowser = System.getProperty("browser");
        if (sysBrowser != null && !sysBrowser.trim().isEmpty()) {
            browser = sysBrowser;
        } else if (browser == null || browser.trim().isEmpty()) {
            browser = config.getProperties("browser");
            if (browser == null || browser.trim().isEmpty()) {
                browser = "chrome";
            }
        }

        browser = browser.trim().toLowerCase();

        // Launch Chrome or Firefox using Selenium Manager
        if (browser.equals("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            // Allow popups in Firefox for multi-window testing
            firefoxOptions.addPreference("dom.disable_open_during_load", false);
            firefoxOptions.addPreference("privacy.popups.showBrowserMessage", false);

            // Detect Firefox binary in user AppData if not in standard Program Files
            String localAppData = System.getenv("LOCALAPPDATA");
            if (localAppData != null) {
                File userFirefox = new File(localAppData, "Mozilla Firefox/firefox.exe");
                if (userFirefox.exists()) {
                    firefoxOptions.setBinary(userFirefox.getAbsolutePath());
                }
            }

            driver = new FirefoxDriver(firefoxOptions);
        } else {
            // Default to Chrome
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
        }

        // Maximize browser window
        driver.manage().window().maximize();

        // Set implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Read URL from config and navigate
        String appUrl = config.getProperties("url");
        if (appUrl == null || appUrl.trim().isEmpty()) {
            appUrl = "https://testautomationpractice.blogspot.com";
        }
        driver.get(appUrl);
    }

    // This teardown method closes the browser after all tests in the class finish
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
