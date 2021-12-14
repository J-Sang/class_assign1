package com.dm.IRS.web.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class LoginPage extends AbstractPage {
	
	
	 @FindBy(xpath = "(//div[contains(@class,'LoginInput')])[1]/input")
	  private ExtendedWebElement userName;
	 
	 @FindBy(xpath = "(//div[contains(@class,'LoginInput')])[2]/input")
	  private ExtendedWebElement passWord;
	 
	 @FindBy(xpath = "//button[contains(@class,'login')]")
	  private ExtendedWebElement login;
	
	
	public LoginPage (WebDriver driver) {
		super(driver);
		setPageAbsoluteURL("https://irsstg.dm.gov.ae/index.html");
		driver.manage().window().maximize();		
	}
	
	public void Login(String uname, String pword) {
		userName.type(uname);
		passWord.type(pword);
		//userName.getElement().sendKeys(uname);
		login.click();	
		
	}

}
