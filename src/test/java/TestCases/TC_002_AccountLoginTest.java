package TestCases;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import PageObjectModel.MyAccountPage;
import TestBase.BaseClass;

public class TC_002_AccountLoginTest extends BaseClass {

	@Test(groups= {"Regression","Master"})
	public void verifyAccountLogin() {
		try {
			logger.info("****Started_TestCase_002_Account_login*****");
			logger.info("Enter_Valid_Credentails_to_login");
			hm = new HomePage(driver);
			lp= new LoginPage(driver);
			lp.Enter_Email_Field(p.getProperty("Email"));
			lp.Enter_Password_Field(p.getProperty("Password"));
			logger.info("Click_on_SignIn_Button_to_Login");
			lp.Click_On_Login_Btn();
			mp = new MyAccountPage(driver);
			logger.info("Verify_the_Home_Button_is_Displayed_Or_Not");
			boolean check_HomeBtn = mp.Verify_HomeBtn();
			assertTrue(check_HomeBtn);
			logger.info("Verify_the_SignOut_Button_is_Displayed_Or_Not");
			boolean check_SignOut = mp.Verify_SignOutBtn();
			assertTrue(check_SignOut);
			logger.info("Click_the_SignOut_Buuton_to_LogOut_of_Account");
			mp.Click_On_SignOutBtn();

		} catch (Exception e) {
			logger.error("Test is Failed ");
			logger.debug("Debugs Logs");
			fail();
		}
		logger.info("***TestCase_002_Account_login_is_Completed****");

	}
}
