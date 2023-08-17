package com.khcproject.qa.testdata;

import org.testng.annotations.DataProvider;

import com.khcproject.qa.utils.UtilsQA;

public class DataSupplier {

	@DataProvider(name="validDataSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data= {{"Hongkong CURA Healthcare Center","Medicaid","01/09/2023","Appointment Booked!"},
		{"Tokyo CURA Healthcare Center","Medicare","11/11/2023","Appointment Booked!"},
		{"Seoul CURA Healthcare Center","None","14/12/2023","Appointment Booked!"}};
		return data;
	}
	
	@DataProvider(name="ExcelDataSupplier")
	public Object[][] supplyExcelTestData()
	{
		Object[][] data= UtilsQA.retrieveExcelData("MakeAppointment"); 
		return data;
	}
	
}
