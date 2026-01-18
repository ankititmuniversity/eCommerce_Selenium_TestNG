package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ViewProductPage  {
	WebDriver driver;
	public ViewProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "(//a[text()='View Product']/parent::li)[1]") private WebElement viewProduct;
	@FindBy(xpath = "//input[@id='name']") private WebElement enterName;
	@FindBy(xpath = "//input[@id='email']") private WebElement enterEmail;
	@FindBy(xpath = "//input[@id='review']") private WebElement reviewComment;
	@FindBy(xpath = "//button[@id='button-review']") private WebElement submitReview;

	public void checkProductDetails() {
		try {
			viewProduct.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", viewProduct);
		}
	}
	public void enterYourName(String name) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", enterName, name);
		enterName.sendKeys(name);
	}
	public void enterYourEmail(String email) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", enterName, email);
		enterEmail.sendKeys(email);
	}
	public void enterYourReviewComment(String reviewMsg) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", enterName, reviewMsg);
		reviewComment.sendKeys(reviewMsg);
	}	
	public void clickSubmitReviewBtn() {
		submitReview.click();
	}
	public ProductPage goBackToProduct(String name,String email,String reviewMsg) {
		checkProductDetails();
		enterYourName(name);
		enterYourEmail(email);
		enterYourReviewComment(reviewMsg);
		clickSubmitReviewBtn();
		return new ProductPage(driver);
	}

}
