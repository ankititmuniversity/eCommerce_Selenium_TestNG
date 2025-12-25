package baseTest;

import java.time.Duration;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
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

public class BaseTest {
	public WebDriver driver;

	public LoginPage loginPage;
	public HomePage homePage;
	public ProductPage productPage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage; 
	public PaymentPage paymentPage;
	public ViewProductPage viewProductPage;

	protected String browser;
	protected String url;
	protected int timeout;
	protected String screenshotPath;
	protected String reportPath;
	
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);


	@BeforeClass
	public void setUp() {
		browser = ConfigManager.getBrowser();
		url = ConfigManager.getUrl();
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
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	    

		}else {
			System.out.println("Create an object of Driver First");
		}

	}

	@AfterClass
	public void tearDown() {
		logger.info("Closing WebDriver...");
        driver.quit();
        logger.debug("Driver closed successfully.");

	}
}
