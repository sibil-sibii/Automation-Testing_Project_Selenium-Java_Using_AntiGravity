package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// This page class handles single and multiple file upload inputs, forms, and status verifications
public class FileUploadPage {

    WebDriver driver;

    // Locators for Single File Upload
    By singleFileInput = By.id("singleFileInput");
    By singleFileSubmitButton = By.xpath("//form[@id='singleFileForm']//button[@type='submit']");
    By singleFileStatus = By.id("singleFileStatus");

    // Locators for Multiple Files Upload
    By multipleFilesInput = By.id("multipleFilesInput");
    By multipleFilesSubmitButton = By.xpath("//form[@id='multipleFilesForm']//button[@type='submit']");
    By multipleFilesStatus = By.id("multipleFilesStatus");

    // Constructor initializes WebDriver
    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method uploads a single file by sending its absolute path to the file input
    public void uploadSingleFile(String filePath) {
        WebElement input = driver.findElement(singleFileInput);
        scrollToElement(input);
        input.sendKeys(filePath);

        WebElement submit = driver.findElement(singleFileSubmitButton);
        submit.click();
    }

    // This method returns the status message after single file upload
    public String getSingleFileStatusMessage() {
        return driver.findElement(singleFileStatus).getText().trim();
    }

    // This method uploads multiple files simultaneously by sending newline-separated file paths
    public void uploadMultipleFiles(String combinedFilePaths) {
        WebElement input = driver.findElement(multipleFilesInput);
        scrollToElement(input);
        input.sendKeys(combinedFilePaths);

        WebElement submit = driver.findElement(multipleFilesSubmitButton);
        submit.click();
    }

    // This method returns the status message after multiple file upload
    public String getMultipleFilesStatusMessage() {
        return driver.findElement(multipleFilesStatus).getText().trim();
    }
}

