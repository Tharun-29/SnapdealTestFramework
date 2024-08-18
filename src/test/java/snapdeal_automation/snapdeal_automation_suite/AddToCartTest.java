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
        // Step 1: Retrieve the product name from the configuration file
        String product = configReader.getProperty("product");

        // Step 2: Perform the search operation for the specified product
        ProductPage productPage = landingPage.searchProducts(product);

        // Step 3: Locate the first product from the search results
        WebElement firstProduct = landingPage.getFirstProduct();

        // Step 4: Click on the first product to open its details in a new window
        firstProduct.click();

        // Step 5: Switch to the newly opened window displaying the product details
        WindowSwitcher.switchToNewWindow(driver);

        // Step 6: Add the product to the cart in the new window
        productPage.addToCart();

        // Step 7: Verify that the product has been successfully added to the cart
        Assert.assertTrue(productPage.isProductAddedToCart(), "The product was not added to the cart.");
    }
}