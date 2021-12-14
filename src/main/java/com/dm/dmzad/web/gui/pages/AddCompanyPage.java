package com.dm.dmzad.web.gui.pages;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import com.dm.dmzad.web.gui.base.BaseUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCompanyPage extends AbstractPage {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	WebDriverWait wait = new WebDriverWait(driver, 30);
	BaseUtilities utilities = new BaseUtilities(driver);	
	
	// input elements copany details
	@FindBy(xpath = "//span[text()='Select Emirates']")
	private ExtendedWebElement emirates;	
	
	@FindBy(xpath = "//div[@class='ui-select-dropdown select2-drop select2-with-searchbox select2-drop-active']")
	private ExtendedWebElement dropdownBlock;	
	
	@FindBy(xpath = "//li[@role='option']/div/span")
	private List<ExtendedWebElement> emiratesOptions;

	@FindBy(xpath = "//div[@name='LicenseAuthorityId']/a")
	private ExtendedWebElement issueingAuthority;
	
	@FindBy(xpath = "//div[@class='ui-select-dropdown select2-drop select2-with-searchbox select2-drop-active']")
	private ExtendedWebElement dropdownBlockAuthority;
	
	@FindBy(xpath = "//li[@role='option']/div/span")
	private List<ExtendedWebElement> authorityOptions;	

	@FindBy(xpath = "//*[@name='LicenseNumber']")
	private ExtendedWebElement license;

	@FindBy(xpath = "//*[@name='NameEn']")
	private ExtendedWebElement NameEn;

	@FindBy(xpath = "//*[@name='NameAr']")
	private ExtendedWebElement NameAr;

	@FindBy(xpath = "//span[text()='Select Business Type']")
	private ExtendedWebElement businessType;
	
	@FindBy(xpath = "//li[@role='option']/div/span")
	private List<ExtendedWebElement> businessOptions;
	
	//***************
	@FindBy(xpath = "//div[@class='_720kb-datepicker-calendar _720kb-datepicker-open']")
	private ExtendedWebElement calendarPopup;
	
	@FindBy(xpath = "(//div[@class='_720kb-datepicker-calendar-header']/div[2])[1]/a/span")
	private ExtendedWebElement yearpicker;
	
	@FindBy(xpath = "(//div[@class='_720kb-datepicker-calendar-header']/div)[1]/a")
	private ExtendedWebElement leftArrow;
	
	@FindBy(xpath = "(//div[@class='_720kb-datepicker-calendar-header']/div[2])[1]")
	private ExtendedWebElement monthPicker;
	
	@FindBy(xpath = "(//div[@class='_720kb-datepicker-calendar-body'])[1]/a")
	private List<ExtendedWebElement> datePicker;	
	
	@FindBy(xpath = "//*[@name='IssueDate']")
	private ExtendedWebElement issueDate;
	//*******************
	
	@FindBy(xpath = "((//div[@class='_720kb-datepicker-calendar-header'])[2]/div[2])[1]/a/span")
	private ExtendedWebElement yearpicker1;	
	
	@FindBy(xpath = "((//div[@class='_720kb-datepicker-calendar-header'])[2]/div)[3]/a")
	private ExtendedWebElement rightArrow;
	
	@FindBy(xpath = "((//div[@class='_720kb-datepicker-calendar-header'])[2]/div[2])[1]")
	private ExtendedWebElement monthPicker1;
	
	@FindBy(xpath = "(//div[@class='_720kb-datepicker-calendar-body'])[2]/a")
	private List<ExtendedWebElement> datePicker1;	

	@FindBy(xpath = "//*[@name='ExpiryDate']")
	private ExtendedWebElement expiryDate;

	@FindBy(xpath = "//li[@class='select2-search-field']/input")
	private ExtendedWebElement companyActivities;

	@FindBy(xpath = "//*[@name='ShipmentImporterCode']")
	private ExtendedWebElement customImporterCode;	
	
	// input elements contact
	@FindBy(xpath = "//*[@name='OwnerName']")
	private ExtendedWebElement ownerTxt;
	
	@FindBy(xpath = "//*[@name='MangerName']")
	private ExtendedWebElement manager;
	
	@FindBy(xpath = "//*[@name='Mobile']")
	private ExtendedWebElement mobileCompany;
	
	@FindBy(xpath = "//*[@name='Phone']")
	private ExtendedWebElement phoneCompany;
	
	@FindBy(xpath = "//*[@name='PO_Box']")
	private ExtendedWebElement post;
	
	@FindBy(xpath = "//*[@name='Fax']")
	private ExtendedWebElement fax;
	
	@FindBy(xpath = "//*[@name='Email']")
	private ExtendedWebElement email;
	
	@FindBy(xpath = "//*[@name='Address']")
	private ExtendedWebElement address;
	
	// input elements attachment
	@FindBy(xpath = "//*[@name='filePicker1']")
	private ExtendedWebElement tl;
	
	@FindBy(xpath = "//*[@name='filePicker2']")
	private ExtendedWebElement authLetter;
	
	@FindBy(xpath = "//*[@name='filePicker3']")
	private ExtendedWebElement rc;
	
	
	// input elements view details
	@FindBy(xpath = "//*[@ng-click='submitForm();']")
	private ExtendedWebElement submit;	

	@FindBy(xpath = "//*[@translate='LNG_Continue']")
	private ExtendedWebElement continueBtn;
	
	//success dialog
	@FindBy(xpath = "//div[@aria-labelledby='swal2-title']")
	private ExtendedWebElement successBox;
	
	@FindBy(xpath = "//div[@aria-labelledby='swal2-title']/div/h2")
	private ExtendedWebElement successMsg;
	
	@FindBy(xpath = "//div[@aria-labelledby='swal2-title']/div[2]/div[1]")
	private ExtendedWebElement reqNo;
	
	@FindBy(xpath = "//button[@class='swal2-cancel swal2-styled']")
	private ExtendedWebElement closeDialog;
	
	@FindBy(xpath = "//*[@translate='LNG_Dashboard']")
	private ExtendedWebElement dashboardTab;

	public AddCompanyPage(WebDriver driver) {
		super(driver);
		setPageAbsoluteURL("https://stg-sfederal.dm.gov.ae/Company/AddCompany");
	}
	
	
	public void addCompanyDetails(List<String> inputdata) throws InterruptedException {
		// TODO Auto-generated method stub
		emirates.click();		
		wait.until(ExpectedConditions.visibilityOf(dropdownBlock.getElement()));				
		for(int i=0; i<emiratesOptions.size(); i++) {
			if(emiratesOptions.get(i).getText().equalsIgnoreCase(inputdata.get(0))) {
				emiratesOptions.get(i).click();
				break;
			}
		}		
		issueingAuthority.click();		
		wait.until(ExpectedConditions.visibilityOf(dropdownBlock.getElement()));				
		for(int i=0; i<authorityOptions.size(); i++) {
			if(authorityOptions.get(i).getText().equalsIgnoreCase(inputdata.get(1))) {
				authorityOptions.get(i).click();
				break;
			}
		}		
		license.type(inputdata.get(2));
		NameEn.type(inputdata.get(3));
		NameAr.type(inputdata.get(4));
		
		
		//businessType.select(inputdata.get(5));
		businessType.click();		
		wait.until(ExpectedConditions.visibilityOf(dropdownBlock.getElement()));				
		for(int i=0; i<businessOptions.size(); i++) {
			if(businessOptions.get(i).getText().equalsIgnoreCase(inputdata.get(5))) {
				businessOptions.get(i).click();
				break;
			}
		}		
		issueDate.click();
		wait.until(ExpectedConditions.visibilityOf(calendarPopup.getElement()));
		String[] dataIssue = inputdata.get(6).split(" ");
		String dateI = dataIssue[0];
		System.out.println(dateI);
		String monthI = dataIssue[1];
		System.out.println(monthI);
		String yearI = dataIssue[2];
		System.out.println(yearI);
		
		//issue year can be current or past years
		System.out.println(yearpicker.getText());
		while(!(yearpicker.getText().equalsIgnoreCase(yearI))) {
			leftArrow.click();
		}
		System.out.println(monthPicker.getText());
		while(!(monthPicker.getText().contains(monthI))) {
			leftArrow.click();
		}
		
		for(int k=0; k<datePicker.size(); k++) {
			System.out.println(datePicker.get(k).getText());
			if(datePicker.get(k).getText().equals(dateI)) {
				datePicker.get(k).click();
				break;
			}
		}
		Thread.sleep(5000);
		expiryDate.click();
		wait.until(ExpectedConditions.visibilityOf(calendarPopup.getElement()));
		String[] dataExpiry = inputdata.get(7).split(" ");
		String dateE = dataExpiry[0];
		System.out.println(dateE);
		String monthE = dataExpiry[1];
		System.out.println(monthE);
		String yearE = dataExpiry[2];
		System.out.println(yearE);
		
		//issue year can be current or past years
		System.out.println(yearpicker1.getText());
		while(!(yearpicker1.getText().equalsIgnoreCase(yearE))) {
			rightArrow.click();
		}
		System.out.println(monthPicker1.getText());
		while(!(monthPicker1.getText().contains(monthE))) {
			rightArrow.click();
		}		
		for(int k=0; k<datePicker1.size(); k++) {
			System.out.println(datePicker1.get(k).getText());
			if(datePicker1.get(k).getText().equals(dateE)) {
				datePicker1.get(k).click();
				break;
			}
		}		
		if(!(inputdata.get(8).isEmpty())) {
			companyActivities.type(inputdata.get(8));
		}
		if(!(inputdata.get(9).isEmpty())) {
			customImporterCode.type(inputdata.get(9));
		}		
		continueBtn.click();		
	}
	
	public void addContactDetails(List<String> inputContactData) {
		pause(3);
		wait.until(ExpectedConditions.visibilityOf(ownerTxt.getElement()));
		ownerTxt.type(inputContactData.get(0));
		pause(3);
		manager.type(inputContactData.get(1));
		pause(3);
		mobileCompany.type(inputContactData.get(2));
		pause(3);
		phoneCompany.type(inputContactData.get(3));
		pause(3);
		post.type(inputContactData.get(4));
		pause(3);
		fax.type(inputContactData.get(5));
		pause(3);
		email.type(inputContactData.get(6));
		pause(3);
		address.type(inputContactData.get(7));
		pause(3);
		continueBtn.click();		
	}
	
	public void addAttachments() throws InterruptedException, IOException {
		String basepath = System.getProperty("user.dir");
		String path = utilities.fetchData("pdfFilepath");
		tl.getElement().sendKeys(basepath+path);
		pause(3);
		authLetter.getElement().sendKeys(basepath+path);
		pause(3);
		rc.getElement().sendKeys(basepath+path);
		pause(3);
		continueBtn.click();
		pause(3);
		submit.click();
		Thread.sleep(3000);
	}
	
	public void storeRequestNumber() throws IOException {
		successBox.assertElementPresent();
		Assert.assertTrue(successMsg.getText().contains("Success"), "Failed to add Company");
		LOGGER.info("Company added Successfully");
		String requestCreated = reqNo.getText().split(":")[1];
		LOGGER.info("Request generated is :"+requestCreated);
		closeDialog.click();
		pause(3);	
		utilities.storeData("request", requestCreated);
		LOGGER.info("Request ("+requestCreated+") is stored in property file");
		//return requestCreated;
	}
		
	public DashboardPage navigateToDashboard() {		
		dashboardTab.click();		
		return new DashboardPage(driver);
	}    
}
