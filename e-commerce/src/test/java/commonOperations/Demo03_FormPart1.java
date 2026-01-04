package commonOperations;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo03_FormPart1 {
	@Test
	public void submitForm() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Select CheckBox			
		driver.findElement(By.xpath("//input[@id='sunday']")).click();
		driver.findElement(By.xpath("//input[@id='monday']")).click();

		//Select Multiple Options
		WebElement country = driver.findElement(By.id("colors")); 
		Select select = new Select(country);
		select.selectByIndex(1);
		select.selectByVisibleText("Green");
		select.selectByValue("yellow");

		//Select and Print drop down Text Value
		List<WebElement> countries = driver.findElements(By.xpath("//select[@id='country']/option"));
		for(WebElement countryName : countries) {
			System.out.println("Name of the Country is : "+countryName.getText());
		}

		//Select Date from Different type of Date Picker
		String inputDate = "01/02/2026";
		WebElement datePicker1 = driver.findElement(By.xpath("//input[@id='datepicker']"));			
		datePicker1.click();  
		datePicker1.clear();  
		datePicker1.sendKeys("01/17/2025"); 

		WebElement datePicker2 = driver.findElement(By.xpath("//input[@id='txtDate']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].value='17/01/2026';",datePicker2);

		String startDate = "20-12-2026";
		String endDate =   "25-12-2026";

		WebElement startInput = driver.findElement(By.id("start-date"));
		WebElement endInput = driver.findElement(By.id("end-date"));

		startInput.sendKeys(startDate);
		endInput.sendKeys(endDate);

		//Switching Tabs
		driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys("Modi");
		driver.findElement(By.xpath("//input[@class='wikipedia-search-button']")).click();
		driver.findElement(By.xpath("//a[text()='More »']")).click();

		List<String> tabs = new ArrayList<>(driver.getWindowHandles());

		// Switch to the newly opened tab (index 1)
		driver.switchTo().window(tabs.get(1));
		System.out.println("In new tab: " + driver.getTitle());
		driver.close();
		driver.switchTo().window(tabs.get(0));
		System.out.println("Back to parent tab: " + driver.getTitle());

		//Dynamic Button 
		driver.findElement(By.xpath("//button[@name='start']")).click();
		driver.findElement(By.xpath("//button[@name='stop']")).click();

		//Alerts 
		driver.findElement(By.id("alertBtn")).click();
		String alertMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		driver.findElement(By.id("confirmBtn")).click();
		String cnfMsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().dismiss();

		driver.findElement(By.id("promptBtn")).click();
		driver.switchTo().alert().sendKeys("Hi Harry Poter");
		driver.switchTo().alert().accept();

		//Pop Up windows Handling
		driver.findElement(By.id("PopUp")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				System.out.println("Switched to child window: " + driver.getTitle());
				driver.close();
				driver.switchTo().window(parentWindow);
				System.out.println("Back to parent window: " + driver.getTitle());
			}
		}	
		//Action Class
		Actions action = new Actions(driver);
		WebElement pointMe = driver.findElement(By.className("dropbtn"));
		WebElement mobiles = driver.findElement(By.xpath("//a[text()='Mobiles']"));		
		action.moveToElement(pointMe).build().perform();
		action.moveToElement(mobiles).click().perform();
		
		WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Copy Text']"));
		action.doubleClick(doubleClick).perform();
		
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		action.dragAndDrop(source, target).perform();
		
		//Slider 
		WebElement handle1 = driver.findElements(By.cssSelector("#slider-range .ui-slider-handle")).get(0);
        WebElement handle2 = driver.findElements(By.cssSelector("#slider-range .ui-slider-handle")).get(1);

        Actions actions = new Actions(driver);
        // Move first handle to the right by 50 pixels
        actions.clickAndHold(handle1).moveByOffset(50, 0).release().perform();
        // Move second handle to the left by 30 pixels
        actions.clickAndHold(handle2).moveByOffset(-30, 0).release().perform();

        //SVG elements handling
        WebElement svgElement = driver.findElement(By.xpath("//*[local-name()='svg']//*[local-name()='circle']"));
        actions.moveToElement(svgElement).click().perform();
        String radius = svgElement.getAttribute("r");
        String color = svgElement.getAttribute("fill");
        System.out.println("Radius is: "+radius+";"+" Color is: "+color);
        
        //Scrolling Drop Down handling without select tag
        WebElement scrollingDD = driver.findElement(By.id("comboBox"));
        scrollingDD.click();
        WebElement selectItem = driver.findElement(By.xpath("//div[text()='Item 100']"));
        actions.moveToElement(selectItem).click().perform();
        
        //Broken link counting
        List<WebElement> links = driver.findElements(By.tagName("a"));
        int brokenCount = 0;

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) continue;

            try {
            	
            	URL urlObject = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
                conn.setRequestMethod("HEAD");
                conn.connect();
                int status = conn.getResponseCode();

                if (status >= 400) {
                    System.out.println("Broken link: " + url + " → Status: " + status);
                    brokenCount++;
                }
            } catch (Exception e) {
                System.out.println("Error checking link: " + url);
                brokenCount++;
            }
        }

        System.out.println("Total broken links: " + brokenCount);

        //Shadow DOM element Handling
        // Step 1: Get shadow root of #shadow_host
        WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // Step 2: Get 'Mobiles' text from #shadow_content
        WebElement mobilesText = shadowRoot.findElement(By.cssSelector("#shadow_content .info"));
        System.out.println("Mobiles text: " + mobilesText.getText());

        // Step 3: Get nested shadow root of #nested_shadow_host
        WebElement nestedHost = shadowRoot.findElement(By.cssSelector("#nested_shadow_host"));
        SearchContext nestedRoot = nestedHost.getShadowRoot();

        // Step 4: Get 'Laptops' text from #nested_shadow_content
        WebElement laptopsText = nestedRoot.findElement(By.cssSelector("#nested_shadow_content div"));
        System.out.println("Laptops text: " + laptopsText.getText());

        // Step 5: Click on Blog link (outside shadow DOM)
        WebElement blogLink = driver.findElement(By.linkText("Blog"));
        blogLink.click();
        driver.navigate().back();
        
        //Chossing File 
        String filePath = "C:\\Users\\ankit\\OneDrive\\Desktop\\Diagram showing Jenk.png";
        WebElement chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
        chooseFile.sendKeys(filePath);
        String uploadedFile = chooseFile.getAttribute("value");
        System.out.println("Uploaded file: " + uploadedFile);
        
        //Form Fields Handling
        


        
        
        
		// driver.quit();


	}
}
