package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void createEmplyee() throws InterruptedException
	{
		logger.info("******* Started TC003_Post_Employee_Record *****");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		//JSON Object is a class that represnts a simple JSON. We can add Key-Value pair using the put method
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		//Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		
		//Add the jsonto the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/create");
		
		Thread.sleep(4000);		
			
		
	}
	
	@Test
	void checkResponeBody()
	{
		String responseBody= response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empSalary),true);
		Assert.assertEquals(responseBody.contains(empAge),true);
	}

	@Test
	void checkStatusCode()
	{
		int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode,200);
	}
	
	@Test
	void checkStatusLine()
	{
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}

	
	@AfterClass
	void tearDown()
	{
		logger.info("******* TC002_TC003_Post_Employee_Record *******");
	}
}

