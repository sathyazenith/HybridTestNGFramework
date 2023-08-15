package com.khcproject.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.khcproject.qa.base.Base;
import com.khcproject.qa.pages.MakeAppointmentPage;

public class LoginTest extends Base{
	public WebDriver driver;
	MakeAppointmentPage mp;

	public LoginTest()
	{
	super();	
	}
	
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() 
	{
		mp = lp.loginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(mp.verifySuccessfulLogin().contains(prop.getProperty("SuccessfullLoginHeader")),"Failed to Login!Check the Credentials!");
	}

	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() 
	{
		Assert.assertTrue(lp.loginWithInvalidCredentials("Sample","password").contains(prop.getProperty("InvalidLoginHeader")),"Unable to validate Invalid Login as the expected message is not displayed");
	}
}