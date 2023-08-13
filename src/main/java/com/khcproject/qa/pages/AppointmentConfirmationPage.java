package com.khcproject.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentConfirmationPage 
{
	WebDriver driver;

	public AppointmentConfirmationPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//p[@id='facility']")
	WebElement txt_facility;
	
	@FindBy(xpath="//p[@id='program']")
	WebElement txt_program;
	
	@FindBy(xpath="//p[@id='visit_date']")
	WebElement txt_visitDate;
	
	@FindBy(xpath="//p[@id='comment']")
	WebElement txt_comment;
	
	public String verifyAppointmentConfirmationFacility()
	{
		return txt_facility.getText();
	}
	
	public String verifyAppointmentConfirmationProgram()
	{
		return txt_program.getText();
	}
	
	public String verifyAppointmentConfirmationVisitDate()
	{
		return txt_visitDate.getText();
	}
	
	public String verifyAppointmentConfirmationComment()
	{
		return txt_comment.getText();
	}
	
	
}
