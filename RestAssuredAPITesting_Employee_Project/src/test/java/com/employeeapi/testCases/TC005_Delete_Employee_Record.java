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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	String empName = RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge =RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		logger.info("****** Started TC005_Delete_Employee_Record ****");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
	
		response = httpRequest.request(Method.GET,"/employees");
		
		//First get the JsonPath object instance from the  Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//Capture id
		String empID = jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE,"/delete/"+empID);
		
		Thread.sleep(2000);
	}
			
			@Test
			void checkResponeBody()
			{
				String responseBody= response.getBody().asString();
				System.out.println("Response body is:"+responseBody);
				Assert.assertEquals(responseBody.contains("Successfully! deleted Records"),true);
			}

			@Test
			void checkStatusCode()
			{
				int statusCode= response.getStatusCode();
				System.out.println("Status Code is :"+statusCode);
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
				logger.info("******* TC005_Delete_Employee_Record *******");
			}


}
