package com.dm.dmzad.web.gui.pages;

import java.lang.invoke.MethodHandles;
import com.dm.dmzad.web.gui.base.BaseUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    WebDriverWait wait = new WebDriverWait(driver,30);
    BaseUtilities utilities = new BaseUtilities(driver);
    
    
    
    @FindBy(xpath = "//a[@id ='myid_btn']")
    private ExtendedWebElement singleSignOnBtn;
    
    //zad login
    @FindBy(xpath = "//button[contains(@class,'UAEPASS')]")
    private ExtendedWebElement UAEPassBtn;
    
    @FindBy(xpath = "//input[@id='username']")
    private ExtendedWebElement passUser;
    
    @FindBy(xpath = "//input[@id='basicPasswordForm-submitButton']")
    private ExtendedWebElement passLoginBtn;
    
    
    
    
    
    //@FindBy(xpath = "//*[@title='Change to English']")
    //private ExtendedWebElement languageTranslation;

    @FindBy(id = "loginForm:username")
    private ExtendedWebElement txtUsername;
    
    @FindBy(id = "loginForm:password")
    private ExtendedWebElement txtPassword;
    
    @FindBy(xpath = "(//*[@value='Login'])[1]")
    private ExtendedWebElement btnLogin;
    
    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    private ExtendedWebElement chkboxCaptcha;
    
    @FindBy(xpath = "//span[@id='recaptcha-anchor']")
    private ExtendedWebElement chkboxCaptcha1;
    
    @FindBy(xpath = "(//iframe[@title='recaptcha challenge'])[1]")
    private ExtendedWebElement imageFrame;
    
    @FindBy(xpath = "//*[@id='rc-imageselect']")
    private ExtendedWebElement image;  
    
    @FindBy(xpath = "//*[@id='recaptcha-anchor'][1]")
    private ExtendedWebElement checkmarkCaptcha;
    
   
  
    
    
    

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://stgsso.dm.gov.ae/oxauth/auth/passport/dsg_external_en.htm");
		driver.manage().window().maximize();        
    }
    //**************************************************    
    
    
    public DashboardPage isLoginWithPassBtnValid(String email) throws InterruptedException {
    	UAEPassBtn.assertElementPresent();
    	String loginWithPassBtn = UAEPassBtn.getText();
    	LOGGER.info(loginWithPassBtn+ " button is present on Login page");
    	UAEPassBtn.click();
    	pause(2);
    	Assert.assertTrue(driver.getTitle().equalsIgnoreCase("UAE PASS"), "Page to enter Pass credentials is not opened");
    	LOGGER.info("Input the credentials");
    	passUser.type(email);
    	passLoginBtn.click();
    	pause(2);
    	//manually approve in mobile ***
    	Thread.sleep(10000);
    	LOGGER.info("Waiting for the user to approve the login from mobile app");
    	return new DashboardPage(driver);    	
    }
    
    public String getTitleOfPage() {    	
    	return driver.getTitle();
    	
    }
    
    public void login(String username, String password) throws InterruptedException {
    	txtUsername.type(username);
    	txtPassword.type(password);
    	//driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@title='reCAPTCHA'])[1]")));
    	LOGGER.info("Check the checkbox"); 
    	//JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		//jse2.executeScript("arguments[0].click()", chkboxCaptcha.getElement());	
    	
    	//System.out.println("Before clicking on captcha:"+image.getElement().isDisplayed());
    	
    	chkboxCaptcha.check();  
    	Thread.sleep(5000);    	
    	driver.switchTo().defaultContent();
    	
    	driver.switchTo().frame(imageFrame.getElement());
    	Boolean captchaPresent = image.getElement().isDisplayed();
    	
    	System.out.println("Before clicking on captcha:"+captchaPresent);
    	
    	if(captchaPresent==true) {
    		Assert.assertTrue(captchaPresent, "Restricting robotic action is Failed!!!");
    		LOGGER.info("Restricting robotic action is a Success!!!");
    	}
    	else if(captchaPresent==false) {
    		Assert.assertTrue(false, "Restricting robotic action is Failed!!!");
    	}    	
    	driver.switchTo().defaultContent();
    }
    
        
    
    
    
    
    
}
