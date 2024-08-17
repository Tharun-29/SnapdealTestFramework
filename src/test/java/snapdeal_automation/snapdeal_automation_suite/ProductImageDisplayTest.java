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

		// Get the product name from the config file
		String product = configReader.getProperty("product");

		// Perform the search operation
		ProductPage productPage = landingPage.searchProducts(product);

		// Verify product images
		boolean areImagesDisplayed = productPage.validateAllProductImagesDisplayed();
		System.out.println(areImagesDisplayed);

		// Assertion to be added here to verify product images
		Assert.assertTrue(areImagesDisplayed, "Some " + product + " images in the search results.");
	}

}
