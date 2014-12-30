package com.d3.automation;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.d3.testrails.D3TestRails;
import com.d3.utils.Utils;
import com.d3.utils.Utils.BrowserType;
import com.gurock.testrail.APIException;

public class QuickPayPaymentFutureDate {
	
	public WebDriver driver;
	private BrowserType browser;
	String TestCase; 
	String TestRun = "16"; 
	D3BusinessLogic bl = new D3BusinessLogic();
	D3TestRails d3testrails = new D3TestRails();
	Utils utils = new Utils();

   	//Properties p = Utils.loadProperties(".\\conf\\properties.properties");            


	@BeforeClass
	@Parameters({"browse", "WebdriverTimeout", "baseurl"})
	public void launchBrowser(@Optional("CHROME") String browse, String WebdriverTimeout, String baseurl)
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
	
	
	  @Test(priority = 17)
	  @Parameters({"userName", "passWord", "secretQuestion"})
	  public void verifyQuickPayFutureDate(String userName, String passWord, String secretQuestion) 
	  {
		   TestCase = "334";
		   bl.loginUn(driver, userName);
		   bl.loginPw(driver, passWord);
		   bl.submit(driver);
		   bl.secretQuestion(driver, secretQuestion);
		   bl.privateDevice(driver);
		   bl.submit(driver);
		   bl.quickPay(driver);
		   bl.selectRecipient(driver);
		   Utils.isTextPresent(driver, "BEST BUY");
		   bl.myCreditCardAccount(driver);
		   Utils.isTextPresent(driver, "AMOUNT");
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