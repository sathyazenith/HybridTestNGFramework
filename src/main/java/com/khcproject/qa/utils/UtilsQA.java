package com.khcproject.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class UtilsQA {


	public static String generateEmailWithTimeStamp() 
	{
		Date date =new Date();
		String timeStamp =date.toString().replace(" ","_").replace(":","_");
		return "email_"+timeStamp+"@gmail.com";
	}

	public static void dropDown(WebDriver driver,WebElement webElement,String Value) {
		Select variable =new Select(webElement);
		variable.selectByVisibleText(Value);
	}
	
public static String captureScreenshot(WebDriver driver,String testName) {
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		
		try {
			FileHandler.copy(srcScreenshot,new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
}

	public static Object[][] retrieveExcelData(String sheetName)
	{
		XSSFWorkbook workbook=null;
		
		File excelFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\khcproject\\qa\\testdata\\testdata.xlsx");
		try {
		FileInputStream fisExcel = new FileInputStream(excelFile);
		workbook = new XSSFWorkbook(fisExcel);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rows][columns];

		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);

			for(int j=0;j<columns;j++)		
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch(cellType)
				{
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				default:
					data[i][j]=cell.getStringCellValue();
					break;
				}
			}
		}
		return data;
	}
	}
