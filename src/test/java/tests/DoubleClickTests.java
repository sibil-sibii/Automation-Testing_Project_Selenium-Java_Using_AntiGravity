package tests;

import base.Base_page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DoubleClickPage;

// This test class verifies double-click text copying, state trigger modification, and single vs double click behaviors
public class DoubleClickTests extends Base_page {

    // TC_DBC_01: Enter text into Field1, perform double click on 'Copy Text' button, assert Field2 populated with identical value
    @Test(priority = 1, description = "TC_DBC_01: Verify text copying from Field1 to Field2 on double click")
    public void testTextFieldCopying() {
        DoubleClickPage dcp = new DoubleClickPage(driver);

        String sampleText = "Welcome to Automation Testing";
        dcp.enterTextInField1(sampleText);
        dcp.clearField2();

        // Perform double-click action
        dcp.doubleClickCopyTextButton();

        // Verify Field2 is populated with identical value
        Assert.assertEquals(dcp.getField2Value(), sampleText, "Field2 does not match Field1 value after double-click!");
    }

    // TC_DBC_02: Execute double click on specific element block and verify trigger of UI state modification
    @Test(priority = 2, description = "TC_DBC_02: Verify UI state change triggered by double-click event")
    public void testStateTriggerEvent() {
        DoubleClickPage dcp = new DoubleClickPage(driver);

        String triggerText = "State Modified Successfully";
        dcp.enterTextInField1(triggerText);
        dcp.clearField2();

        // Confirm Field2 is initially empty
        Assert.assertTrue(dcp.getField2Value().isEmpty(), "Field2 should initially be empty!");

        // Execute double click trigger
        dcp.doubleClickCopyTextButton();

        // Verify Field2 state has been modified
        Assert.assertFalse(dcp.getField2Value().isEmpty(), "Field2 state should be modified after double-click!");
        Assert.assertEquals(dcp.getField2Value(), triggerText, "Field2 did not receive the expected triggered text!");
    }

    // TC_DBC_03: Verify that single click does not trigger double-click event listener on target component
    @Test(priority = 3, description = "TC_DBC_03: Verify single click does not copy text into Field2")
    public void testSingleVsDoubleClick() {
        DoubleClickPage dcp = new DoubleClickPage(driver);

        dcp.enterTextInField1("Single Click Test");
        dcp.clearField2();

        // Perform only a single click
        dcp.singleClickCopyTextButton();

        // Assert that single click did not trigger copying into Field2
        Assert.assertTrue(dcp.getField2Value().isEmpty(), "Single click should NOT trigger text copy into Field2!");
    }
}

