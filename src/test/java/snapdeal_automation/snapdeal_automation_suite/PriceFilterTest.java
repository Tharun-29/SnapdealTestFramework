package snapdeal_automation.snapdeal_automation_suite;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import annotations.TestDescription;

public class PriceFilterTest extends BaseTest {

    @Test
    @TestDescription("TC 4 - Product Price Test")
    public void verifyPriceFilterFunctionality() {
        // Step 1: Retrieve the product name and price range from the configuration file
        String product = configReader.getProperty("product");
        String minPrice = configReader.getProperty("minPrice");
        String maxPrice = configReader.getProperty("maxPrice");

        // Step 2: Perform the search operation for the specified product
        ProductPage productPage = landingPage.searchProducts(product);

        // Step 3: Apply the price filter based on the specified minimum and maximum price
        productPage.applyPriceFilter(minPrice, maxPrice);

        // Step 4: Convert the minPrice and maxPrice values to double for comparison
        double minPriceValue = Double.parseDouble(minPrice);
        double maxPriceValue = Double.parseDouble(maxPrice);

        // Step 5: Verify that all displayed search results fall within the selected price range
        boolean arePricesInRange = productPage.verifyPricesInRange(minPriceValue, maxPriceValue);

        // Step 6: Assert that all product prices are within the specified range, providing a clear error message if not
        Assert.assertTrue(arePricesInRange, 
                "Not all products are within the specified price range.");
    }
}