package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// This page class handles link validation (HTTP HEAD status checks) and dynamic identifier elements
public class LinksLabelsPage {

    WebDriver driver;

    // Locators for Links and Labels
    By laptopLinks = By.xpath("//div[@id='laptops']//a");
    By brokenLinks = By.xpath("//div[@id='broken-links']//a");
    By dynamicToggleButton = By.xpath("//button[contains(@class,'start') or contains(@class,'stop')]");

    // Dynamic partial locators using CSS and XPath strategies
    By appleLinkByPrefix = By.cssSelector("a[id^='app']");
    By realMeLabelByContains = By.xpath("//label[contains(@id,'real')]");
    By motoLabelByContains = By.xpath("//label[contains(@id,'mot')]");

    // Constructor initializes WebDriver
    public LinksLabelsPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method returns all valid laptop link URLs
    public List<String> getLaptopLinkUrls() {
        WebElement section = driver.findElement(By.id("laptops"));
        scrollToElement(section);

        List<WebElement> links = driver.findElements(laptopLinks);
        List<String> urls = new ArrayList<>();
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                urls.add(href);
            }
        }
        return urls;
    }

    // This method returns all broken link URLs from the broken links section
    public List<String> getBrokenLinkUrls() {
        WebElement section = driver.findElement(By.id("broken-links"));
        scrollToElement(section);

        List<WebElement> links = driver.findElements(brokenLinks);
        List<String> urls = new ArrayList<>();
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                urls.add(href);
            }
        }
        return urls;
    }

    // This method sends an HTTP HEAD request to verify the HTTP response code of a given URL
    public int getHttpResponseCode(String urlString) {
        try {
            URL url = URI.create(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            return connection.getResponseCode();
        } catch (Exception e) {
            // In case of connection failure or invalid link, return an error code (e.g., 500)
            return 500;
        }
    }

    // This method clicks the Dynamic Button which toggles between START and STOP
    public void clickDynamicButton() {
        WebElement button = driver.findElement(dynamicToggleButton);
        scrollToElement(button);
        button.click();
    }

    // This method returns the current text of the Dynamic Button
    public String getDynamicButtonText() {
        return driver.findElement(dynamicToggleButton).getText().trim();
    }

    // This method interacts with the Apple link located by CSS prefix selector (^=)
    public String getAppleLinkTextByCssPrefix() {
        return driver.findElement(appleLinkByPrefix).getText().trim();
    }

    // This method interacts with the RealMe label located by XPath partial contains() selector
    public String getRealMeLabelTextByXPath() {
        return driver.findElement(realMeLabelByContains).getText().trim();
    }

    // This method interacts with the Moto label located by XPath partial contains() selector
    public String getMotoLabelTextByXPath() {
        return driver.findElement(motoLabelByContains).getText().trim();
    }
}
