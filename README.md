# Selenium POM Automation Framework - TestAutomationPractice

A complete, beginner-friendly Selenium WebDriver test automation framework in **Java 17** using **Maven**, **Selenium 4.x**, **TestNG**, and **ExtentReports 5.x**, built using the **Page Object Model (POM)** architectural design pattern.

---

## 1. Project Overview & Architecture

- **Target Website**: [https://testautomationpractice.blogspot.com](https://testautomationpractice.blogspot.com)
- **Language**: Java 17+
- **Build Tool**: Maven 3.9+
- **Test Runner**: TestNG 7.10.2
- **Automation Engine**: Selenium WebDriver 4.25.0 (managed automatically via Selenium Manager, no WebDriverManager required)
- **Reporting**: ExtentReports 5.1.2 (generates individual HTML reports per functionality per browser)
- **Cross-Browser Support**: Google Chrome & Mozilla Firefox

All framework code is structured cleanly under `src/test/java` (no `src/main`), adhering strictly to standard test automation principles.

---

## 2. Directory Structure

```text
AntiGravity Automation Testing Project
├── .mvn/
├── reports/                                    <- Auto-generated HTML reports & screenshots
│   ├── GUIElements_chrome_<timestamp>.html
│   ├── GUIElements_firefox_<timestamp>.html
│   ├── WebTables_chrome_<timestamp>.html
│   └── screenshots/
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── Base_page.java              <- Base setup, teardown & browser launch
│       │   ├── pages/                          <- Page Object classes (plain By locators & methods)
│       │   │   ├── GUIElementsPage.java
│       │   │   ├── WebTablesPage.java
│       │   │   ├── AlertsPage.java
│       │   │   ├── MouseHoverPage.java
│       │   │   ├── DoubleClickPage.java
│       │   │   ├── DragDropPage.java
│       │   │   ├── NewTabPage.java
│       │   │   ├── FileUploadPage.java
│       │   │   └── LinksLabelsPage.java
│       │   ├── tests/                          <- Test classes with TestNG @Test annotations
│       │   │   ├── GUIElementsTests.java
│       │   │   ├── WebTablesTests.java
│       │   │   ├── AlertsTests.java
│       │   │   ├── MouseHoverTests.java
│       │   │   ├── DoubleClickTests.java
│       │   │   ├── DragDropTests.java
│       │   │   ├── NewTabTests.java
│       │   │   ├── FileUploadTests.java
│       │   │   └── LinksLabelsTests.java
│       │   └── utils/                          <- Utilities for configuration & reporting
│       │       ├── ConfigReader.java
│       │       ├── ExtentManager.java
│       │       └── ReportListener.java
│       └── resources/
│           ├── config.properties               <- URL, browser, and test input configuration
│           └── testdata/
│               ├── sample1.txt                 <- Single & multiple upload test file
│               └── sample2.txt                 <- Multiple upload test file
├── mvn.cmd                                     <- Standalone Maven wrapper for Windows
├── pom.xml                                     <- Dependencies & plugins build configuration
├── testng.xml                                  <- Test suite running Chrome & Firefox test blocks
└── README.md                                   <- Framework documentation & instructions
```

---

## 3. The 9 Page Objects and 27 Test Scenarios

| # | Page Object Class | Test Class | Test Case ID | Scenario Description & Verification |
|---|-------------------|------------|--------------|--------------------------------------|
| 1 | `GUIElementsPage` | `GUIElementsTests` | `TC_GUI_01` | Verify text entry into Name, Email, Phone, Address via `getAttribute("value")` |
| | | | `TC_GUI_02` | Select Gender radio and multiple Hobby checkboxes; assert `isSelected()` before/after |
| | | | `TC_GUI_03` | Single-select Country & multi-select Colors by visible text, index, and value |
| 2 | `WebTablesPage` | `WebTablesTests` | `TC_TBL_01` | Extract row/column counts; calculate total price sum ($7,100) using Java Streams |
| | | | `TC_TBL_02` | Search product across pagination pages and verify corresponding price |
| | | | `TC_TBL_03` | Locate specific product row dynamically and select its row checkbox |
| 3 | `AlertsPage` | `AlertsTests` | `TC_ALT_01` | Trigger Simple Alert, retrieve message, assert, and accept |
| | | | `TC_ALT_02` | Trigger Confirmation Alert, verify both 'OK' and 'Cancel' DOM feedback |
| | | | `TC_ALT_03` | Trigger Prompt Alert, send custom input string, accept, and assert rendered text |
| 4 | `MouseHoverPage` | `MouseHoverTests` | `TC_HOV_01` | Hover over 'Point Me', wait for dropdown menu visibility, click child link |
| | | | `TC_HOV_02` | Hover over popover container and verify visible text content |
| | | | `TC_HOV_03` | Verify CSS attribute (`background-color`) before and after hover action |
| 5 | `DoubleClickPage` | `DoubleClickTests` | `TC_DBC_01` | Double-click 'Copy Text' button, assert Field2 populated identical to Field1 |
| | | | `TC_DBC_02` | Execute double-click and verify trigger of UI state modification |
| | | | `TC_DBC_03` | Verify single click does NOT copy text into Field2 (negative assertion) |
| 6 | `DragDropPage` | `DragDropTests` | `TC_DRG_01` | Drag source 'Draggable' onto target 'Droppable'; assert text changes to 'Dropped!' |
| | | | `TC_DRG_02` | Drag range slider handle horizontally by relative x-offset; verify updated amount |
| 7 | `NewTabPage` | `NewTabTests` | `TC_TAB_01` | Click New Tab, switch window handle, verify URL/title, close tab, restore focus |
| | | | `TC_TAB_02` | Click Popup Windows, switch by title/keyword, interact, close, restore focus |
| 8 | `FileUploadPage` | `FileUploadTests` | `TC_UPL_01` | Single file upload using dynamic relative path; verify status message on UI |
| | | | `TC_UPL_02` | Multiple files simultaneous upload using newline delimiter; verify both files listed |
| 9 | `LinksLabelsPage` | `LinksLabelsTests` | `TC_LNK_01` | Audit links sending HTTP HEAD requests via `HttpURLConnection` (<400 for valid, >=400 for broken) |
| | | | `TC_LNK_02` | Interact with elements using dynamic CSS/XPath attribute matching (`^=`, `contains()`) |

---

## 4. Prerequisites

1. **Java Development Kit (JDK 17+)**: Ensure `java -version` returns JDK 17 or higher.
2. **Apache Maven (3.9+)**: Ensure `mvn -version` or `./mvn.cmd -version` executes properly.
3. **Google Chrome**: Installed on the system.
4. **Mozilla Firefox**: Installed on the system.

---

## 5. How to Open the Project in IDE

### In IntelliJ IDEA:
1. Open IntelliJ IDEA -> Click **File** -> **Open...**
2. Select the project folder: `c:\Users\sibil\OneDrive\Documents\AntiGravity Automation Testing Project`.
3. Choose **Open as Maven Project**.
4. Allow Maven to import dependencies and index the project.
5. In **Project Structure** (`File -> Project Structure`), ensure Project SDK is set to **Java 17**.

### In Eclipse IDE:
1. Open Eclipse IDE -> Click **File** -> **Import...**
2. Select **Maven** -> **Existing Maven Projects** -> Click **Next**.
3. Browse to the project folder and click **Finish**.
4. Right-click the project -> **Maven** -> **Update Project...** -> Click **OK**.

---

## 6. How to Run the Tests

### Option A: Run Full Cross-Browser Suite (Chrome + Firefox)
Run all 27 tests in Chrome and all 27 tests in Firefox via `testng.xml`:
```bash
mvn clean test
```
*(On Windows without global mvn in PATH, you can use `.\mvn.cmd clean test`)*

### Option B: Run a Single Functionality (Ready-to-Use Example)
To run only one functionality (for example, GUI Elements):
```bash
mvn test -Dtest=GUIElementsTests
```
To run only one functionality on Firefox:
```bash
mvn test -Dtest=GUIElementsTests -Dbrowser=firefox
```
To run multiple specific test classes:
```bash
mvn test "-Dtest=GUIElementsTests,AlertsTests,DragDropTests"
```

### Option C: Run a Single Functionality via TestNG XML
Create or use a minimal testng XML suite (e.g. `testng_single.xml`):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Single Functionality Suite">
    <listeners>
        <listener class-name="utils.ReportListener" />
    </listeners>
    <test name="GUI Elements Chrome Test">
        <parameter name="browser" value="chrome" />
        <classes>
            <class name="tests.GUIElementsTests" />
        </classes>
    </test>
</suite>
```
Execute it with:
```bash
mvn test -Dsurefire.suiteXmlFiles=testng_single.xml
```

---

## 7. Reports & Screenshots

- **Report Location**: `reports/` folder at the project root.
- **Report Naming Pattern**: `<Functionality>_<Browser>_<timestamp>.html`
  - Examples:
    - `reports/GUIElements_chrome_20261028_1625.html`
    - `reports/WebTables_chrome_20261028_1633.html`
    - `reports/Alerts_firefox_20261028_1645.html`
- **Failure Screenshots**: If any test fails, an automatic screenshot is captured and saved in `reports/screenshots/` and embedded directly into the corresponding Extent HTML report.

---

## 8. How to Add a New Page and Test Class

To add a new functionality (e.g., `DatePicker`):

1. **Create Page Object** in `src/test/java/pages/DatePickerPage.java`:
   ```java
   package pages;
   import org.openqa.selenium.By;
   import org.openqa.selenium.WebDriver;

   public class DatePickerPage {
       WebDriver driver;
       By dateInput = By.id("datepicker");

       // Constructor
       public DatePickerPage(WebDriver driver) {
           this.driver = driver;
       }

       // Action method
       public void enterDate(String date) {
           driver.findElement(dateInput).sendKeys(date);
       }
   }
   ```

2. **Create Test Class** in `src/test/java/tests/DatePickerTests.java`:
   ```java
   package tests;
   import base.Base_page;
   import org.testng.Assert;
   import org.testng.annotations.Test;
   import pages.DatePickerPage;

   public class DatePickerTests extends Base_page {
       @Test(priority = 1, description = "TC_DP_01: Verify Date selection")
       public void testDateSelection() {
           DatePickerPage dpp = new DatePickerPage(driver);
           dpp.enterDate("10/28/2026");
           // Assertions...
       }
   }
   ```

3. **Register in `testng.xml`**:
   Add `<class name="tests.DatePickerTests" />` inside the desired `<classes>` block.

