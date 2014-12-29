package com.d3.automation;

import java.io.File;
import java.io.IOException;
import java.sql.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.ITestResult;

import com.d3.testrails.D3TestRails;
import com.d3.utils.*;
import com.d3.utils.Utils.BrowserType;
import com.gurock.testrail.APIException;


public class LookAndFeel {


		public WebDriver driver;
		private BrowserType browser;
		String TestCase; 
		String TestRun = "1"; 
		D3BusinessLogic bl = new D3BusinessLogic();
		D3TestRails d3testrails = new D3TestRails();
		Utils utils = new Utils();
	   	//Properties p = Utils.loadProperties(".\\conf\\properties.properties");            


		@BeforeClass
		@Parameters({"browse", "WebdriverTimeout", "baseurl"})
		public void launchBrowser(@Optional("FIREFOX") String browse, String WebdriverTimeout, String baseurl)
		{
	    	switch (browse)
	    	{
	    	    case "FIREFOX": 
	    	    	browser = Utils.BrowserType.FIREFOX;
	            	break;
	    	    case "IE":
	    	    	browser = Utils.BrowserType.IE;
	            	break;
	            case "CHROME":
	            	browser = Utils.BrowserType.CHROME;
	            	break;
	            default:
	            	browser = Utils.BrowserType.FIREFOX;
	            	break;
	            	

	    	}
	    	
	        //String webdriverTimeout = p.getProperty("WebdriverTimeout");
	    	Long timeout = Long.valueOf(WebdriverTimeout);
	        driver = Utils.getWebDriver(browser, timeout); 
			
			driver.get(baseurl);
			bl.init(driver, timeout);

		}
					
		@BeforeClass
		@Parameters({"testRailUrl", "testRailUserName", "testRailPassWord"})
		public void initTestRails(String testRailUrl, String testRailUserName, String testRailPassWord)
		{	
			d3testrails.InitRail(testRailUrl, testRailUserName, testRailPassWord);
		}
		
		
		  @Test(priority = 16)
		  @Parameters({"userName", "passWord", "secretQuestion"})
		  public void verifyAccountsButton2(String userName, String passWord, String secretQuestion) 
		  {
			   TestCase = "16";
			   bl.loginUn(driver, userName);
			   bl.loginPw(driver, passWord);
			   bl.submit(driver);
			   bl.secretQuestion(driver, secretQuestion);
			   bl.privateDevice(driver);
			   bl.submit(driver);
			   bl.accountsButton(driver);
			   Utils.isTextPresent(driver, "My Accounts");
			   Utils.isTextPresent(driver, "Assets");
			   Utils.isTextPresent(driver, "Liabilities");
		  } 

	  @AfterMethod
	  public void updateTestRails(ITestResult result) 
	  {
	     if (result.getStatus() == ITestResult.SUCCESS) {
	        try {
				d3testrails.Passed(TestRun, TestCase, "It worked!");
			} catch (IOException | APIException e) {
				e.printStackTrace();
			}
	        //System.out.print("Successful TestRun [" + TestRun + "] TestCase[" + TestCase + "]\n");
	     }
	     else
	     {
	         try {
	 			d3testrails.Failed(TestRun, TestCase, "It failed!");
	 		} catch (IOException | APIException e) {
	 			e.printStackTrace();
	 		}
	        //System.out.print("Failed TestRun [" + TestRun + "] TestCase[" + TestCase + "]\n");
	     }
	  }  
	     
	  @AfterMethod
	  public void takeScreenShotOnFailure(ITestResult testResult) throws IOException 
	  {
		  
			  Date date = new Date(System.currentTimeMillis());
		      String dateString = date.toString();
	      
	    	 if (testResult.getStatus() == ITestResult.FAILURE) { 
	    		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
	    		 FileUtils.copyFile(scrFile, new File(".\\Reports\\Screenshots\\" + testResult.getName()  + dateString + ".png")); 
	    		 } 
	  }
	  

	   
	  @AfterClass
	  public void terminateBrowser()
	  {
		  driver.quit();
	  }
	  
}
