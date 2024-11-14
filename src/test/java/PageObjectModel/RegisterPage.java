package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "firstName")
	WebElement FirstNameBox;

	@FindBy(id = "lastName")
	WebElement LastNameBox;

	@FindBy(id = "userEmail")
	WebElement EmailBox;
	
	@FindBy(id="userMobile")
	WebElement PhoneNumberBox;

	@FindBy(id = "userPassword")
	WebElement PasswordBox;
	
	@FindBy(id = "confirmPassword")
	WebElement ConfirmPasswordBox;
	
	@FindBy(xpath = "//*[@type='radio' and @value='Male']")
	WebElement MaleRadioBtn;
	
	@FindBy(xpath="//*[@formcontrolname='occupation']")
	WebElement selectBtn;
	
	@FindBy(xpath="//*[@formcontrolname='required']")
	WebElement CheckBox;
	
	@FindBy(xpath="//*[@type='submit']")
	WebElement SubmitBtn;
//	@FindBy(className = "form-check-input")
//	WebElement PolicyBtn;
//	
//	@FindBy(xpath="//button[text()='Continue']")
//	WebElement ContinueBtn;
//	
	@FindBy(xpath="//h1[text()='Account Created Successfully']")
	WebElement VerifyConfirmTxt;

	public void Enter_FirstName_Field(String Fname) {
		FirstNameBox.sendKeys(Fname);
	}

	public void Enter_LastName_Field(String Lname) {
		LastNameBox.sendKeys(Lname);
	}

	public void Enter_Email_Field(String Email) {
		EmailBox.sendKeys(Email);
	}
	public void Enter_PhoneNumber_Field(String Phone) {
		PhoneNumberBox.sendKeys(Phone);
	}
	
	public void Select_Occupation_Field() {
		Select se= new Select(selectBtn);
		se.selectByVisibleText("Engineer");
	}
	
	public void Click_on_MaleRadioButton() {
		MaleRadioBtn.click();
	}
	
	public void Enter_Password_Field(String Password) {
		PasswordBox.sendKeys(Password);
	}
	public void Enter_ConfrimPassword_Field(String Password) {
		ConfirmPasswordBox.sendKeys(Password);
	}
	public void Click_on_PrivacyButton() {
		CheckBox.click();
	}
	public void Click_on_submitButton() {
		SubmitBtn.click();
	}
	
//	public void Click_on_NewsPaper() {
//		NewsBtn.click();
//	}
//	public void Click_on_PolicyBtn() {
//		PolicyBtn.click();
//	}
//	public void Click_on_ContiniueBtn() {
//		ContinueBtn.click();
//	}
	public String Verify_Confirmation_msg() {
		try {
		return (VerifyConfirmTxt.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
		
	}
}
