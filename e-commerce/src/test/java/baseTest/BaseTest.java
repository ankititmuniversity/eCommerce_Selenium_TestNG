package baseTest;

import java.time.Duration;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ProductPage;
import pages.ViewProductPage;
import utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
	public static WebDriver driver;
	public LoginPage loginPage;
	public HomePage homePage;
	public ProductPage productPage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage; 
	public PaymentPage paymentPage;
	public ViewProductPage viewProductPage;

	public String browser;
	public String url;
	public String email;
	public String pwd;
	public int timeout;
	public String screenshotPath;
	public String reportPath;
	public static final Logger logger = LogManager.getLogger(BaseTest.class);


	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		browser = ConfigManager.getBrowser();
		url = ConfigManager.getUrl();
		email = ConfigManager.getEmail();
		pwd = 	ConfigManager.getPwd();	
		timeout = ConfigManager.getTimeout();
		screenshotPath = ConfigManager.getScrrenshotPath();
		reportPath = ConfigManager.getReportPath();

		switch(browser) {
		case "chrome" : WebDriverManager.chromedriver().setup();
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--incognito"); 
						//options.addArguments("--headless=new");
						logger.info("Initializing WebDriver...");
						driver = new ChromeDriver(options);
						logger.debug("Driver initialized: {}", driver);
						break;
		case "edge"  :  WebDriverManager.edgedriver().setup();
						logger.info("Initializing WebDriver...");
						driver = new EdgeDriver();
						logger.debug("Driver initialized: {}", driver);
						break;	
		case "firefox": WebDriverManager.firefoxdriver().setup();
					logger.info("Initializing WebDriver...");				
					driver = new FirefoxDriver();
					logger.debug("Driver initialized: {}", driver);
					break;	
		default : 		driver = null;    				
		}

		if(driver!=null) {
			//context.setAttribute("driver", driver);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	    

		}else {
			System.out.println("Create an object of Driver First");
		}

	}

	public String captureScreenshot(String testName) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "-" + timeStamp + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshotPath);
		FileUtils.copyFile(src, dest);
		return screenshotPath;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		logger.info("Closing WebDriver...");
		driver.quit();
		logger.debug("Driver closed successfully.");

	}
}
