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
		String product = configReader.getProperty("product");
		String brandName = configReader.getProperty("brandName");

		// Step 1: Perform the search operation
		ProductPage productPage = landingPage.searchProducts(product);

		// Step 2: Apply the brand filter
		productPage.applyBrandFilter(brandName);

		// Step 3: Verify that all displayed search results are from the selected brand
		boolean areProductsFromBrand = productPage.verifyProductsFromBrand(brandName);

		// Step 4: Assertion to ensure that all products are from the specified brand
		Assert.assertTrue(areProductsFromBrand, "Not all products are from the selected brand: " + brandName);
	}
}
