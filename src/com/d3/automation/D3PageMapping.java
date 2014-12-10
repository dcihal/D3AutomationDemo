package com.d3.automation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class D3PageMapping {

	public WebDriver driver;


    public D3PageMapping(WebDriver driver)
    {
        this.driver = driver;
    }

    //@FindBy(how = How.XPATH, using = " /html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/font/a")
    
    //public WebElement Flights; 
    @FindBy(how = How.NAME, using = "user-name")
    
    public WebElement UserName;
    
    @FindBy(how = How.NAME, using = "password")
    
    public WebElement Password;
    
    //@FindBy(how = How.CSS, using = "button.btn.btn-submit")
    @FindBy(how = How.XPATH, using = "//*[@id='challenge-form']/div[2]/div[2]/button[2]")
    
    public WebElement Submit;
    
    @FindBy(how = How.NAME, using = "secret-question")
    
    public WebElement SecretQuestion;
    
    @FindBy(how = How.CSS, using = "button.btn.flipper-button.manage-button")
    
    public WebElement Manage;
	
    @FindBy(how = How.CSS, using = "button.btn.flipper-button.plan-button")
//  @FindBy(how = How.XPATH, using = "//img[contains(@src,'assets/img/glyphicons_173_play_right.png')]")
    
	public WebElement Plan;
    
    @FindBy(how = How.CSS, using = "button.create-budget.btn.pull-right")
    
    public WebElement CreateBudget;
    
    @FindBy(how = How.CSS, using = "div.nav-icon.messages.center")
    
    public WebElement Messages;
    
    @FindBy(how = How.CSS, using = "div.nav-icon.accounts.center")
    
    public WebElement Accounts;
    
    @FindBy(how = How.CSS, using = "div.nav-icon.transactions.center")
    
    public WebElement Transactions;
    
    @FindBy(how = How.CSS, using = "div.nav-icon.money-movement.center")
    
    public WebElement MoneyMovement;
    
    @FindBy(how = How.CSS, using = "div.nav-icon.planning.center")
    
    public WebElement Planning;
    
    @FindBy(how = How.CSS, using = "div.nav-icon.help.center")
    
    public WebElement Help;
    
    @FindBy(how = How.CSS, using = "div.nav-icon.settings.center")
    
    public WebElement Settings;
	
}
