package TestComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

public class BaseTest {

	public WebDriver driver;
	public ConfigReader configReader;
	public LandingPage landingPage;

	// Initialize ConfigReader
	public BaseTest() {
		configReader = new ConfigReader();
	}

	// Set up WebDriver based on the browser configuration
	public WebDriver initializeDriver() {

		// Read browser name from system property or config file
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: configReader.getProperty("browser");

		if (browser.contains("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			opt.addArguments("start-maximized", "incognito");
			opt.setAcceptInsecureCerts(true);
			if (browser.contains("headless")) {
				opt.addArguments("headless");
			}
			driver = new ChromeDriver(opt);
		} else if (browser.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		int implicitWait = Integer.parseInt(configReader.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		return driver;
	}

	// Set up method to initialize WebDriver and open the application
	@BeforeMethod(alwaysRun = true)
	public LandingPage setup() {
		driver = initializeDriver();
		String applicationUrl = configReader.getProperty("baseURL");

		landingPage = new LandingPage(driver);
		landingPage.launchApplication(applicationUrl);

		return landingPage;
	}

	// Clean up method to quit the WebDriver
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000); // Delay for visibility
		if (driver != null) {
			driver.quit();
		}
	}

	// Capture and save a screenshot
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(src, dest);
		return dest.getPath();
	}
}