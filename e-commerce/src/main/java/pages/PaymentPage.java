package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@name='name_on_card']") private WebElement nameOnCard;
	@FindBy(xpath="//input[@name='card_number']") private WebElement cardNumber;
	@FindBy(xpath="//input[@name='cvc']") private WebElement cvc ;
	@FindBy(xpath="//input[@name='expiry_month']") private WebElement expiaryMonth;
	@FindBy(xpath="//input[@name='expiry_year']") private WebElement expiaryYear;
	@FindBy(xpath="//button[text()='Pay and Confirm Order']") private WebElement payCnfBtn;
	@FindBy(xpath="//p[text()='Congratulations! Your order has been confirmed!']") private WebElement orderPlaced;
	@FindBy(xpath ="//a[@href='/logout']") private WebElement logout;
	
	public void enterNameOnCard(String name) {
		nameOnCard.sendKeys(name);
	}
	public void enterCardNumber(String cardNum) {
		cardNumber.sendKeys(cardNum);
	}
	public void enterCVV(String cvcNum) {
		cvc.sendKeys(cvcNum);
	}
	public void expMonth(String month) {
		expiaryMonth.sendKeys(month);
	}
	public void expYear(String year) {
		expiaryYear.sendKeys(year);
	}
	public void confirmPaymenet() {
		try {
			payCnfBtn.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", payCnfBtn);
		}

	}

	public String orderConfirmation() {
		return orderPlaced.getText();
	}
	public void clickLogoutBtn() {
		logout.click();
	}
	public void payWithCard(String name,String cardNum,String cvv,String mon,String yr) {
		enterNameOnCard(name);
		enterCardNumber(cardNum);
		enterCVV(cvv);
		expMonth(mon);
		expYear(yr);
		confirmPaymenet();
		System.out.println(orderConfirmation());
		clickLogoutBtn();
	}
	
}
