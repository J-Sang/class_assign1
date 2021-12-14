package com.dm.dmzad.web.gui.pages;

import java.io.IOException;
import com.dm.dmzad.web.gui.base.BaseUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends AbstractPage {  
    WebDriverWait wait = new WebDriverWait(driver,30);
	BaseUtilities utilities = new BaseUtilities(driver);    
    
	//add company
    @FindBy(xpath = "//*[@translate='LNG_Company']")
    private ExtendedWebElement companyTab;   
    @FindBy(xpath = "//*[@class='btn-floating btn-large red']")
    private ExtendedWebElement addIcon;    
    @FindBy(xpath = "//*[@class='fixed-action-btn horizontal click-to-toggle active']")
    private ExtendedWebElement overlay;    
    @FindBy(xpath = "//*[@class='btn_circle green']")
    private ExtendedWebElement addCompany;  
    //*********
    
    ////add product
    @FindBy(xpath = "//*[@translate='LNG_Products']")
    private ExtendedWebElement productTab;     
    @FindBy(xpath = "//*[@class='btn_circle blue']")
    private ExtendedWebElement addProduct;
    
     
    @FindBy(xpath = "//*[@translate='LNG_Companies']")
    private ExtendedWebElement companyRequest;    
    @FindBy(xpath = "//*[@ng-model='vm.searchOption.COMPANY.RequestNumber']")    
    private ExtendedWebElement reqNotxt;    
    @FindBy(xpath = "//*[@ng-if='!vm.searchOption.COMPANY.IsAdvanceSearch']")
    private ExtendedWebElement searchBtn;    
    @FindBy(xpath = "//table[@dt-options='vm.dtCOMPANYOptions']/tbody/tr/td[1]")
    private ExtendedWebElement compRow;
    
    @FindBy(xpath = "(//*[@translate='LNG_Products'])[2]")
    private ExtendedWebElement productRequest;
    @FindBy(xpath = "//*[@ng-model='vm.searchOption.ITEM.RequestNumber']")    
    private ExtendedWebElement productReqtxt;
    @FindBy(xpath = "//*[@ng-if='!vm.searchOption.ITEM.IsAdvanceSearch']")
    private ExtendedWebElement productSearchBtn;    
    @FindBy(xpath = "//table[@dt-options='vm.dtITEMOptions']/tbody/tr/td[1]")
    private ExtendedWebElement prodRow;

    public DashboardPage(WebDriver driver) {
    	super(driver);
    	////setPageAbsoluteURL("https://stg-sfederal.dm.gov.ae/Request/EDashboard"); 
    	setPageAbsoluteURL("https://stg-sfederal.dm.gov.ae/Request/IDashboard");  	
    }

    public String isDashboardPresent() {     	
		return driver.getTitle();
    }
    
    public AddCompanyPage navigateToCompany() {  
    	companyTab.click();
    	addIcon.click();
    	wait.until(ExpectedConditions.visibilityOf(overlay.getElement()));
    	addCompany.click();
    	return new AddCompanyPage(driver);		
    }
    
    public String isCompanyAdded() throws IOException {	
    	String companyReq = utilities.fetchData("request");
    	companyRequest.click();
    	//allCompanyRequest.click();  
    	
    	
    	//edited code to fail *************************
		reqNotxt.type(companyReq);
		pause(2);
		searchBtn.click();
		Assert.assertTrue(compRow.getText().equalsIgnoreCase(companyReq), "The copany is not added Expected: "+companyReq+" but found: "+compRow.getText());
		return companyReq;
    }
    
    public AddCompanyProductPage navigateToProduct() throws InterruptedException {
    	productTab.click();
    	Thread.sleep(7000);
    	wait.until(ExpectedConditions.visibilityOf(addIcon.getElement()));
    	addIcon.click();
    	wait.until(ExpectedConditions.visibilityOf(overlay.getElement()));
    	addProduct.click();
    	return new AddCompanyProductPage(driver);		
    }
    
    public String isProductAdded() throws IOException {	
    	String productReq = utilities.fetchData("requestProduct");
    	productRequest.click();
    	//allCompanyRequest.click();    	
    	productReqtxt.type(productReq);
		pause(2);
		productSearchBtn.click();
		Assert.assertTrue(prodRow.getText().equalsIgnoreCase(productReq), "The copany is not added Expected: "+productReq+" but found: "+prodRow.getText());
		return productReq;
    }    
}
