package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("*******Started TC002_Get_Single_Employee_Record *****");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee/"+empID);
		
		Thread.sleep(200);
	}

	@Test
	void checkResponseBody()
	{
		logger.info("*********Checking Response Body *****");
		
		String responseBody=response.getBody().asString();
		logger.info("Response body :"+responseBody);
		Assert.assertEquals(responseBody.contains(empID),true);
		
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("*********Checking Status Code *****");
		
		int statusCode=response.getStatusCode();
		logger.info("Status Code is :"+statusCode);
		Assert.assertEquals(statusCode,200);
		
	}
	@Test
	void checkResponseTime()
	{
		logger.info("*********Checking Response Time *****");
		
		long responseTime=response.getTime();
		logger.info("Response Time is :"+responseTime);
		
		if(responseTime>9000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime<9000);	
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("******* TC002_Get_Single_Employee_Record*******");
	}
}
