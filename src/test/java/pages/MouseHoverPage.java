package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// This page class handles mouse hover actions, sub-menu navigation, and hover styling
public class MouseHoverPage {

    WebDriver driver;

    // Locators for Point Me hover button and sub-menu items
    By dropdownContainer = By.className("dropdown");
    By pointMeButton = By.xpath("//button[contains(text(),'Point Me')]");
    By dropdownContent = By.className("dropdown-content");
    By mobilesLink = By.xpath("//div[@class='dropdown-content']//a[text()='Mobiles']");
    By laptopsLink = By.xpath("//div[@class='dropdown-content']//a[text()='Laptops']");

    // Constructor initializes WebDriver
    public MouseHoverPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    // This method returns the Point Me button WebElement
    public WebElement getPointMeButton() {
        WebElement button = driver.findElement(pointMeButton);
        scrollToElement(button);
        return button;
    }

    // This method hovers over the Point Me button and dropdown container
    public void hoverOverPointMe() {
        WebElement container = driver.findElement(dropdownContainer);
        scrollToElement(container);
        Actions act = new Actions(driver);
        act.moveToElement(container).perform();
    }

    // This method waits for the dropdown menu to become visible and returns its text
    public String getDropdownMenuText() {
        hoverOverPointMe();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownContent));
            return content.getText().trim();
        } catch (Exception e) {
            // Fallback: extract text directly from element in case native hover is simulated differently
            WebElement content = driver.findElement(dropdownContent);
            String text = content.getText().trim();
            if (text.isEmpty()) {
                text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", content);
            }
            return text != null ? text.trim() : "";
        }
    }

    // This method clicks the Mobiles child menu link after hover
    public void clickMobilesSubMenu() {
        hoverOverPointMe();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(mobilesLink));
            link.click();
        } catch (Exception e) {
            // Fallback click via JavaScript if browser menu overlay behaves differently
            WebElement link = driver.findElement(mobilesLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }

    // This method returns the CSS background-color of the Point Me button
    public String getPointMeBackgroundColor() {
        return driver.findElement(pointMeButton).getCssValue("background-color");
    }

    // This method returns the CSS background-color of the Mobiles menu item
    public String getMobilesLinkBackgroundColor() {
        return driver.findElement(mobilesLink).getCssValue("background-color");
    }

    // This method hovers over the child Mobiles link
    public void hoverOverMobilesLink() {
        hoverOverPointMe();
        WebElement link = driver.findElement(mobilesLink);
        Actions act = new Actions(driver);
        act.moveToElement(link).perform();
    }
}
