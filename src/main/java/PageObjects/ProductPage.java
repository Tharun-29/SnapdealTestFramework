package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[contains(@class,'product-image')]")
    private List<WebElement> productImages;

    @FindBy(xpath = "(//div[contains(@class,'product-tuple-listing')])")
    private List<WebElement> productResults;

    @FindBy(xpath = "//div[contains(@class, 'sort-selected')]")
    private WebElement sortByDropdown;

    @FindBy(xpath = "//span[@class='lfloat product-price']")
    private List<WebElement> productPrices;

    @FindBy(id = "add-cart-button-id")
    private WebElement addToCartButton;

    @FindBy(className = "mess-text")
    private WebElement cartMessage;

    @FindBy(xpath = "//div[text()='View Cart']")
    private WebElement viewCartButton;

    @FindBy(xpath = "//span[contains(.,'REMOVE')]")
    private WebElement removeFromCartButton;

    @FindBy(xpath = "//div[@class='cart-heading clearfix']//h3[1]")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//input[@name='fromVal']")
    private WebElement minPriceInput;

    @FindBy(xpath = "//input[@name='toVal']")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//div[contains(@class,'price-go-arrow')]")
    private WebElement goButton;

    @FindBy(xpath = "//input[@data-filtername='Brand']")
    private WebElement productBrandSearch;

    @FindBy(xpath = "(//input[@data-name='filterSearch-Brand'])[1]")
    private WebElement productBrandSearchInput;

    @FindBy(xpath = "//div[text()='APPLY']")
    private WebElement brandFilterApplyButton;

    // Select sorting option
    public void selectSortOption(String option) {
        sortByDropdown.click();
        WebElement sortOption = driver.findElement(By.xpath("//li[contains(.,'" + option + "')]"));
        sortOption.click();
        WaitHelper.staticWait(2); // Wait for dropdown to update
    }

    // Get product prices from the page
    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : productPrices) {
            String priceText = priceElement.getText().trim();
            String numericPrice = priceText.replaceAll("[^\\d]", "");
            if (!numericPrice.isEmpty()) {
                prices.add(Double.parseDouble(numericPrice));
            }
        }
        return prices;
    }

    // Verify if prices are sorted in ascending order
    public boolean verifyPriceSortedAscending() {
        List<Double> prices = getProductPrices();
        if (prices.isEmpty()) return false;
        return prices.get(0) <= prices.get(prices.size() - 1);
    }

    // Verify if prices are sorted in descending order
    public boolean verifyPriceSortedDescending() {
        List<Double> prices = getProductPrices();
        if (prices.isEmpty()) return false;
        return prices.get(0) >= prices.get(prices.size() - 1);
    }

    // Check if the number of product images matches the number of products
    public boolean validateAllProductImagesDisplayed() {
        return productResults.size() == productImages.size();
    }

    // Add the product to the cart
    public void addToCart() {
        addToCartButton.click();
        WaitHelper.staticWait(2); // Wait for the product to be added
    }

    // Check if the product has been added to the cart
    public boolean isProductAddedToCart() {
        WaitHelper.staticWait(2); // Wait for cart message update
        return cartMessage.getText().trim().contains("added to your cart");
    }

    // Remove the product from the cart
    public void removeFromCart() {
        viewCartButton.click();
        WaitHelper.staticWait(2); // Wait for cart page to load
        removeFromCartButton.click();
    }

    // Verify if the product has been removed from the cart
    public String isProductRemovedFromCart() {
        WaitHelper.staticWait(2); // Wait for cart update
        return emptyCartMessage.getText();
    }

    // Apply price filter
    public void applyPriceFilter(String minPrice, String maxPrice) {
        minPriceInput.clear();
        minPriceInput.sendKeys(minPrice);
        maxPriceInput.clear();
        maxPriceInput.sendKeys(maxPrice);
        goButton.click();
        WaitHelper.staticWait(2); // Wait for filter to apply
    }

    // Verify if all prices are within the specified range
    public boolean verifyPricesInRange(double minPrice, double maxPrice) {
        List<Double> prices = getProductPrices();
        for (Double price : prices) {
            if (price < minPrice || price > maxPrice) return false;
        }
        return true;
    }

    // Apply brand filter
    public void applyBrandFilter(String brandName) {
        productBrandSearch.click();
        WaitHelper.staticWait(3); // Wait for filter options to load
        productBrandSearchInput.sendKeys(brandName);
        WebElement brandFilterCheckbox = driver.findElement(By.xpath("//label[@for='Brand-" + brandName + "']"));
        WaitHelper.staticWait(2); // Wait for checkbox to be clickable
        brandFilterCheckbox.click();
        brandFilterApplyButton.click();
    }

    // Verify if all products are from the specified brand
    public boolean verifyProductsFromBrand(String brandName) {
        String brandValue = driver.findElement(By.xpath("//a[@data-key='Brand|Brand']")).getText();
        return brandValue.equalsIgnoreCase(brandName);
    }
}