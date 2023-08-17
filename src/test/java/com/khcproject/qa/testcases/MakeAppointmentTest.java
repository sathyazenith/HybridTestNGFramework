package com.khcproject.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.khcproject.qa.base.Base;
import com.khcproject.qa.pages.AppointmentConfirmationPage;
import com.khcproject.qa.pages.MakeAppointmentPage;
import com.khcproject.qa.testdata.DataSupplier;
import com.khcproject.qa.utils.UtilsQA;

public class MakeAppointmentTest extends Base {
	public WebDriver driver;
	MakeAppointmentPage	mp;
	AppointmentConfirmationPage acp;
	public MakeAppointmentTest()
	{
		super();
	}
	
	@Test(enabled=false)
	public void makeAppointment() 
	{
		mp=lp.loginWithValidCredentials(prop.getProperty("username"),prop.getProperty("password"));
		acp=mp.enterRegistrationDetails("Hongkong CURA Healthcare Center", "Medicaid", "14/10/2023", "Appointment booked for "+UtilsQA.generateEmailWithTimeStamp());			
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(acp.verifyAppointmentConfirmationFacility(),"Hongkong CURA Healthcare Center" );
		sa.assertEquals(acp.verifyAppointmentConfirmationProgram(), "Medicaid");
		sa.assertEquals(acp.verifyAppointmentConfirmationVisitDate(), "14/10/2023");
		sa.assertEquals(acp.verifyAppointmentConfirmationComment(),"Appointment booked for "+UtilsQA.generateEmailWithTimeStamp());
		sa.assertAll("Validations are successful");

	}

	@Test(priority=1,dataProvider="validDataSupplier",dataProviderClass=DataSupplier.class,enabled=false)
	public void makeAppointmentUsingDataDriven(String facility,String program,String date,String comment) 
	{
	mp=lp.loginWithValidCredentials(prop.getProperty("username"),prop.getProperty("password"));
	acp=mp.enterRegistrationDetails(facility, program, date, comment);
	SoftAssert sa = new SoftAssert();
	sa.assertEquals(acp.verifyAppointmentConfirmationFacility(), facility);
	sa.assertEquals(acp.verifyAppointmentConfirmationProgram(), program);
	sa.assertEquals(acp.verifyAppointmentConfirmationVisitDate(), date);
	sa.assertEquals(acp.verifyAppointmentConfirmationComment(), comment);
	sa.assertAll("Validations are successful");
	}

	@Test(priority=1,dataProvider="ExcelDataSupplier",dataProviderClass=DataSupplier.class)
	public void makeAppointmentUsingExcelDataDriven(String facility,String program,String date,String comment)  
	{
		mp=lp.loginWithValidCredentials(prop.getProperty("username"),prop.getProperty("password"));
		acp=mp.enterRegistrationDetails(facility, program, date, comment);	
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(acp.verifyAppointmentConfirmationFacility(), facility);
		sa.assertEquals(acp.verifyAppointmentConfirmationProgram(), program);
		sa.assertEquals(acp.verifyAppointmentConfirmationVisitDate(), date);
		sa.assertEquals(acp.verifyAppointmentConfirmationComment(), comment);
		sa.assertAll("Validations are successful");
	}
}