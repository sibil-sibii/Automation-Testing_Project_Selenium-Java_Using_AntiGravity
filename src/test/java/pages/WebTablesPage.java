package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// This page class handles interactions with static and dynamic pagination web tables
public class WebTablesPage {

    WebDriver driver;

    // Locators for Static Book Table
    By bookTable = By.name("BookTable");
    By bookTableRows = By.xpath("//table[@name='BookTable']//tr");
    By bookTableColumns = By.xpath("//table[@name='BookTable']//tr[1]/th");
    By bookTablePrices = By.xpath("//table[@name='BookTable']//tr[position()>1]/td[4]");

    // Locators for Dynamic Pagination Table
    By productTable = By.id("productTable");
    By paginationLinks = By.xpath("//ul[@id='pagination']//a");

    // Constructor initializes WebDriver
    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method returns the total number of rows in the static Book table (including header)
    public int getBookTableRowCount() {
        WebElement table = driver.findElement(bookTable);
        scrollToElement(table);
        return driver.findElements(bookTableRows).size();
    }

    // This method returns the total number of columns in the static Book table
    public int getBookTableColumnCount() {
        return driver.findElements(bookTableColumns).size();
    }

    // This method returns the list of WebElements representing book prices
    public List<WebElement> getBookPriceElements() {
        return driver.findElements(bookTablePrices);
    }

    // This method calculates the total price of all books using Java Streams as required by the test plan
    public int calculateTotalPriceUsingStreams() {
        return getBookPriceElements().stream()
                .map(WebElement::getText)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    // This method searches for a product across pagination pages and returns its price
    public String getProductPriceFromPaginationTable(String productName) {
        WebElement table = driver.findElement(productTable);
        scrollToElement(table);

        List<WebElement> pages = driver.findElements(paginationLinks);
        for (int i = 0; i < pages.size(); i++) {
            // Re-fetch pages list on each iteration to prevent stale elements
            List<WebElement> currentPages = driver.findElements(paginationLinks);
            currentPages.get(i).click();

            // Check if product is in the current page rows
            By rowLocator = By.xpath("//table[@id='productTable']//tr[td[normalize-space()='" + productName + "']]");
            List<WebElement> matchingRows = driver.findElements(rowLocator);
            if (!matchingRows.isEmpty()) {
                WebElement priceCell = matchingRows.get(0).findElement(By.xpath("./td[3]"));
                return priceCell.getText().trim();
            }
        }
        return null;
    }

    // This method locates a specific product row and clicks its checkbox
    public void selectProductCheckbox(String productName) {
        WebElement table = driver.findElement(productTable);
        scrollToElement(table);

        // Click first page if not already active
        List<WebElement> pages = driver.findElements(paginationLinks);
        if (!pages.isEmpty()) {
            pages.get(0).click();
        }

        // Locate checkbox within the row matching the product name
        By checkboxLocator = By.xpath("//table[@id='productTable']//td[normalize-space()='" + productName + "']/../td/input[@type='checkbox']");
        WebElement checkbox = driver.findElement(checkboxLocator);
        checkbox.click();
    }

    // This method verifies if the checkbox for a specific product is selected
    public boolean isProductCheckboxSelected(String productName) {
        By checkboxLocator = By.xpath("//table[@id='productTable']//td[normalize-space()='" + productName + "']/../td/input[@type='checkbox']");
        return driver.findElement(checkboxLocator).isSelected();
    }
}

