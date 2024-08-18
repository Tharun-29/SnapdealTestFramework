package snapdeal_automation.snapdeal_automation_suite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.ProductPage;
import TestComponents.BaseTest;
import annotations.TestDescription;
import utilities.WindowSwitcher;

public class RemoveFromCartTest extends BaseTest {
    
    // Test Case 7: Verify the functionality of removing a product from the cart
    @Test
    @TestDescription("TC 7 - Product Removal From Cart Test")
    public void verifyRemoveFromCartFunctionality() {
        
        // Step 1: Retrieve the product name from the configuration file
        String product = configReader.getProperty("product");

        // Step 2: Perform the search operation on the landing page using the product name
        ProductPage productPage = landingPage.searchProducts(product);

        // Step 3: Click on the first product to open its details in a new window
        WebElement firstProduct = landingPage.getFirstProduct();
        firstProduct.click();

        // Step 4: Switch to the new window where the product details are displayed
        WindowSwitcher.switchToNewWindow(driver);

        // Step 5: Add the product to the cart in the new window
        productPage.addToCart();
        
        // Assertion 1: Verify that the product was successfully added to the cart
        Assert.assertTrue(productPage.isProductAddedToCart(), "The product was not added to the cart.");

        // Step 6: Remove the product from the cart
        productPage.removeFromCart();
        
        // Step 7: Verify that the product was successfully removed from the cart
        Assert.assertEquals(productPage.isProductRemovedFromCart(), "Shopping Cart is empty!",
                "The product was not removed from the cart as expected.");
    }
}