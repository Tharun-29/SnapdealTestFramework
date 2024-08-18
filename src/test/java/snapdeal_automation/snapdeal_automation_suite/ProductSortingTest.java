package snapdeal_automation.snapdeal_automation_suite;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import annotations.TestDescription;

public class ProductSortingTest extends BaseTest {

    // Test Case 2.1: Verify that products can be sorted by price from low to high
    @Test
    @TestDescription("TC 2 - Product Sorting Test (Price Low to High)")
    public void verifySortingByPriceLowToHigh() {
        
        // Step 1: Retrieve the product name from the configuration file
        String product = configReader.getProperty("product");

        // Step 2: Perform the search operation on the landing page using the product name
        ProductPage productPage = landingPage.searchProducts(product);

        // Step 3: Select the "Price Low to High" sorting option from the sort dropdown
        productPage.selectSortOption("Price Low To High");

        // Step 4: Verify that the products are sorted by price in ascending order
        Assert.assertTrue(productPage.verifyPriceSortedAscending(), 
                "The products are not sorted by price in ascending order.");
    }

    // Test Case 2.2: Verify that products can be sorted by price from high to low
    @Test (description = "Sorting Test")
    @TestDescription("TC 2 - Product Sorting Test (Price High to Low)")
    public void verifySortingByPriceHighToLow() {
        
        // Step 1: Retrieve the product name from the configuration file
        String product = configReader.getProperty("product");

        // Step 2: Perform the search operation on the landing page using the product name
        ProductPage productPage = landingPage.searchProducts(product);

        // Step 3: Select the "Price High to Low" sorting option from the sort dropdown
        productPage.selectSortOption("Price High To Low");

        // Step 4: Verify that the products are sorted by price in descending order
        Assert.assertTrue(productPage.verifyPriceSortedDescending(), 
                "The products are not sorted by price in descending order.");
    }
}