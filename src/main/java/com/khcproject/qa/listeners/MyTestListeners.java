package com.khcproject.qa.listeners;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.khcproject.qa.utils.ExtentReporter;
import com.khcproject.qa.utils.UtilsQA;

public class MyTestListeners implements ITestListener

{
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) 
	{
		extentReport = ExtentReporter.generateExtentReport();
	}	


	@Override
	public void onTestStart(ITestResult result) 
	{
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.PASS,result.getName()+" got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		WebDriver driver = null;

		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		String destinationScreenshotPath = UtilsQA.captureScreenshot(driver,result.getName());

		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
	}



	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();

		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
