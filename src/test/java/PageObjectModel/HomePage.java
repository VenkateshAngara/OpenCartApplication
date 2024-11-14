package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//@FindBy(xpath = "//span[text()='My Account']")
	//WebElement MyaccountBtn;

	//@FindBy(xpath = "//a[text()='Login']")
	//WebElement LoginBtn;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement RegisterBtn;

	public HomePage(WebDriver driver) {
		super(driver);
	}

//	public void Click_On_Myaccount() {
//		MyaccountBtn.click();
//	}
//
//	public void Click_On_Login() {
//		LoginBtn.click();
//	}

	public void Click_On_Register() {
		RegisterBtn.click();
	}

}
