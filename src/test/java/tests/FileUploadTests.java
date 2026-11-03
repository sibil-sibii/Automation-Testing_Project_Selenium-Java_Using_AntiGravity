package tests;

import base.Base_page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FileUploadPage;

import java.io.File;

// This test class verifies Single File Upload and Multiple Files Upload
public class FileUploadTests extends Base_page {

    // TC_UPL_01: Provide local file path to input element of type 'file'. Verify upload status message rendering on UI
    @Test(priority = 1, description = "TC_UPL_01: Verify single file upload and status message rendering")
    public void testSingleFileUpload() {
        FileUploadPage fup = new FileUploadPage(driver);

        // Build file path dynamically using user.dir
        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "testdata" + File.separator + "sample1.txt";

        // Upload single file
        fup.uploadSingleFile(filePath);

        // Verify status message renders on UI and mentions the uploaded file name
        String statusMessage = fup.getSingleFileStatusMessage();
        Assert.assertTrue(statusMessage.contains("sample1.txt"),
                "Upload status message should contain the uploaded file name 'sample1.txt'!");
    }

    // TC_UPL_02: Upload multiple files simultaneously by passing newline-separated file paths to upload input element
    @Test(priority = 2, description = "TC_UPL_02: Verify multiple files simultaneous upload")
    public void testMultipleFilesUpload() {
        FileUploadPage fup = new FileUploadPage(driver);

        // Build file paths dynamically using user.dir
        String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "testdata" + File.separator;
        String file1Path = basePath + "sample1.txt";
        String file2Path = basePath + "sample2.txt";

        // Separate multiple paths with newline character
        String combinedPaths = file1Path + "\n" + file2Path;

        // Upload multiple files
        fup.uploadMultipleFiles(combinedPaths);

        // Verify status message renders and mentions both files
        String statusMessage = fup.getMultipleFilesStatusMessage();
        Assert.assertTrue(statusMessage.contains("sample1.txt"),
                "Upload status message should contain 'sample1.txt'!");
        Assert.assertTrue(statusMessage.contains("sample2.txt"),
                "Upload status message should contain 'sample2.txt'!");
    }
}

