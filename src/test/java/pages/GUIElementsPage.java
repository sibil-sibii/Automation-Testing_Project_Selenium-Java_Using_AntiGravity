package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

// This page class handles all interactions with GUI form elements (inputs, radio buttons, checkboxes, dropdowns)
public class GUIElementsPage {

    WebDriver driver;

    // Locators for Text Inputs
    By nameField = By.id("name");
    By emailField = By.id("email");
    By phoneField = By.id("phone");
    By addressField = By.id("textarea");

    // Locators for Radio Buttons and Checkboxes
    By maleRadio = By.id("male");
    By femaleRadio = By.id("female");
    By sundayCheckbox = By.id("sunday");
    By mondayCheckbox = By.id("monday");
    By tuesdayCheckbox = By.id("tuesday");

    // Locators for Dropdowns
    By countryDropdown = By.id("country");
    By colorsDropdown = By.id("colors");

    // Constructor initializes WebDriver
    public GUIElementsPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method enters a name into the Name field
    public void enterName(String name) {
        WebElement element = driver.findElement(nameField);
        element.clear();
        element.sendKeys(name);
    }

    // This method gets the value entered in the Name field
    public String getNameValue() {
        return driver.findElement(nameField).getAttribute("value");
    }

    // This method enters an email into the Email field
    public void enterEmail(String email) {
        WebElement element = driver.findElement(emailField);
        element.clear();
        element.sendKeys(email);
    }

    // This method gets the value entered in the Email field
    public String getEmailValue() {
        return driver.findElement(emailField).getAttribute("value");
    }

    // This method enters a phone number into the Phone field
    public void enterPhone(String phone) {
        WebElement element = driver.findElement(phoneField);
        element.clear();
        element.sendKeys(phone);
    }

    // This method gets the value entered in the Phone field
    public String getPhoneValue() {
        return driver.findElement(phoneField).getAttribute("value");
    }

    // This method enters an address into the Address textarea
    public void enterAddress(String address) {
        WebElement element = driver.findElement(addressField);
        element.clear();
        element.sendKeys(address);
    }

    // This method gets the value entered in the Address field
    public String getAddressValue() {
        return driver.findElement(addressField).getAttribute("value");
    }

    // This method checks if the Male radio button is selected
    public boolean isMaleSelected() {
        return driver.findElement(maleRadio).isSelected();
    }

    // This method selects the Male radio button
    public void selectMale() {
        WebElement element = driver.findElement(maleRadio);
        scrollToElement(element);
        element.click();
    }

    // This method checks if the Female radio button is selected
    public boolean isFemaleSelected() {
        return driver.findElement(femaleRadio).isSelected();
    }

    // This method selects the Female radio button
    public void selectFemale() {
        WebElement element = driver.findElement(femaleRadio);
        scrollToElement(element);
        element.click();
    }

    // This method checks if Sunday checkbox is selected
    public boolean isSundaySelected() {
        return driver.findElement(sundayCheckbox).isSelected();
    }

    // This method clicks the Sunday checkbox
    public void clickSunday() {
        WebElement element = driver.findElement(sundayCheckbox);
        scrollToElement(element);
        element.click();
    }

    // This method checks if Monday checkbox is selected
    public boolean isMondaySelected() {
        return driver.findElement(mondayCheckbox).isSelected();
    }

    // This method clicks the Monday checkbox
    public void clickMonday() {
        WebElement element = driver.findElement(mondayCheckbox);
        scrollToElement(element);
        element.click();
    }

    // This method selects a country by its visible text
    public void selectCountryByVisibleText(String text) {
        WebElement dropdown = driver.findElement(countryDropdown);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    // This method selects a country by its index
    public void selectCountryByIndex(int index) {
        WebElement dropdown = driver.findElement(countryDropdown);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // This method selects a country by its HTML value attribute
    public void selectCountryByValue(String value) {
        WebElement dropdown = driver.findElement(countryDropdown);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // This method returns the currently selected country text
    public String getSelectedCountryText() {
        Select select = new Select(driver.findElement(countryDropdown));
        return select.getFirstSelectedOption().getText().trim();
    }

    // This method selects a color by visible text
    public void selectColorByVisibleText(String text) {
        WebElement dropdown = driver.findElement(colorsDropdown);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    // This method selects a color by index
    public void selectColorByIndex(int index) {
        WebElement dropdown = driver.findElement(colorsDropdown);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // This method selects a color by value
    public void selectColorByValue(String value) {
        WebElement dropdown = driver.findElement(colorsDropdown);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // This method returns all selected options from the Colors multi-select dropdown
    public List<String> getSelectedColors() {
        Select select = new Select(driver.findElement(colorsDropdown));
        List<WebElement> options = select.getAllSelectedOptions();
        List<String> texts = new ArrayList<>();
        for (WebElement option : options) {
            texts.add(option.getText().trim());
        }
        return texts;
    }
}

