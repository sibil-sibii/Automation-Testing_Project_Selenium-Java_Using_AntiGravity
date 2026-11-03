package tests;

import base.Base_page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LinksLabelsPage;

import java.util.List;

// This test class verifies HTTP link status codes (broken links audit) and dynamic attribute identifier strategies
public class LinksLabelsTests extends Base_page {

    // TC_LNK_01: Fetch all <a> tags, send HTTP HEAD requests using HttpURLConnection to verify HTTP status codes
    @Test(priority = 1, description = "TC_LNK_01: Audit links using HTTP HEAD requests and verify status codes")
    public void testBrokenLinksAudit() {
        LinksLabelsPage llp = new LinksLabelsPage(driver);

        // Fetch valid laptop links from the page
        List<String> validUrls = llp.getLaptopLinkUrls();
        Assert.assertFalse(validUrls.isEmpty(), "Valid laptop links list should not be empty!");

        // Verify that the first valid link returns an HTTP status code < 400 (Success or Redirect)
        String firstValidUrl = validUrls.get(0);
        int statusCode = llp.getHttpResponseCode(firstValidUrl);
        Assert.assertTrue(statusCode < 400, "Valid link (" + firstValidUrl + ") should return status code < 400, but got: " + statusCode);

        // Fetch broken links from the broken links section
        List<String> brokenUrls = llp.getBrokenLinkUrls();
        Assert.assertFalse(brokenUrls.isEmpty(), "Broken links list should not be empty!");

        // Verify that known broken link returns status code >= 400
        int brokenStatusCode = llp.getHttpResponseCode(brokenUrls.get(0));
        Assert.assertTrue(brokenStatusCode >= 400, "Broken link (" + brokenUrls.get(0) + ") should return error code >= 400, but got: " + brokenStatusCode);
    }

    // TC_LNK_02: Interact with labels and buttons possessing dynamic IDs using CSS/XPath partial attribute matching strategies
    @Test(priority = 2, description = "TC_LNK_02: Interact with elements using dynamic CSS/XPath attribute matching")
    public void testDynamicIdentifiers() {
        LinksLabelsPage llp = new LinksLabelsPage(driver);

        // 1. Partial attribute matching on dynamic button using contains(@class, 'start' / 'stop')
        String initialButtonText = llp.getDynamicButtonText();
        Assert.assertEquals(initialButtonText, "START", "Initial dynamic button text should be 'START'!");

        llp.clickDynamicButton();
        String toggledButtonText = llp.getDynamicButtonText();
        Assert.assertEquals(toggledButtonText, "STOP", "Toggled dynamic button text should be 'STOP'!");

        // 2. Partial CSS selector with prefix match: a[id^='app'] for id='apple'
        String appleText = llp.getAppleLinkTextByCssPrefix();
        Assert.assertEquals(appleText, "Apple", "CSS prefix match should locate Apple link!");

        // 3. Partial XPath with contains(): //label[contains(@id, 'real')] for id='realme'
        String realMeText = llp.getRealMeLabelTextByXPath();
        Assert.assertEquals(realMeText, "Real Me", "XPath contains match should locate Real Me label!");

        // 4. Partial XPath with contains(): //label[contains(@id, 'mot')] for id='moto'
        String motoText = llp.getMotoLabelTextByXPath();
        Assert.assertEquals(motoText, "Moto", "XPath contains match should locate Moto label!");
    }
}

