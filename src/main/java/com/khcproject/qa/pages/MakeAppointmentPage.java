package com.khcproject.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.khcproject.qa.utils.UtilsQA;

public class MakeAppointmentPage {
	WebDriver driver;
	public MakeAppointmentPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='col-sm-12 text-center']//h2")
	WebElement txt_validLogin;

	@FindBy(name="facility")
	WebElement select_facility;

	@FindBy(name="hospital_readmission")
	WebElement checkbox_hospitalReadmission;

	@FindBy(xpath="//input[@value='Medicaid']")
	WebElement radio_facility_medicaid;

	@FindBy(xpath="//input[@value='Medicare']")
	WebElement radio_facility_medicare;

	@FindBy(xpath="//input[@value='None']")
	WebElement radio_facility_none;

	@FindBy(id="txt_visit_date")
	WebElement txtbox_visitDate;

	@FindBy(id="txt_comment")
	WebElement txtbox_comment;

	@FindBy(xpath="//button[@type='submit']")
	WebElement button_submit;

	public String verifySuccessfulLogin() {
		return txt_validLogin.getText();
	}

	public AppointmentConfirmationPage enterRegistrationDetails(String facility,String program,String date,String comment) 
	{
		UtilsQA.dropDown(driver,select_facility, facility);
		checkbox_hospitalReadmission.click();
		if(program.equalsIgnoreCase("medicaid"))
		{
		radio_facility_medicaid.click();
		}
		else if(program.equalsIgnoreCase("medicare"))
		{
			radio_facility_medicare.click();
		}
		else if(program.equalsIgnoreCase("none"))
		{
			radio_facility_none.click();
		}
		txtbox_visitDate.sendKeys(date);
		txtbox_comment.sendKeys(comment);
		button_submit.click();
		return new AppointmentConfirmationPage(driver);
		}
}