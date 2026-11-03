package tests;

import base.Base_page;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

// This test class verifies Simple Alerts, Confirmation Alerts (OK/Cancel), and Prompt Dialogs
public class AlertsTests extends Base_page {

    // TC_ALT_01: Trigger Simple Alert, switch context to alert, retrieve message text, verify message, and accept alert
    @Test(priority = 1, description = "TC_ALT_01: Verify Simple Alert text and acceptance")
    public void testSimpleAlert() {
        AlertsPage ap = new AlertsPage(driver);

        // Click Simple Alert button
        ap.clickSimpleAlert();

        // Switch context to alert, verify message text, and accept
        Alert alert = ap.waitForAlert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "I am an alert box!", "Simple alert text does not match expected message!");
        alert.accept();
    }

    // TC_ALT_02: Trigger Confirmation Alert, test both 'OK' and 'Cancel' paths, verifying resulting DOM text feedback
    @Test(priority = 2, description = "TC_ALT_02: Verify Confirmation Alert OK and Cancel paths")
    public void testConfirmationAlert() {
        AlertsPage ap = new AlertsPage(driver);

        // Path 1: Click confirm and accept (OK)
        ap.clickConfirmationAlert();
        Alert alertOk = ap.waitForAlert();
        Assert.assertEquals(alertOk.getText(), "Press a button!", "Confirm alert text does not match!");
        alertOk.accept();
        Assert.assertEquals(ap.getDemoText(), "You pressed OK!", "Feedback text should show 'You pressed OK!'");

        // Path 2: Click confirm and dismiss (Cancel)
        ap.clickConfirmationAlert();
        Alert alertCancel = ap.waitForAlert();
        alertCancel.dismiss();
        Assert.assertEquals(ap.getDemoText(), "You pressed Cancel!", "Feedback text should show 'You pressed Cancel!'");
    }

    // TC_ALT_03: Trigger Prompt Alert, send custom string input into prompt, accept alert, and assert text rendered on UI
    @Test(priority = 3, description = "TC_ALT_03: Verify Prompt Alert input and DOM feedback rendering")
    public void testPromptAlert() {
        AlertsPage ap = new AlertsPage(driver);

        String customInput = "Selenium Test";

        // Trigger prompt alert
        ap.clickPromptAlert();

        // Switch to alert, send custom input, and accept
        Alert promptAlert = ap.waitForAlert();
        promptAlert.sendKeys(customInput);
        promptAlert.accept();

        // Assert rendered text on UI
        String expectedFeedback = "Hello " + customInput + "! How are you today?";
        Assert.assertEquals(ap.getDemoText(), expectedFeedback, "Prompt feedback text does not match expected message!");
    }
}
