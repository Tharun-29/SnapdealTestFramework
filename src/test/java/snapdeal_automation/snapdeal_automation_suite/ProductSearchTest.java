package snapdeal_automation.snapdeal_automation_suite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import annotations.TestDescription;

public class ProductSearchTest extends BaseTest{

    @Test
    @TestDescription("TC 1 - Product Search Test")
    public void productSearch() {
	    
        // Step 1: Retrieve the product keyword from the configuration file
        String product = configReader.getProperty("product");
        
        // Step 2: Perform the search operation on the landing page using the product keyword
        landingPage.searchProducts(product);
		
        // Step 3: Verify that the search keyword is present in the search results header
        String searchResultsHeaderText = landingPage.getSearchResultsHeaderText();
        Assert.assertTrue(searchResultsHeaderText.contains(product),
            "Search results are not relevant to the keyword: " + product);
		
        // Step 4: Verify that at least one product is displayed in the search results
        WebElement firstProduct = landingPage.getFirstProduct();
        Assert.assertTrue(firstProduct.isDisplayed(),
            "No products are displayed in the search results for the keyword: " + product);
    }
}