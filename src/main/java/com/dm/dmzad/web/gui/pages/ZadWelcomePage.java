package com.dm.dmzad.web.gui.pages;

import com.dm.dmzad.web.gui.base.BaseUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;


import org.openqa.selenium.support.ui.WebDriverWait;

public class ZadWelcomePage extends AbstractPage {
  
    WebDriverWait wait = new WebDriverWait(driver,30);
    BaseUtilities utilities = new BaseUtilities(driver);     
    
    @FindBy(xpath = "//*[@translate='LNG_UAE_municipality']")
    private ExtendedWebElement uaeLocalAuth;    
    
    @FindBy(xpath = "//a[@id ='myid_btn']")
    private ExtendedWebElement singleSignOnBtn;
    
    @FindBy(xpath = "//input[@id='Email']")
    private ExtendedWebElement email;
    
    @FindBy(xpath = "//input[@id='Password']")
    private ExtendedWebElement password;
    
    @FindBy(xpath = "//button[@id='m_login_signin_submit']")
    private ExtendedWebElement loginBtn;
    
    public ZadWelcomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://stg-sfederal.dm.gov.ae/");
		driver.manage().window().maximize();        
    }
    //**************************************************
    
    public LoginPage logintoZadUAEPass() {
    	singleSignOnBtn.click();
    	return new LoginPage(driver);
    }    
    
    
    public DashboardPage logintoZadUAELocalAuth(String un, String pw) {
    	uaeLocalAuth.click();
    	email.type(un);
    	password.type(pw);
    	loginBtn.click();        	
    	//return new LoginPage(driver);
    	return new DashboardPage(driver); 
    }
       
    public String getTitleOfPage() {
    	//System.out.println(driver.getTitle());
    	return driver.getTitle();    	
    }
   
    
}
