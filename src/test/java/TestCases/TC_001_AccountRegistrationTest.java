package TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import PageObjectModel.HomePage;
import PageObjectModel.RegisterPage;
import TestBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Smoke","Master"})
	public void Verify_AccountRegistration() {
		try {
			logger.info("****Started_the_Testcase_001_Registration*********");
			hm = new HomePage(driver);
			// hm.Click_On_Myaccount();
			logger.info("Clicked_on_My_Acoount_Button....");
			hm.Click_On_Register();
			logger.info("Clicked_on_Register_Button....");
			rp = new RegisterPage(driver);
			logger.info("Providing_the_Basic_Information....");
			rp.Enter_FirstName_Field(RandomString().toUpperCase());
			rp.Enter_LastName_Field(RandomString().toUpperCase());
			rp.Enter_Email_Field(RandomString() + "@gmail.com");
			rp.Enter_PhoneNumber_Field("9" + RandomNumber());
			rp.Select_Occupation_Field();
			rp.Click_on_MaleRadioButton();
			String password = RandomAlphaNumeric();
			rp.Enter_Password_Field(password);
			rp.Enter_ConfrimPassword_Field(password);
			rp.Click_on_PrivacyButton();
			rp.Click_on_submitButton();
			// rp.Click_on_NewsPaper();
			// rp.Click_on_PolicyBtn();
			// rp.Click_on_ContiniueBtn();
			logger.info("Clicked on Continue  Button....");
			logger.info("Validating the Success Message ....");
			String msg = rp.Verify_Confirmation_msg();
			assertEquals(msg, "Account Created Successfully");
		} catch (Exception e) {
			logger.error("Test is Failed ");
			logger.debug("Debugs Logs");
			fail();
		}
		logger.info("****Testcase_001_Registration_is_Finished *********");
	}

}
