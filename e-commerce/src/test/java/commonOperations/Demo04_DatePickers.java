package commonOperations;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo04_DatePickers {
	@Test
	public void datePickers() {
		// User-provided date string (adjust format as needed)
		String inputDate = "01/02/2026";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		
	

		WebElement datePicker1 = driver.findElement(By.xpath("//input[@id='datepicker']"));			
		datePicker1.click();  
		datePicker1.clear();  
		datePicker1.sendKeys("01/17/2025"); 

		WebElement datePicker2 = driver.findElement(By.xpath("//input[@id='txtDate']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].value='17/01/2026';",datePicker2);

		//		    ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly')", datePicker2);
		//		    datePicker2.sendKeys("17/01/2029"); 

		String startDate = "20-12-2026";
		String endDate =  "15-12-2026";

		WebElement startInput = driver.findElement(By.id("start-date"));
		WebElement endInput = driver.findElement(By.id("end-date"));

		startInput.sendKeys(startDate);
		endInput.sendKeys(endDate);

		//driver.quit();

	}
}
