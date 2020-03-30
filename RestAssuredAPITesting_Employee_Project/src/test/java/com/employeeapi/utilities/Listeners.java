package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReports.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Rest Assured API Testing");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name","Employee API");
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Gururaj Kanni");
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "TEST CASE PASSED IS"+result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL,"TEST CASE FAILED IS "+ result.getName());
		test.log(Status.FAIL,"TEST CASE FAILED IS "+result.getThrowable());
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.SKIP,"TEST CASE SKIPPED IS "+ result.getName());
		//test.log(Status.FAIL,"TEST CASE FAILED IS "+result.getThrowable());
		
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	

}
