package com.khcproject.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@id='txt-username']")
	private WebElement txtbox_username;

	@FindBy(xpath="//input[@id='txt-password']")
	private WebElement txtbox_password;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement button_submit;

	@FindBy(xpath="//p[@class='lead text-danger']")
	private WebElement txt_invalidLogin;

	public MakeAppointmentPage loginWithValidCredentials(String username,String password)
	{
		txtbox_username.sendKeys(username);
		txtbox_password.sendKeys(password);
		button_submit.click();
		return new MakeAppointmentPage(driver);
	}

	public String loginWithInvalidCredentials(String username,String password)
	{
		txtbox_username.sendKeys(username);
		txtbox_password.sendKeys(password);
		button_submit.click();
		return txt_invalidLogin.getText();
	}

}