package snapdeal_automation.snapdeal_automation_suite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import annotations.TestDescription;
import utilities.WindowSwitcher;

public class RemoveFromCartTest extends BaseTest {
    
	@Test
	@TestDescription("TC 7 - Product Removal From Cart Test")
	public void verifyRemoveFromCartFunctionality() {
		String product = configReader.getProperty("product");

		// Step 1: Perform the search operation
		ProductPage productPage = landingPage.searchProducts(product);

		// Step 2: Click on the first product to open its details in a new window
		WebElement firstProduct = landingPage.getFirstProduct();
		firstProduct.click();

		// Step 3: Switch to the new window
		WindowSwitcher.switchToNewWindow(driver);

		// Step 4: Add the product to the cart in the new window
		productPage.addToCart();
		Assert.assertTrue(productPage.isProductAddedToCart(), "The product was not added to the cart.");

		// Step 5: Remove the product from the cart
		productPage.removeFromCart();
		
		// Step 6: Verify that the product was removed from the cart
        Assert.assertEquals(productPage.isProductRemovedFromCart(), "Shopping Cart is empty!");

	}

}
