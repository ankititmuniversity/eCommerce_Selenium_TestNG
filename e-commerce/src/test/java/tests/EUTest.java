package tests;

import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ProductPage;

public class EUTest extends BaseTest {
	@Test
	public void buyProduct() {
		logger.info("Initial Set up Done");
		LoginPage loginPage = new LoginPage(driver);
		logger.info("Login is working as expected");
		HomePage homePage = loginPage.login(email,pwd );
		logger.info("HomePage object is created and User successfully logged in");
		ProductPage productPage = homePage.goToProductPage();
		logger.info("ProductPage object is created and User successfully added product in cart.");
		CheckoutPage checkoutPage = productPage.addProduct();
		logger.info("CheckoutPage object is created and User successfully added product in cart.");
		CartPage cartPage = checkoutPage.proceedToCheckout(); 
		logger.info("CartPage object is created and User successfullyproceed to checkout.");
		PaymentPage paymentPage = checkoutPage.makePayment("Hello");
		paymentPage.payWithCard("Mohan Raj","1234567887654321","123", "12","2030");
		logger.info("PaymentPage object is created and User successfully made Payment");
	}

}
