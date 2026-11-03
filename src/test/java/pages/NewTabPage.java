package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

// This page class handles new browser tabs, pop-up windows, and switching window handles
public class NewTabPage {

    WebDriver driver;

    // Locators for New Tab button and Popup Windows button
    By newTabButton = By.xpath("//button[contains(text(),'New Tab')]");
    By popupWindowsButton = By.id("PopUp");

    // Constructor initializes WebDriver
    public NewTabPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method clicks the New Tab button
    public void clickNewTabButton() {
        WebElement button = driver.findElement(newTabButton);
        scrollToElement(button);
        button.click();
    }

    // This method clicks the Popup Windows button
    public void clickPopupWindowsButton() {
        WebElement button = driver.findElement(popupWindowsButton);
        scrollToElement(button);
        button.click();
    }

    // This method returns the current window handle
    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    // This method returns all open window handles
    public Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    // This method switches focus to a specific window handle
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    // This method switches to a window matching a specific title or url keyword
    public boolean switchToWindowByTitleOrUrl(String keyword) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            String title = driver.getTitle();
            String url = driver.getCurrentUrl();
            if ((title != null && title.toLowerCase().contains(keyword.toLowerCase())) ||
                (url != null && url.toLowerCase().contains(keyword.toLowerCase()))) {
                return true;
            }
        }
        return false;
    }

    // This method closes the currently active tab or popup window
    public void closeCurrentWindow() {
        driver.close();
    }
}

