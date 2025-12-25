package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
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
	

}
