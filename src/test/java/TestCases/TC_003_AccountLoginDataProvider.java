package TestCases;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import PageObjectModel.MyAccountPage;
import TestBase.BaseClass;
import Utilities.DataProviderClass;

public class TC_003_AccountLoginDataProvider extends BaseClass {
	@Test(dataProvider="LoginData",dataProviderClass=DataProviderClass.class,groups= {"DataDriven","Master"})
	public void AccountloginTest(String Email,String Password,String Result) {
	
	logger.info("****Started_TestCase_003_Account_login_with_DataProvider*****");
	try {
	logger.info("Enter_Credentails_to_login");
	
	hm = new HomePage(driver);
	lp= new LoginPage(driver);
	lp.Enter_Email_Field(Email);
	lp.Enter_Password_Field(Password);
	logger.info("Click_on_SignIn_Button_to_Login");
	lp.Click_On_Login_Btn();
	
	mp = new MyAccountPage(driver);
	logger.info("Verify_the_Home_Button_is_Displayed_Or_Not");
	
	boolean check_HomeBtn = mp.Verify_HomeBtn();
	if(Result.equalsIgnoreCase("Valid")) {
		if(check_HomeBtn==true) {
			logger.info("Click_the_SignOut_Buuton_to_LogOut_of_Account");
			mp.Click_On_SignOutBtn();
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
	if(Result.equalsIgnoreCase("InValid")) {
        if(check_HomeBtn==true) {
        	logger.info("Click_the_SignOut_Buuton_to_LogOut_of_Account");
			mp.Click_On_SignOutBtn();
			assertTrue(false);
		}
		else {
			assertTrue(true);
			fail();
		}
	}
	}catch(Exception e) {
		fail();
	}
	
	logger.info("****TestCase_003_Account_login_with_DataProvider_is_Finished*****");
	
	}
}
