package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage  {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[contains(@href,'Women')]") private WebElement womenSection;
	@FindBy(xpath="(//a[contains(text(),'Dress')])[1]") private WebElement dress;
	@FindBy(xpath="(//a[contains(text(),'Tops')])[1]") private WebElement tops;
	@FindBy(xpath="//a[contains(text(),'Saree')]") private WebElement saree;					
	
	public void goToWomenSection() {
		womenSection.click();
	}
	public void selectSaree() {
		saree.click();
	}
	public ProductPage goToProductPage() {
		goToWomenSection();
		selectSaree();
		return new ProductPage(driver);
	}
}
