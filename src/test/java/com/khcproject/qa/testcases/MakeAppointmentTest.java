package com.khcproject.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.khcproject.qa.base.Base;
import com.khcproject.qa.pages.AppointmentConfirmationPage;
import com.khcproject.qa.pages.LoginPage;
import com.khcproject.qa.pages.MakeAppointmentPage;
import com.khcproject.qa.utils.UtilsQA;

public class MakeAppointmentTest extends Base {
	public WebDriver driver;
	LoginPage lp;
	MakeAppointmentPage	mp;
	AppointmentConfirmationPage acp;
	public MakeAppointmentTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver= browserInitialization(prop.getProperty("browser"));
	lp=new LoginPage(driver);
	}

	@AfterMethod
	public void tearDown() 
	{
		driver.quit();
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

	@DataProvider(name="validDataSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data= {{"Hongkong CURA Healthcare Center","Medicaid","01/09/2023","Appointment Booked!"},
		{"Tokyo CURA Healthcare Center","Medicare","11/11/2023","Appointment Booked!"},
		{"Seoul CURA Healthcare Center","None","14/12/2023","Appointment Booked!"}};
		return data;
	}
	
	@Test(priority=1,dataProvider="validDataSupplier",enabled=false)
	public void makeAppointmentUsingDataDriven(String facility,String program,String date,String comment) throws InterruptedException 
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

	@DataProvider(name="ExcelDataSupplier")
	public Object[][] supplyExcelTestData()
	{
		Object[][] data= UtilsQA.retrieveExcelData("MakeAppointment"); 
		return data;
	}
	
	@Test(priority=1,dataProvider="ExcelDataSupplier")
	public void makeAppointmentUsingExcelDataDriven(String facility,String program,String date,String comment) throws InterruptedException 
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
