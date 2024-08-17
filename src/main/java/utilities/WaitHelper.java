package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void staticWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);  // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
