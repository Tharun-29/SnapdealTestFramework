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
	    
		String product = configReader.getProperty("product");
		landingPage.searchProducts(product);
		
		// Assertion 1: Verify that the search keyword is present in the search results header
		String searchResultsHeaderText = landingPage.getSearchResultsHeaderText();
		Assert.assertTrue(searchResultsHeaderText.contains(product),"Search results are not relevant to the keyword:" + product);
		
		// Assertion 2: Verify that at least one product is displayed in the search results
		WebElement firstProduct = landingPage.getFirstProduct();
		Assert.assertTrue(firstProduct.isDisplayed(),"No products are displayed in the search results for the keyword: " + product);
	}
	
	
}
