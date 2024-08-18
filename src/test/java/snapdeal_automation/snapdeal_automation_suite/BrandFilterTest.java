package snapdeal_automation.snapdeal_automation_suite;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import annotations.TestDescription;

public class BrandFilterTest extends BaseTest {

    @Test
    @TestDescription("TC 3 - Brand Filter Test")
    public void verifyBrandFilterFunctionality() {
        // Step 1: Retrieve the product name and brand name from the configuration file
        String product = configReader.getProperty("product");
        String brandName = configReader.getProperty("brandName");

        // Step 2: Perform the search operation for the specified product
        ProductPage productPage = landingPage.searchProducts(product);

        // Step 3: Apply the brand filter based on the specified brand name
        productPage.applyBrandFilter(brandName);

        // Step 4: Verify that all displayed search results are from the selected brand
        boolean areProductsFromBrand = productPage.verifyProductsFromBrand(brandName);

        // Step 5: Assert that all products are from the specified brand, providing a clear error message if not
        Assert.assertTrue(areProductsFromBrand, 
                "Not all products are from the selected brand: " + brandName);
    }
}