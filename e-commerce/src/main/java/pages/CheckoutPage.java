package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@class,'close-checkout')]") private WebElement cancelCheckout;
	@FindBy(xpath ="//table[@class='table table-condensed']") private WebElement preCheckOutView;
	@FindBy(xpath ="//textarea[@name='message']") private WebElement giveComment;
	@FindBy(xpath ="//a[text()='Place Order']") private WebElement placeOrder;
	@FindBy(xpath="//a[@href='/view_cart']") private WebElement clickCart;
	@FindBy(css="i.fa.fa-times") WebElement removeItem;
	@FindBy(xpath ="//a[text()='Proceed To Checkout']") private WebElement proceedToCheckout;
	public void clickOnCart() {
		clickCart.click();
	}
	public void cancelItem() {
		removeItem.click();
	}
	public void clickCheckout() {
		proceedToCheckout.click(); 
	}
	public void undoCheckout() {
		cancelCheckout.click();
	}

	public void preCheckoutReview() {
		preCheckOutView.click();
	}
	public void giveYourComment(String msg) {
		giveComment.sendKeys(msg);
	}
	public void placeOrders() {
		try {
			placeOrder.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", placeOrder);
		}
	}
	
	public CartPage proceedToCheckout() {
		clickCheckout();
		return new CartPage(driver);
	}
	public PaymentPage makePayment(String msg) {
//		undoCheckout();
//		preCheckoutReview();
//		giveYourComment(msg);	 
		placeOrders();
		return new PaymentPage(driver);
	}

}

