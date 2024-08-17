package utilities;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowSwitcher {
  
	// Method to switch to the newly opened window
    public static void switchToNewWindow(WebDriver driver) {
        // Get the current window handle before the new window opens
        String currentWindow = driver.getWindowHandle();

        // Get all window handles
        Set<String> allWindows = driver.getWindowHandles();

        // Loop through all window handles
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                // Switch to the new window
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // Method to switch back to the original window
    public static void switchToOriginalWindow(WebDriver driver, String originalWindowHandle) {
        driver.switchTo().window(originalWindowHandle);
    }
}
