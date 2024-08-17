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
		String product = configReader.getProperty("product");
		String minPrice = configReader.getProperty("minPrice");
		String maxPrice = configReader.getProperty("maxPrice");

		// Step 1: Perform the search operation
		ProductPage productPage = landingPage.searchProducts(product);

		// Step 2: Apply the price filter
		productPage.applyPriceFilter(minPrice, maxPrice);

		// Step 3: Verify that all the displayed search results fall within the selected
		// price range
		double minPriceValue = Double.parseDouble(minPrice);
		double maxPriceValue = Double.parseDouble(maxPrice);

		boolean arePricesInRange = productPage.verifyPricesInRange(minPriceValue, maxPriceValue);

		// Step 4: Assertion to ensure that prices are within the specified range
		Assert.assertTrue(arePricesInRange, "Not all products are within the specified price range.");
	}
}
