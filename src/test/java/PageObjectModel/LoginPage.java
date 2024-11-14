package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	@FindBy(id = "userEmail")
	WebElement EmailField;
	
	
	@FindBy(id = "userPassword")
    WebElement PasswordField;
	
	@FindBy(id = "login")
    WebElement LoginBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void Enter_Email_Field(String Email) {
		EmailField.clear();
		EmailField.sendKeys(Email);
	}
	
	public void Enter_Password_Field(String password) {
		PasswordField.clear();
		PasswordField.sendKeys(password);
	}
	public void Click_On_Login_Btn() {
		LoginBtn.click();
	}

}
