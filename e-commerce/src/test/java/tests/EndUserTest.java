package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ProductPage;
import pages.ViewProductPage;

public class EndUserTest extends BaseTest {

	@Test(groups="smoke")
	public void buyProduct() throws InterruptedException {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	    productPage = new ProductPage(driver);
	    cartPage = new CartPage(driver);
	    checkoutPage = new CheckoutPage(driver);		    
	    paymentPage = new PaymentPage(driver);
	    viewProductPage = new ViewProductPage(driver);
		
		loginPage.loginSignupLink();
		loginPage.enterYourEmail(email);
		loginPage.enterYourPassword(pwd);
		loginPage.clickLoginBtn();
		
		Thread.sleep(5000);
		homePage.goToWomenSection();
		homePage.selectSaree();
		
		productPage.addItem();
		System.out.println(productPage.checkMsg());
		productPage.goToCart();
		
		cartPage.clickOnCart();
		cartPage.clickCheckout();
		
		checkoutPage.giveYourComment("I am happy With my Shopping experience.");
		checkoutPage.placeOrders();
		
		paymentPage.enterNameOnCard("Mohan Raj");
		paymentPage.enterCardNumber("1234567890123456");
		paymentPage.enterCVV("123");
		paymentPage.expMonth("11");
		paymentPage.expYear("2029");
		paymentPage.confirmPaymenet();
		System.out.println(paymentPage.orderConfirmation());
		paymentPage.clickLogoutBtn();
	}
}
