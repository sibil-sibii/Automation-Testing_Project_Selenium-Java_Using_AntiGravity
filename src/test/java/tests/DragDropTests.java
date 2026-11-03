package tests;

import base.Base_page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragDropPage;

// This test class verifies Drag and Drop functionality and Range Slider horizontal dragging
public class DragDropTests extends Base_page {

    // TC_DRG_01: Drag source element 'Draggable' and drop onto target container 'Droppable'. Assert text changes to 'Dropped!'
    @Test(priority = 1, description = "TC_DRG_01: Verify Drag and Drop action updates target text to 'Dropped!'")
    public void testBasicDragAndDrop() {
        DragDropPage ddp = new DragDropPage(driver);

        // Perform drag and drop action
        ddp.performDragAndDrop();

        // Verify target container text updates to 'Dropped!'
        String droppedText = ddp.getDroppableText();
        Assert.assertEquals(droppedText, "Dropped!", "Target container text should be 'Dropped!' after drop!");
    }

    // TC_DRG_02: Drag slider handle horizontally by relative x-offset to adjust numerical value. Verify updated slider value
    @Test(priority = 2, description = "TC_DRG_02: Verify horizontal drag of Range Slider adjusts numerical amount")
    public void testRangeSliderDrag() {
        DragDropPage ddp = new DragDropPage(driver);

        // Capture initial slider amount value
        String initialAmount = ddp.getSliderAmountValue();
        Assert.assertNotNull(initialAmount, "Initial slider amount should not be null!");

        // Drag handle by +60 pixels horizontally
        ddp.dragSliderHandle(60);

        // Capture updated slider amount value
        String updatedAmount = ddp.getSliderAmountValue();
        Assert.assertNotNull(updatedAmount, "Updated slider amount should not be null!");

        // Assert that the slider value changed
        Assert.assertNotEquals(updatedAmount, initialAmount, "Slider amount value should change after dragging!");
    }
}

