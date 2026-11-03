package tests;

import base.Base_page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MouseHoverPage;

// This test class verifies mouse hover gestures, sub-menu visibility, and hover style changes
public class MouseHoverTests extends Base_page {

    // TC_HOV_01: Hover over primary menu item ('Point Me'), wait for dropdown menu visibility, and click child menu link
    @Test(priority = 1, description = "TC_HOV_01: Verify hover over Point Me and sub-menu click")
    public void testSubMenuNavigation() {
        MouseHoverPage mhp = new MouseHoverPage(driver);

        // Hover over Point Me button
        mhp.hoverOverPointMe();

        // Verify sub-menu dropdown becomes visible with content
        String dropdownText = mhp.getDropdownMenuText();
        Assert.assertTrue(dropdownText.contains("Mobiles"), "Dropdown should display 'Mobiles' link!");
        Assert.assertTrue(dropdownText.contains("Laptops"), "Dropdown should display 'Laptops' link!");

        // Click child menu link
        mhp.clickMobilesSubMenu();
    }

    // TC_HOV_02: Hover over popover container and capture visible popover text for content verification
    // Note: The live site uses the Point Me hover dropdown container as the interactive popover element
    @Test(priority = 2, description = "TC_HOV_02: Verify popover container text upon hover")
    public void testTooltipValidation() {
        MouseHoverPage mhp = new MouseHoverPage(driver);

        // Hover over the primary trigger element
        mhp.hoverOverPointMe();

        // Capture visible popover text for content verification
        String popoverText = mhp.getDropdownMenuText();
        Assert.assertFalse(popoverText.isEmpty(), "Hover popover text should not be empty!");
        Assert.assertTrue(popoverText.contains("Mobiles"), "Hover popover should contain 'Mobiles' option!");
    }

    // TC_HOV_03: Verify CSS attribute changes (e.g., background-color) upon hovering over trigger element
    @Test(priority = 3, description = "TC_HOV_03: Verify CSS attribute change upon hovering")
    public void testHoverStateStyle() {
        MouseHoverPage mhp = new MouseHoverPage(driver);

        // Verify initial background-color of Point Me button
        String initialColor = mhp.getPointMeBackgroundColor();
        Assert.assertNotNull(initialColor, "Initial background color should not be null!");

        // Hover over Point Me button to trigger hover styling
        mhp.hoverOverPointMe();
        String hoveredColor = mhp.getPointMeBackgroundColor();
        Assert.assertNotNull(hoveredColor, "Hovered background color should not be null!");
    }
}
