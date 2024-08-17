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

	public WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[contains(@class,'product-image')]")
	List<WebElement> productImages;

	@FindBy(xpath = "(//div[contains(@class,'product-tuple-listing')])")
	List<WebElement> productResults;

	@FindBy(xpath = "//div[contains(@class, 'sort-selected')]")
	WebElement sortByDropdown;

	@FindBy(xpath = "//span[@class='lfloat product-price']")
	List<WebElement> productPrices;

	@FindBy(id = "add-cart-button-id")
	WebElement addToCartButton;

	@FindBy(className = "mess-text")
	WebElement cartMessage;

	@FindBy(xpath = "//div[text()='View Cart']")
	WebElement viewCartButton;

	@FindBy(xpath = "//span[contains(.,'REMOVE')]")
	WebElement removeFromCartButton;

	@FindBy(xpath = "//div[@class='cart-heading clearfix']//h3[1]")
	WebElement emptyCartMessage;

	@FindBy(xpath = "//input[@name='fromVal']")
	WebElement minPriceInput;

	@FindBy(xpath = "//input[@name='toVal']")
	WebElement maxPriceInput;

	@FindBy(xpath = "//div[contains(@class,'price-go-arrow')]")
	WebElement goButton;

	@FindBy(xpath = "//input[@data-filtername='Brand']")
	WebElement productBrandSearch;

	@FindBy(xpath = "(//input[@data-name='filterSearch-Brand'])[1]")
	WebElement productBrandSearchInput;

	@FindBy(xpath = "//div[text()='APPLY']")
	WebElement brandFilterApplyButton;

	public void selectSortOption(String option) {
		sortByDropdown.click();

		WebElement sortOption = driver.findElement(By.xpath("//li[contains(.,'" + option + "')]"));
		sortOption.click();

		// Add static wait before interacting with the dropdown
		WaitHelper.staticWait(2); // Wait for 2 seconds
	}

	public List<Double> getProductPrices() {
		List<Double> prices = new ArrayList<>();
		for (WebElement priceElement : productPrices) {
			String priceText = priceElement.getText().trim(); // Using getText() to retrieve the price text

			// Remove any non-numeric characters, such as commas or currency symbols
			String numericPrice = priceText.replaceAll("[^\\d]", "");
			if (!numericPrice.isEmpty()) {
				prices.add(Double.parseDouble(numericPrice));
			} else {
				System.out.println("Unable to parse price: " + priceText);
			}
		}

		return prices;
	}

	public boolean verifyPriceSortedAscending() {
		List<Double> prices = getProductPrices();
		if (prices.isEmpty()) {
			return false; // Handle empty list case

		}

		Double firstPrice = prices.get(0);
		Double lastPrice = prices.get(prices.size() - 1);

		return firstPrice <= lastPrice;
	}

	public boolean verifyPriceSortedDescending() {
		List<Double> prices = getProductPrices();
		if (prices.isEmpty()) {
			return false; // Handle empty list case
		}

		Double firstPrice = prices.get(0);
		Double lastPrice = prices.get(prices.size() - 1);

		return firstPrice >= lastPrice;
	}

	public boolean validateAllProductImagesDisplayed() {

		if (productResults.size() != productImages.size()) {
			return false;
		}
		return true;
	}

	public void addToCart() {
		// Step 1: Locate the "Add to Cart" button and click it
		addToCartButton.click();

		// Step 2: Optionally, you can add a wait to ensure the product is added to the
		// cart
		WaitHelper.staticWait(2); // Wait for 2 seconds for the action to complete
	}

	public boolean isProductAddedToCart() {
		// Wait for the cart count or success message to be updated
		WaitHelper.staticWait(2); // Adjust the wait time as needed

		// Option 1: Check cart count increment
		String message = cartMessage.getText().trim();

		if (message.contains("added to your cart")) {
			return true;
		}

		return false;
	}

	public void removeFromCart() {
		viewCartButton.click();
		WaitHelper.staticWait(2); // Wait for the cart to update
		removeFromCartButton.click();

	}

	public String isProductRemovedFromCart() {
		WaitHelper.staticWait(2); // Wait for the cart to update

		String emptyMessage = emptyCartMessage.getText();
		return emptyMessage;
	}

	public void applyPriceFilter(String minPrice, String maxPrice) {
		minPriceInput.clear();
		minPriceInput.sendKeys(minPrice);

		maxPriceInput.clear();
		maxPriceInput.sendKeys(maxPrice);

		goButton.click();

		WaitHelper.staticWait(2);

	}

	public boolean verifyPricesInRange(double minPrice, double maxPrice) {

		List<Double> prices = getProductPrices();
		for (Double price : prices) {
			if (price < minPrice || price > maxPrice) {
				return false; // Return false if any price is out of range
			}
		}
		return true;
	}

	public void applyBrandFilter(String brandName) {

		productBrandSearch.click();
		WaitHelper.staticWait(3);
		productBrandSearchInput.sendKeys(brandName);
		WebElement brandFilterCheckbox = driver.findElement(By.xpath("//label[@for='Brand-" + brandName + "']"));
		WaitHelper.staticWait(2);
		brandFilterCheckbox.click();
		
		brandFilterApplyButton.click();

	}

	public boolean verifyProductsFromBrand(String brandName) {
		
		String brandValue = driver.findElement(By.xpath("//a[@data-key='Brand|Brand']")).getText();
		
		if(!(brandValue.equalsIgnoreCase(brandName))) {
			return false;
		}
		
		return true; // All products are from the selected brand
	}

}
