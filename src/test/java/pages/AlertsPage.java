package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// This page class handles interactions with JavaScript Alerts (Simple, Confirmation, and Prompt)
public class AlertsPage {

    WebDriver driver;

    // Locators for Alert buttons and output demo text
    By simpleAlertButton = By.id("alertBtn");
    By confirmationAlertButton = By.id("confirmBtn");
    By promptAlertButton = By.id("promptBtn");
    By demoText = By.id("demo");

    // Constructor initializes WebDriver
    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method waits for an alert to be present and switches to it
    public Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // This method clicks the Simple Alert button
    public void clickSimpleAlert() {
        WebElement element = driver.findElement(simpleAlertButton);
        scrollToElement(element);
        element.click();
    }

    // This method clicks the Confirmation Alert button
    public void clickConfirmationAlert() {
        WebElement element = driver.findElement(confirmationAlertButton);
        scrollToElement(element);
        element.click();
    }

    // This method clicks the Prompt Alert button
    public void clickPromptAlert() {
        WebElement element = driver.findElement(promptAlertButton);
        scrollToElement(element);
        element.click();
    }

    // This method gets the feedback message displayed in the demo paragraph
    public String getDemoText() {
        return driver.findElement(demoText).getText().trim();
    }
}

