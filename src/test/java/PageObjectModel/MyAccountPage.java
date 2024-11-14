package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	@FindBy(xpath="//*[text()=' HOME ']")
	WebElement HomeBtn;
	
	@FindBy(xpath="//*[text()=' Sign Out ']")
	WebElement SignOutBtn;
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean Verify_HomeBtn() {
		return HomeBtn.isDisplayed();
	}
	public boolean Verify_SignOutBtn() {
		return SignOutBtn.isDisplayed();
	}
	public void Click_On_SignOutBtn() {
		SignOutBtn.click();;
	}
	
}
