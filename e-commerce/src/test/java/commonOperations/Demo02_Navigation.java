package commonOperations;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo02_Navigation {
	WebDriver driver;
	@Test
	public void getNavigation() throws InterruptedException {
		// Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // 1. Open a page using driver.get()
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Thread.sleep(2000);

        // 2. Navigate to another page using navigate().to()
        driver.navigate().to("https://www.selenium.dev/");
        Thread.sleep(2000);

        // 3. Go back to the previous page
        driver.navigate().back();
        Thread.sleep(2000);

        // 4. Go forward to the next page
        driver.navigate().forward();
        Thread.sleep(2000);

        // 5. Refresh the current page
        driver.navigate().refresh();
        Thread.sleep(2000);

        // Close the browser
        driver.quit();

	}
}
