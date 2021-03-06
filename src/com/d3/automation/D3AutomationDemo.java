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


public class D3AutomationDemo {

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
	
	
  @Test(priority = 1)
  public void verifyHomepageTitle() {
	   TestCase = "12";
	   bl.veriyHomePage(driver);
  }
    
  @Test(priority = 2)
  @Parameters({"userName"})
  public void verifyInvalidLogin(String userName) 
  {
	   TestCase = "1";
	   bl.loginUn(driver, userName);
	   bl.loginPw(driver, "xxxxxx");
	   bl.submit(driver);
	   //WebDriverWait wait = new WebDriverWait(driver, 10);
	   //wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='user-alert']"), "Invalid User Credentials"));
	   Utils.isTextPresent(driver, "Invalid");

  }
  
  @Test(priority = 3)
  @Parameters({"userName", "passWord"})
  public void verifyValidLogin(String userName, String passWord) 
  {
	   TestCase = "2";
	   driver.findElement(By.name("user-name")).clear();
	   driver.findElement(By.name("password")).clear();
	   bl.loginUn(driver, userName);
	   bl.loginPw(driver, passWord);
	   bl.submit(driver);
  }
  
  @Test(priority = 4)
  @Parameters({"secretQuestion"})
  public void verifySecretQuestion(String secretQuestion) 
  {
	   TestCase = "13";
	   bl.secretQuestion(driver, secretQuestion);
	   bl.privateDevice(driver);
	   bl.submit(driver);
	   Utils.isTextPresent(driver, "Last Login:");
	   Utils.isTextPresent(driver, "Logout");	   
	   Utils.isTextPresent(driver, "Samuel Adams III");
	  //String expectedTitle = "Welcome: Mercury Tours";
		//String actualTitle = driver.getTitle();
		//Assert.assertEquals(actualTitle, expectedTitle);
  }
  
//  @Test(priority = 5)
//  public void termsOfService() {
//	   //TestCase = "";
//	  _aiTemp.termsOfService(driver);
//	  _aiTemp.submit(driver);
//  }
  
  @Test(priority = 6)
  public void verifyPlanButton() 
  {
	   TestCase = "14";
	   bl.planButton(driver);
	   Utils.isTextPresent(driver, "Cash Flow Trends");
	   Utils.isTextPresent(driver, "Financial Goal Progress");	   
  }  
 
//  @Test(priority = 7)
//  public void verifyCreateBudget() {
//  TestCase = "6";
//	   _aiTemp.createBudget(driver);
//  }
// 
//  @Test(priority = 8)
//  public void verifyManageButton() {
//  TestCase = "7";
//	   _aiTemp.manageButton(driver);
//  }

  @Test(priority = 9)
  public void verifyMessagesButton() 
  {
	   TestCase = "15";
	   bl.messagesButton(driver);
	   Utils.isTextPresent(driver, "Messages: Notices");
  }  
  
  @Test(priority = 10)
  public void verifyAccountsButton() 
  {
	   TestCase = "16";
	   bl.accountsButton(driver);
	   Utils.isTextPresent(driver, "My Accounts");
	   Utils.isTextPresent(driver, "Assets");
	   Utils.isTextPresent(driver, "Liabilities");
  }  
 
  @Test(priority = 11)
  public void verifyTransactionsButton() 
  {
	   TestCase = "17";
	   bl.transactionsButton(driver);
	   Utils.isTextPresent(driver, "All Accounts");
  }  
 
  @Test(priority = 12)
  public void verifyMoneyMovementButton() 
  {
	   TestCase = "18";
	   bl.moneyMovementButton(driver);
	   Utils.isTextPresent(driver, "Money Movement: Schedule");
	   Utils.isTextPresent(driver, "Payments & Transfers");
  }  
 
  @Test(priority = 13)
  public void verifyPlanningButton() 
  {
	   TestCase = "19";
	   bl.planningButton(driver);
	   Utils.isTextPresent(driver, "Planning: Budget");
	   Utils.isTextPresent(driver, "Income Categories");
	   Utils.isTextPresent(driver, "Expense Categories");
  }  
 
  @Test(priority = 14)
  public void verifyHelpButton() 
  {
	   TestCase = "20";
	   bl.helpButton(driver);
	   Utils.isTextPresent(driver, "Help: Frequently Asked Questions");
	   Utils.isTextPresent(driver, "Customer Support:");
	   Utils.isTextPresent(driver, "402-555-1234");
  }  
 
  @Test(priority = 15)
  public void verifySettingsButton() 
  {
	   TestCase = "21";
	   bl.settingsButton(driver);
	   Utils.isTextPresent(driver, "Settings:");
	   Utils.isTextPresent(driver, "User Profile");
  }  
    
  @AfterMethod
  public void updateTestRails(ITestResult result) 
  {
     if (result.getStatus() == ITestResult.SUCCESS) {
        try {
			d3testrails.Passed(TestRun, TestCase, "It worked!");
		} catch (IOException | APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.print("Successful TestRun [" + TestRun + "] TestCase[" + TestCase + "]\n");
     }
     else
     {
         try {
 			d3testrails.Failed(TestRun, TestCase, "It failed!");
 		} catch (IOException | APIException e) {
 			// TODO Auto-generated catch block
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