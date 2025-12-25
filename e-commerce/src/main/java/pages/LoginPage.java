package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[@href='/login']/parent::li") private WebElement login;
	@FindBy(xpath ="//input[@name='email']") private WebElement emailId;
	@FindBy(xpath ="//input[@name='password']") private WebElement password ;
	@FindBy(xpath ="//button[@data-qa='login-button']") private WebElement loginBtn;


	public void loginSignupLink() {
		login.click();
	}
	public void enterYourEmail(String email) {
		emailId.sendKeys(email);
	}
	public void enterYourPassword(String pawd) {
		password.sendKeys(pawd);
	}
	public void clickLoginBtn() {
		loginBtn.click();
	}	
	
	public HomePage login(String email, String pwd) {
        loginSignupLink();
        enterYourEmail(email);
        enterYourPassword(pwd);
        clickLoginBtn();
        return new HomePage(driver);
    }

}
