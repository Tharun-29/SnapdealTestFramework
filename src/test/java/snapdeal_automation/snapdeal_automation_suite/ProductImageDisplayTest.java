package snapdeal_automation.snapdeal_automation_suite;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import annotations.TestDescription;

public class ProductImageDisplayTest extends BaseTest {

    @Test
    @TestDescription("TC 5 - Product Image Display Test")
    public void verifyProductImage() {

        // Step 1: Retrieve the product name from the configuration file
        String product = configReader.getProperty("product");

        // Step 2: Perform the search operation for the specified product
        ProductPage productPage = landingPage.searchProducts(product);

        // Step 3: Validate that all product images are displayed in the search results
        boolean areImagesDisplayed = productPage.validateAllProductImagesDisplayed();
        System.out.println(areImagesDisplayed); // Logging the result for debugging

        // Step 4: Assert that all product images are displayed, providing a clear error message if not
        Assert.assertTrue(areImagesDisplayed, "Some " + product + " images are missing in the search results.");
    }

}