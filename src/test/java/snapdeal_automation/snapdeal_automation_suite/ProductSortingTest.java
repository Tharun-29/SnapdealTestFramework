package snapdeal_automation.snapdeal_automation_suite;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import annotations.TestDescription;

public class ProductSortingTest extends BaseTest{
	
    @Test
    @TestDescription("TC 2 - Product Sorting Test")
	public void verifySortingByPriceLowToHigh() {
		//Get the product name from the config file
        String product = configReader.getProperty("product");

        //Perform the search operation
        ProductPage productPage = landingPage.searchProducts(product);

        //Select "Price Low to High" from the sort options
        productPage.selectSortOption("Price Low To High");

        //Verify that the products are sorted by price in ascending order
        Assert.assertTrue(productPage.verifyPriceSortedAscending(), 
                "The products are not sorted by price in ascending order.");
	}
	
    @Test (description = "Sorting Test")
    @TestDescription("TC 2 - Product Sorting Test")
    public void verifySortingByPriceHighToLow() {
        // Get the product name from the config file
        String product = configReader.getProperty("product");

        // Perform the search operation
        ProductPage productPage = landingPage.searchProducts(product);

        // Select "Price High to Low" from the sort options
        productPage.selectSortOption("Price High To Low");

        // Verify that the products are sorted by price in descending order
        Assert.assertTrue(productPage.verifyPriceSortedDescending(), 
                "The products are not sorted by price in descending order.");
    }
}
