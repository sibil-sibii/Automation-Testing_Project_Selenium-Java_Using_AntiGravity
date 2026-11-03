package tests;

import base.Base_page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GUIElementsPage;

import java.util.List;

// This test class verifies text inputs, radio buttons, checkboxes, and dropdown selections
public class GUIElementsTests extends Base_page {

    // TC_GUI_01: Verify text entry into Name, Email, Phone, and Address fields
    @Test(priority = 1, description = "TC_GUI_01: Verify text entry into Name, Email, Phone, and Address fields")
    public void testInputAndTextarea() {
        GUIElementsPage gep = new GUIElementsPage(driver);

        String testName = config.getProperties("name");
        String testEmail = config.getProperties("email");
        String testPhone = config.getProperties("phone");
        String testAddress = config.getProperties("address");

        // Enter values into input fields
        gep.enterName(testName);
        gep.enterEmail(testEmail);
        gep.enterPhone(testPhone);
        gep.enterAddress(testAddress);

        // Validate typed values using getAttribute("value")
        Assert.assertEquals(gep.getNameValue(), testName, "Name value does not match expected input!");
        Assert.assertEquals(gep.getEmailValue(), testEmail, "Email value does not match expected input!");
        Assert.assertEquals(gep.getPhoneValue(), testPhone, "Phone value does not match expected input!");
        Assert.assertEquals(gep.getAddressValue(), testAddress, "Address value does not match expected input!");
    }

    // TC_GUI_02: Select Gender radio button and multiple Hobby checkboxes; assert state before and after action
    @Test(priority = 2, description = "TC_GUI_02: Verify Gender radio and Hobby checkboxes selection state")
    public void testRadioAndCheckboxes() {
        GUIElementsPage gep = new GUIElementsPage(driver);

        // Verify initial state of radio button
        Assert.assertFalse(gep.isMaleSelected(), "Male radio should not be selected initially!");
        gep.selectMale();
        Assert.assertTrue(gep.isMaleSelected(), "Male radio should be selected after click!");

        // Verify initial state of Sunday and Monday checkboxes
        Assert.assertFalse(gep.isSundaySelected(), "Sunday checkbox should not be selected initially!");
        Assert.assertFalse(gep.isMondaySelected(), "Monday checkbox should not be selected initially!");

        // Select checkboxes and verify they are selected
        gep.clickSunday();
        gep.clickMonday();
        Assert.assertTrue(gep.isSundaySelected(), "Sunday checkbox should be selected after click!");
        Assert.assertTrue(gep.isMondaySelected(), "Monday checkbox should be selected after click!");
    }

    // TC_GUI_03: Select options in Country (single-select) and Colors (multi-select) dropdowns
    @Test(priority = 3, description = "TC_GUI_03: Verify Country and Colors dropdown selections")
    public void testDropdownSelection() {
        GUIElementsPage gep = new GUIElementsPage(driver);

        // Test Country single-select dropdown by visible text
        gep.selectCountryByVisibleText("Canada");
        Assert.assertEquals(gep.getSelectedCountryText(), "Canada", "Selected country should be Canada!");

        // Test Country by index (e.g., index 9 is India)
        gep.selectCountryByIndex(9);
        Assert.assertEquals(gep.getSelectedCountryText(), "India", "Selected country should be India!");

        // Test Country by value (e.g., value 'usa')
        gep.selectCountryByValue("usa");
        Assert.assertEquals(gep.getSelectedCountryText(), "United States", "Selected country should be United States!");

        // Test Colors multi-select dropdown by visible text, index, and value
        gep.selectColorByVisibleText("Red");
        gep.selectColorByValue("blue");
        gep.selectColorByIndex(2); // Green

        List<String> selectedColors = gep.getSelectedColors();
        Assert.assertTrue(selectedColors.contains("Red"), "Colors should include Red!");
        Assert.assertTrue(selectedColors.contains("Blue"), "Colors should include Blue!");
        Assert.assertTrue(selectedColors.contains("Green"), "Colors should include Green!");
    }
}

