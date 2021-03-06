package com.d3.automation;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.Keys;

import com.d3.utils.Utils;



public class D3BusinessLogic {
	
    public WebDriver Driver;
    public String TimeToWait; 
    public D3PageMapping Mapping;
    public WebDriverWait wait;
    
    //Utils utils = new Utils();
   	//Properties p = Utils.loadProperties(".\\conf\\properties.properties");
	//String webdriverTimeout = p.getProperty("WebdriverTimeout");
	//Long timeout = Long.valueOf(webdriverTimeout);
    
    public void init(WebDriver driver, Long timeout)
    {

      	Mapping = new D3PageMapping(driver);
        PageFactory.initElements(driver, Mapping);
        Driver = driver;
        wait = new WebDriverWait(driver, timeout);
    	
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
	    new Actions(driver).moveToElement(Mapping.Submit).click().perform();
	//     	Actions builder = new Actions(driver); 
	//        builder.click(Mapping.Submit).release().build().perform();
    }
    
    public void secretQuestion(WebDriver driver, String secretq)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.SecretQuestion));	
	    Mapping.SecretQuestion.sendKeys(secretq);
	}
        
    public void privateDevice(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.Submit));
	    Mapping.PrivateDevice.click();
    }
    
    public void termsOfService(WebDriver driver)
    {
        if (Mapping.Terms.isDisplayed() == true){	
        	new Actions(driver).moveToElement(Mapping.Terms).sendKeys(Keys.SPACE);
        }
    }
    
    public void planButton(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.Plan));	
	    Mapping.Plan.click(); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.active.flipper-button.plan-button")));
    }
    
    public void createBudget(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.active.flipper-button.plan-button")));
	    wait.until(ExpectedConditions.visibilityOf(Mapping.CreateBudget));	
	    Mapping.CreateBudget.click(); 
	}
    
    public void manageButton(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.Manage));	
	    Mapping.Manage.click(); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.active.flipper-button.manage-button")));
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

    public void quickPay(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.QuickPay));	
	    Mapping.QuickPay.click(); 
	}

    public void selectRecipient(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.SelectRecipient));	
	    Mapping.SelectRecipient.click(); 
	}
    
    public void myCreditCardAccount(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.MyCreditCardAccount));	
	    Actions action = new Actions(driver);    
	   		action.moveToElement(Mapping.MyCreditCardAccount).click().build().perform();   
    }

    public void setQuickPayAmount(WebDriver driver, String amount)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.Amount));	
	    new Actions(driver).moveToElement(Mapping.Amount).perform();
	    Mapping.Amount.sendKeys(amount);; 
    }
    
    public void quickPayCalendarCurrentDate(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.QuickPayCalendar));	
	    new Actions(driver).moveToElement(Mapping.QuickPayCalendar).perform();
	    Mapping.QuickPayCalendar.sendKeys(Utils.getDateMMddyyyywithSlash()); 
    }
    
    public void quickPayCalendarSpecificDate(WebDriver driver, String date)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.QuickPayCalendar));	
	    new Actions(driver).moveToElement(Mapping.QuickPayCalendar).perform();
	    Mapping.QuickPayCalendar.sendKeys(date); 
    }
    
    public void quickPaySubmitButton(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.QuickPaySubmit));	
	    Actions action = new Actions(driver);    
   		action.moveToElement(Mapping.QuickPaySubmit).click().build().perform();
    }
    
    public void quickPayConfirm(WebDriver driver)
    {
	    wait.until(ExpectedConditions.visibilityOf(Mapping.QuickPayConfirm));	
	    Actions action = new Actions(driver);    
   		action.moveToElement(Mapping.QuickPayConfirm).click().build().perform();   
    }
    
    
}


