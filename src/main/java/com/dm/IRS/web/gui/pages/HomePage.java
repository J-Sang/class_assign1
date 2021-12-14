package com.dm.IRS.web.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class HomePage extends AbstractPage {
	
	
	
	public HomePage (WebDriver driver) {
		super(driver);
		setPageAbsoluteURL("https://irsstg.dm.gov.ae/index.html");
		driver.manage().window().maximize();		
	}
	
	public String GetTitleOfpage() {
		
		String titleoftHomePage = driver.getTitle();
		return titleoftHomePage;
	}	
	
}
