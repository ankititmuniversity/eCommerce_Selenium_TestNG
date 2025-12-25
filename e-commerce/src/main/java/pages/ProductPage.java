package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage  {
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[@class='btn btn-default add-to-cart']") private WebElement addToCart;
	//@FindBy(xpath = "//a[@data-product-id='39']") private WebElement addProduct;
	@FindBy(xpath ="//u[text()='View Cart']") private WebElement viewCart;
	@FindBy(xpath ="//p[contains(text(),'Your product has been added to cart.')]") private WebElement cnfmTextMsg;
	@FindBy(xpath="//button[text()='Continue Shopping']") private WebElement continueShopping;

	public void addItem() {
		try {
			addToCart.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addToCart);
		}
	}
	public String checkMsg() {
		return cnfmTextMsg.getText();
	}
	public void goToCart() {
		try {
			viewCart.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", viewCart);
		}
	}
	public void goToProduct() {
		continueShopping.click();
	}

	public CheckoutPage addProduct() {
		addItem();
		goToCart();
		//checkMsg();
		//goToProduct();
		return new CheckoutPage(driver);
	}

	public CartPage removeItemFromList() {
		addItem();
		checkMsg();
		goToProduct();
		return new CartPage(driver);
	}
}
