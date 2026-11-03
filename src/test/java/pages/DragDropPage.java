package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

// This page class handles HTML5/native drag and drop interactions and horizontal range slider adjustments
public class DragDropPage {

    WebDriver driver;

    // Locators for Draggable and Droppable elements
    By draggableElement = By.id("draggable");
    By droppableElement = By.id("droppable");

    // Locators for Slider and its Amount display
    By sliderContainer = By.id("slider-range");
    By leftSliderHandle = By.xpath("//div[@id='slider-range']//span[contains(@class,'ui-slider-handle')][1]");
    By rightSliderHandle = By.xpath("//div[@id='slider-range']//span[contains(@class,'ui-slider-handle')][2]");
    By amountField = By.id("amount");

    // Constructor initializes WebDriver
    public DragDropPage(WebDriver driver) {
        this.driver = driver;
    }

    // This method scrolls an element into view
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method drags the draggable item and drops it onto the droppable target
    public void performDragAndDrop() {
        WebElement source = driver.findElement(draggableElement);
        WebElement target = driver.findElement(droppableElement);
        scrollToElement(source);

        Actions act = new Actions(driver);
        act.dragAndDrop(source, target).perform();
    }

    // This method returns the text inside the droppable target element
    public String getDroppableText() {
        return driver.findElement(droppableElement).getText().trim();
    }

    // This method drags the slider handle horizontally by a specified x-offset
    public void dragSliderHandle(int xOffset) {
        WebElement handle = driver.findElement(leftSliderHandle);
        scrollToElement(handle);

        Actions act = new Actions(driver);
        act.dragAndDropBy(handle, xOffset, 0).perform();
    }

    // This method returns the current value from the slider amount field
    public String getSliderAmountValue() {
        WebElement amount = driver.findElement(amountField);
        scrollToElement(amount);
        return amount.getAttribute("value");
    }
}

