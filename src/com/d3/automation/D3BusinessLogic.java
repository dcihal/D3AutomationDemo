package com.d3.automation;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestListenerAdapter;


public class D3BusinessLogic {
	
    public WebDriver Driver;
    public long TimeToWait = 15;
//public TimeSpan TimeToWait = TimeSpan.FromSeconds(30);
    public D3PageMapping Mapping;
    public WebDriverWait wait;

    
    public void init(WebDriver driver)
    {
      	Mapping = new D3PageMapping(driver);
        PageFactory.initElements(driver, Mapping);
        Driver = driver;
        wait = new WebDriverWait(driver, TimeToWait);
    	
    }
    
    
    public void veriyHomePage(WebDriver driver)
    {
  
        try
        {
            wait.until(ExpectedConditions.titleContains("D3 Banking"));
            Assert.assertTrue(true);
            
        }
        catch(Exception e)
        {
        	//Assert.assertTrue(false);
            throw new NotFoundException("FAIL.");
        }
    }
    
    public void loginUn(WebDriver driver, String username)
    {
    	wait.until(ExpectedConditions.visibilityOf(Mapping.UserName));
    	Mapping.UserName.sendKeys(username);
    }
    
    public void loginPw(WebDriver driver, String password)
    {
    	wait.until(ExpectedConditions.visibilityOf(Mapping.Password));
    	Mapping.Password.sendKeys(password);
    }
    
    public void submit(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Submit));	
    //new Actions(driver).moveToElement(Mapping.Submit).click().perform();
     	Actions builder = new Actions(driver); 
        builder.click(Mapping.Submit).release().build().perform();
    }
    
    public void secretQuestion(WebDriver driver, String secretq)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.SecretQuestion));	
    Mapping.SecretQuestion.sendKeys(secretq);
	}
    
    public void planButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Plan));	
    Mapping.Plan.click(); 
    WebDriverWait wait = new WebDriverWait(driver, 5);
    WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.active.flipper-button.plan-button")));
	}
    
    public void createBudget(WebDriver driver)
    {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.active.flipper-button.plan-button")));
    wait.until(ExpectedConditions.visibilityOf(Mapping.CreateBudget));	
    Mapping.CreateBudget.click(); 
	}
    
    public void manageButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Manage));	
    Mapping.Manage.click(); 
    WebDriverWait wait = new WebDriverWait(driver, 5);
    WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.active.flipper-button.manage-button")));
	}
    
    public void messagesButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Messages));	
    Mapping.Messages.click(); 
	}
    
    public void accountsButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Accounts));	
    Mapping.Accounts.click(); 
	}
    
    public void transactionsButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Transactions));	
    Mapping.Transactions.click(); 
	}
    
    public void moneyMovementButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.MoneyMovement));	
    Mapping.MoneyMovement.click(); 
	}
    
    public void planningButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Planning));	
    Mapping.Planning.click(); 
	}
    
    public void helpButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Help));	
    Mapping.Help.click(); 
	}
    
    public void settingsButton(WebDriver driver)
    {
    wait.until(ExpectedConditions.visibilityOf(Mapping.Settings));	
    Mapping.Settings.click(); 
	}

}

