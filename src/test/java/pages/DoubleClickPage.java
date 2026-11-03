package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

// This page class handles double-click actions, text copying between fields, and click event verifications
public class DoubleClickPage {

    WebDriver driver;

    // Locators for Field1, Field2, and Copy Text button
    By field1 = By.id("field1");
    By field2 = By.id("field2");
    By copyTextButton = By.xpath("//button[contains(text(),'Copy Text')]");

    // Constructor initializes WebDriver
    public DoubleClickPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method enters text into Field1
    public void enterTextInField1(String text) {
        WebElement f1 = driver.findElement(field1);
        scrollToElement(f1);
        f1.clear();
        f1.sendKeys(text);
    }

    // This method gets the value from Field1
    public String getField1Value() {
        return driver.findElement(field1).getAttribute("value");
    }

    // This method gets the value from Field2
    public String getField2Value() {
        return driver.findElement(field2).getAttribute("value");
    }

    // This method clears Field2 using JavaScript
    public void clearField2() {
        ((JavascriptExecutor) driver).executeScript("document.getElementById('field2').value = '';");
    }

    // This method performs a double-click on the Copy Text button
    public void doubleClickCopyTextButton() {
        WebElement button = driver.findElement(copyTextButton);
        scrollToElement(button);
        Actions act = new Actions(driver);
        act.doubleClick(button).perform();
    }

    // This method performs a single click on the Copy Text button
    public void singleClickCopyTextButton() {
        WebElement button = driver.findElement(copyTextButton);
        scrollToElement(button);
        button.click();
    }
}

