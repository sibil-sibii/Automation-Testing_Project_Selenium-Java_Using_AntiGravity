package tests;

import base.Base_page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WebTablesPage;

// This test class verifies static table calculations and dynamic pagination table interactions
public class WebTablesTests extends Base_page {

    // TC_TBL_01: Extract row/column counts from book table and calculate total price sum of all listed books
    @Test(priority = 1, description = "TC_TBL_01: Verify BookTable row/column count and total price sum using Streams")
    public void testStaticGridAudit() {
        WebTablesPage wtp = new WebTablesPage(driver);

        // Verify total row count (1 header row + 6 book data rows = 7 rows)
        int rowCount = wtp.getBookTableRowCount();
        Assert.assertEquals(rowCount, 7, "Book table should contain 7 total rows!");

        // Verify total column count (BookName, Author, Subject, Price = 4 columns)
        int colCount = wtp.getBookTableColumnCount();
        Assert.assertEquals(colCount, 4, "Book table should contain 4 columns!");

        // Calculate price sum using Streams and assert against expected sum (300+500+300+3000+2000+1000 = 7100)
        int totalPrice = wtp.calculateTotalPriceUsingStreams();
        Assert.assertEquals(totalPrice, 7100, "Calculated total price sum of all books should be 7100!");
    }

    // TC_TBL_02: Iterate through pagination dynamic table to locate specific product row and verify corresponding price
    @Test(priority = 2, description = "TC_TBL_02: Locate specific product across pagination table and verify price")
    public void testDynamicSearchFilter() {
        WebTablesPage wtp = new WebTablesPage(driver);

        // Search for product 'Laptop' and verify its price ($19.99)
        String laptopPrice = wtp.getProductPriceFromPaginationTable("Laptop");
        Assert.assertNotNull(laptopPrice, "Product 'Laptop' should be found in pagination table!");
        Assert.assertEquals(laptopPrice, "$19.99", "Price for 'Laptop' does not match!");

        // Search for product 'Smartphone' and verify its price ($10.99)
        String phonePrice = wtp.getProductPriceFromPaginationTable("Smartphone");
        Assert.assertNotNull(phonePrice, "Product 'Smartphone' should be found in pagination table!");
        Assert.assertEquals(phonePrice, "$10.99", "Price for 'Smartphone' does not match!");
    }

    // TC_TBL_03: Locate specific table row based on item name and select corresponding checkbox in column
    @Test(priority = 3, description = "TC_TBL_03: Select checkbox corresponding to specific product row")
    public void testCheckboxSelection() {
        WebTablesPage wtp = new WebTablesPage(driver);

        // Select checkbox for product 'Laptop'
        wtp.selectProductCheckbox("Laptop");
        Assert.assertTrue(wtp.isProductCheckboxSelected("Laptop"), "Checkbox for 'Laptop' should be selected!");
    }
}

