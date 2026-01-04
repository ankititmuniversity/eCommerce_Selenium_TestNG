package commonOperations;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo04_FormPart2 {
	@Test
	public void formPart2() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebElement input1 = driver.findElement(By.id("input1"));
		input1.sendKeys("Hi I am Ankit, and I amn QA Lead.");
		driver.findElement(By.id("btn1")).click();

		WebElement input2 = driver.findElement(By.id("input2"));
		input2.sendKeys("I work for Pvt Ltd Companmy.");
		driver.findElement(By.id("btn2")).click();

		//Handling footer
		WebElement footer = driver.findElement(By.xpath("(//div[@class='widget-content'])[19]/ul"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
		WebElement privacyLink = driver.findElement(By.linkText("Hidden Elements & AJAX"));
		privacyLink.click();


		// Handling AJAX 
		// Wait for input boxes to be visible and interact
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement ip1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input1")));
		ip1.clear();
		ip1.sendKeys("Value for Input 1");
		driver.findElement(By.id("toggleInput")).click();
		WebElement ip2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input2")));
		ip2.clear();
		ip2.sendKeys("Value for Input 2");

		// Handle visible checkbox
		WebElement checkbox1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkbox1")));
		if (!checkbox1.isSelected()) checkbox1.click();

		// Handle hidden checkbox using JavaScript
		WebElement checkbox2 = driver.findElement(By.id("checkbox2"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.display='block';", checkbox2); // Make it visible
		if (!checkbox2.isSelected()) checkbox2.click();

		//Static Table 
		WebElement staticTable = driver.findElement(By.name("BookTable"));
		List<WebElement> rows = staticTable.findElements(By.tagName("tr"));

		for(WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("th"));
			if(cells.isEmpty()) {
				cells = row.findElements(By.tagName("td")); // fallback for data rows
			}
			for(WebElement cell : cells) {
				System.out.print(cell.getText() + " | ");
			}
			System.out.println();
		}

		//Dynamic Table
		WebElement dynamicTable = driver.findElement(By.id("taskTable"));
		WebElement dRow = dynamicTable.findElement(By.xpath("//tr[@id='headers']"));
		List<WebElement> dCells = dRow.findElements(By.tagName("th"));
		for(WebElement cell : dCells) {
			System.out.print(cell.getText() + " | ");
		}
		System.out.println();

		List<WebElement> dRows = dynamicTable.findElements(By.tagName("tr"));
		for(WebElement row : dRows) {
			List<WebElement> dynamicCells = row.findElements(By.tagName("td"));
			for(WebElement dynamicCell :dynamicCells ) {
				System.out.print(dynamicCell.getText() + " | ");
			}
			System.out.println();
		}

		//Pagination Table 
		WebElement page2Link = driver.findElement(By.xpath("//ul[@id='pagination']//a[text()='2']"));
		page2Link.click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='productTable']/tbody/tr")));
		
		WebElement paginationTable = driver.findElement(By.id("productTable"));
		List<WebElement> prows = paginationTable.findElements(By.xpath(".//tbody/tr"));

		for (WebElement row : prows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));

		    for (int i = 0; i < cells.size(); i++) {
		        WebElement cell = cells.get(i);

		        // If it's the last column (Select), check for checkbox
		        if (i == cells.size() - 1) {
		            List<WebElement> checkboxes = cell.findElements(By.xpath(".//input[@type='checkbox']"));
		            if (!checkboxes.isEmpty()) {
		                checkboxes.get(0).click(); // Select the checkbox
		                System.out.print("[Checkbox Selected] | ");
		            } else {
		                System.out.print("[No Checkbox] | ");
		            }
		        } else {
		            System.out.print(cell.getText() + " | ");
		        }
		    }
		    System.out.println();
		}

		driver.quit();

	}
}
