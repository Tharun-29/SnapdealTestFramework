package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    private WebDriver driver;

    @FindBy(id = "inputValEnter")
    private WebElement searchBar;

    @FindBy(className = "searchTextSpan")
    private WebElement searchIcon;

    @FindBy(xpath = "(//span[@class='nnn'])[1]")
    private WebElement searchResultText;

    @FindBy(xpath = "(//div[contains(@class,'col-xs-6 ')])[1]")
    private WebElement firstProduct;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Launch the application with the given URL
    public void launchApplication(String baseURL) {
        driver.get(baseURL);
    }

    // Perform search and return the ProductPage instance
    public ProductPage searchProducts(String product) {
        searchBar.sendKeys(product);
        searchIcon.click();
        return new ProductPage(driver);
    }

    // Get the text of the search results header
    public String getSearchResultsHeaderText() {
        return searchResultText.getText();
    }

    // Get the first product element
    public WebElement getFirstProduct() {
        return firstProduct;
    }
}