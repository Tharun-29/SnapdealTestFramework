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

	// Constructor to initialize configReader
	public BaseTest() {
		configReader = new ConfigReader();
	}

	public WebDriver initializeDriver() {

		String browser = configReader.getProperty("browser");

		if (browser.contains("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			opt.addArguments("start-maximized");
			opt.setAcceptInsecureCerts(true);
			opt.addArguments("incognito");

			// Check if headless mode is enabled
			if (browser.contains("headless")) {
				opt.addArguments("headless"); // Run Chrome in headless mode (no GUI)
				WebDriverManager.chromedriver().setup();
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

	@BeforeMethod(alwaysRun = true)
	public LandingPage setup() {
		driver = initializeDriver();
		String applicationUrl = configReader.getProperty("baseURL");

		landingPage = new LandingPage(driver);
		landingPage.launchApplication(applicationUrl);

		return landingPage;

	}

	// Method executed after each test method to tear down and quit the WebDriver
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		if (driver != null) {
			driver.quit();
		}
	}

	// Method to capture screenshot and save it
		public String getScreenShot(String TestCaseName, WebDriver driver) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) driver; // Cast WebDriver to TakesScreenshot
			File src = ts.getScreenshotAs(OutputType.FILE); // Capture screenshot as FILE
			File dest = new File(System.getProperty("user.dir") + "//reports//" + TestCaseName + ".png");
			FileUtils.copyFile(src, dest); // Copy screenshot to destination
			return System.getProperty("user.dir") + "//reports//" + TestCaseName + ".png"; // Return screenshot path
		}

}
