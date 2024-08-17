package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;
	
	@FindBy(id = "inputValEnter")
	WebElement searchBar;
	
	@FindBy(className = "searchTextSpan")
	WebElement searchIcon;
	
	@FindBy(xpath = "(//span[@class='nnn'])[1]")
	WebElement searchResultText;
	
	@FindBy(xpath = "(//div[contains(@class,'col-xs-6 ')])[1]")
	WebElement firstProduct;
	
	
    public LandingPage(WebDriver driver) {
	  	this.driver = driver;
	  	PageFactory.initElements(driver, this);
	}
    
    
    public void launchApplication(String baserURL) {
		driver.get(baserURL);
	}
    
    
    public ProductPage searchProducts(String product) {
    	searchBar.sendKeys(product);
    	searchIcon.click();
    	return new ProductPage(driver); // Return new ProductPage instance
    }
    
    public String getSearchResultsHeaderText() {
        return searchResultText.getText();
    }

    public WebElement getFirstProduct() {
    	
        return firstProduct;
    }
    
}
