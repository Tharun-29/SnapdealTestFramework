package snapdeal_automation.snapdeal_automation_suite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import utilities.WindowSwitcher;
import annotations.TestDescription;

public class AddToCartTest extends BaseTest {

	@Test
	@TestDescription("TC 6 - Product Add To Cart Test")
	public void verifyAddToCartFunctionality() {
		String product = configReader.getProperty("product");

		/// Step 1: Perform the search operation
		ProductPage productPage = landingPage.searchProducts(product);

		// Step 2: Click on the first product to open its details in a new window

		WebElement firstProduct = landingPage.getFirstProduct();
		firstProduct.click();

		// Step 2: Switch to the new window
		WindowSwitcher.switchToNewWindow(driver);

		// Step 4: Add the product to the cart in the new window
		productPage.addToCart();

		// Step 5: Verify that the product was added to the cart
		Assert.assertTrue(productPage.isProductAddedToCart(), "The product was not added to the cart.");

	}

}
