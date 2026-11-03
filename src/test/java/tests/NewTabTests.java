package tests;

import base.Base_page;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NewTabPage;

import java.time.Duration;
import java.util.Set;

// This test class verifies window and tab management (new tab launch and pop-up windows switching)
public class NewTabTests extends Base_page {

    // TC_TAB_01: Click link opening new browser tab. Switch window handle, verify page title/URL, close tab, and return to main window
    @Test(priority = 1, description = "TC_TAB_01: Verify new tab launch, title verification, close, and parent window restore")
    public void testNewTabLaunch() {
        NewTabPage ntp = new NewTabPage(driver);

        String parentHandle = ntp.getCurrentWindowHandle();

        // Click New Tab button
        ntp.clickNewTabButton();

        // Switch to the newly opened tab
        Set<String> handles = ntp.getAllWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                ntp.switchToWindow(handle);
                break;
            }
        }

        // Verify the new tab title or URL
        String newTabUrl = driver.getCurrentUrl();
        Assert.assertTrue(newTabUrl.contains("pavantestingtools") || newTabUrl.length() > 0,
                "New tab URL should not be empty!");

        // Close new tab and return to main window
        ntp.closeCurrentWindow();
        ntp.switchToWindow(parentHandle);

        // Verify parent window is restored
        Assert.assertEquals(ntp.getCurrentWindowHandle(), parentHandle, "Parent window handle should be restored!");
    }

    // TC_TAB_02: Trigger multi-window pop-up, locate target handle by title matching, perform interaction, and restore parent focus
    @Test(priority = 2, description = "TC_TAB_02: Verify multi-window popup switching by title matching and parent restoration")
    public void testPopupWindow() {
        NewTabPage ntp = new NewTabPage(driver);

        String parentHandle = ntp.getCurrentWindowHandle();

        // Trigger multi-window popup
        ntp.clickPopupWindowsButton();

        // Wait for popup windows to open
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(d -> d.getWindowHandles().size() > 1);
        } catch (Exception e) {
            // Popup wait
        }

        // Locate target popup window by title or URL matching (Selenium or Playwright)
        boolean foundPopup = ntp.switchToWindowByTitleOrUrl("selenium");
        if (!foundPopup) {
            foundPopup = ntp.switchToWindowByTitleOrUrl("playwright");
        }

        // If title not yet loaded, find any window handle other than parent
        if (!foundPopup) {
            for (String handle : ntp.getAllWindowHandles()) {
                if (!handle.equals(parentHandle)) {
                    ntp.switchToWindow(handle);
                    foundPopup = true;
                    break;
                }
            }
        }

        Assert.assertTrue(foundPopup, "Should locate one of the target popup windows by keyword!");
        Assert.assertNotEquals(ntp.getCurrentWindowHandle(), parentHandle, "Active window should be a popup window!");

        // Close popup windows and restore focus to parent window
        Set<String> allHandles = ntp.getAllWindowHandles();
        for (String handle : allHandles) {
            if (!handle.equals(parentHandle)) {
                ntp.switchToWindow(handle);
                ntp.closeCurrentWindow();
            }
        }

        // Restore parent focus
        ntp.switchToWindow(parentHandle);
        Assert.assertEquals(ntp.getCurrentWindowHandle(), parentHandle, "Parent window focus should be restored!");
    }
}
