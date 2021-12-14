package com.dm.dmzad.web.gui.pages;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import com.dm.dmzad.web.gui.base.BaseUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCompanyProductPage extends AbstractPage {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	WebDriverWait wait = new WebDriverWait(driver, 30);
	BaseUtilities utilities = new BaseUtilities(driver);	
	
	//input company details
	@FindBy(xpath = "//a[@class='select2-choice ui-select-match']")
	private ExtendedWebElement selectEmirates;	
	
	//@FindBy(xpath = "//li[@role='option']/div/span")
	//private List<ExtendedWebElement> emiratesOptions;
	
	@FindBy(xpath = "//div[@name='IssueingAuthority']/a")
	private ExtendedWebElement selectIssueingAuthority;	
	
	//@FindBy(xpath = "//li[@role='option']/div/span")
	//private List<ExtendedWebElement> authorityOptions;
	
	@FindBy(xpath = "//*[@ng-model='companySearchParams.TradeLicense']")
	private ExtendedWebElement tradeLicense;	
	@FindBy(xpath = "//*[@ng-model='companySearchParams.TradeName']")
	private ExtendedWebElement tradeName;	
	@FindBy(xpath = "//*[@translate='LNG_Search']")
	private ExtendedWebElement searchBtn;	
	@FindBy(xpath = "//*[@dt-instance='vm.dtCompanyInstance']")
	private ExtendedWebElement tradeInstance;	
	@FindBy(xpath = "//*[@dt-instance='vm.dtCompanyInstance']/tbody/tr/td/div")
	private ExtendedWebElement companySelect;	
	@FindBy(xpath = "//div[@class='m-option custom_selected_company']")
	private ExtendedWebElement companySelected;
	@FindBy(xpath = "//div[@class='m-option custom_selected_company']/div/div[2]/h3/a")
	private ExtendedWebElement selectedCompanyName;
	
	//input products
	@FindBy(xpath = "//*[@name='txt_product_InternationalBarcode']")
	private ExtendedWebElement internationalBarcode;
	
	@FindBy(xpath = "//div[@name='select_product_Brand']/a")
	private ExtendedWebElement productBrand;
	@FindBy(xpath = "//input[@name='BrandOtherName']")
	private ExtendedWebElement addBrand;
	
	@FindBy(xpath = "//div[@name='select_product_TradeName']/a")
	private ExtendedWebElement trade;
	@FindBy(xpath = "//input[@name='TradeNameOther']")
	private ExtendedWebElement addTrade;
	
	@FindBy(xpath = "//div[@name='select_product_Manufacturer']/a")
	private ExtendedWebElement manufacturer;
	@FindBy(xpath = "//input[@name='ManufactureOtherName']")
	private ExtendedWebElement addManufacturer;
	
	@FindBy(xpath = "//div[@name='select_product_CountryofOrigin']/a")
	private ExtendedWebElement country;
	
	@FindBy(xpath = "//input[@id='txt_product_Weight']")
	private ExtendedWebElement weight;
	
	@FindBy(xpath = "//div[@name='select_product_WeightUnitVolume']/a")
	private ExtendedWebElement weightUnit;
	
	@FindBy(xpath = "//div[@name='select_product_PackagingType']/a")
	private ExtendedWebElement packagingType;
	
	@FindBy(xpath = "//input[@name='ShelfLife']")
	private ExtendedWebElement selfLife;
	
	@FindBy(xpath = "//div[@name='select_product_StorageConditions']/a")
	private ExtendedWebElement storageCondition;
	
	@FindBy(xpath = "//input[@name='HSCode']")
	private ExtendedWebElement hsCode;	
	
	@FindBy(xpath = "//label[@class='m-checkbox ckh_hscode']/span")
	private ExtendedWebElement halaCheckbox;
	
	@FindBy(xpath = "//*[@id='filePickerIngredients']")
	private ExtendedWebElement ingredientFileUpload;	
	
	
	@FindBy(xpath = "//div[@class='ui-select-dropdown select2-drop select2-with-searchbox select2-drop-active']")
	private ExtendedWebElement dropdownBlock;
	@FindBy(xpath = "//div[@class='ui-select-dropdown select2-drop select2-with-searchbox select2-drop-active']/div/input")
	private ExtendedWebElement txtInput;
	@FindBy(xpath = "//li[@role='option']/div/span")
	private List<ExtendedWebElement> dropOptions;	
	
	// input elements attachment
	@FindBy(xpath = "//*[@name='filePicker1']")
	private ExtendedWebElement productImage;
	
	@FindBy(xpath = "//*[@name='filePicker2']")
	private ExtendedWebElement labelImage;
	
	@FindBy(xpath = "//*[@name='filePicker3']")
	private ExtendedWebElement otherDocument;
	
	
	// input elements view details
	@FindBy(xpath = "//*[@ng-click='Submitform()']")
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

	public AddCompanyProductPage(WebDriver driver) {
		super(driver);		
		setPageAbsoluteURL("https://stg-sfederal.dm.gov.ae/Product/AddEdit");
	}
	
	public void FetchCompanyToAddProduct(List<String> inputdata) throws InterruptedException {
		selectEmirates.click();
		selectUnlisted(inputdata.get(0));
		
		selectIssueingAuthority.click();
		selectUnlisted(inputdata.get(1));
		
		//wait.until(ExpectedConditions.visibilityOf(dropdownBlock.getElement()));
		//for(int i=0; i<emiratesOptions.size(); i++) {
			//if(emiratesOptions.get(i).getText().equalsIgnoreCase(inputdata.get(0))) {
				//emiratesOptions.get(i).click();
				//break;
			//}
		//}		
			
		//wait.until(ExpectedConditions.visibilityOf(dropdownBlock.getElement()));		
		//for(int i=0; i<authorityOptions.size(); i++) {
			//if(authorityOptions.get(i).getText().equalsIgnoreCase(inputdata.get(1))) {
				//authorityOptions.get(i).click();
				//break;
			//}
		//}
		tradeLicense.type(inputdata.get(2));
		tradeName.type(inputdata.get(3));
		searchBtn.click();
		wait.until(ExpectedConditions.visibilityOf(tradeInstance.getElement()));
		LOGGER.info("Company "+inputdata.get(3)+" loaded on search");
		companySelect.click();		
		Thread.sleep(5000);
		companySelected.assertElementPresent();
		Assert.assertTrue(selectedCompanyName.getText().equalsIgnoreCase(inputdata.get(3)), "Company not selected! Expected:"+inputdata.get(3)+" but found:"+selectedCompanyName.getText());
		LOGGER.info("Company "+inputdata.get(3)+" selected");
		continueBtn.click();		
	}
	
	public void selectUnlisted(String input) {
		//String unlisted = "unlisted";
		wait.until(ExpectedConditions.visibilityOf(dropdownBlock.getElement()));
		txtInput.type(input);
		for(int i=0; i<dropOptions.size(); i++) {
			if(dropOptions.get(i).getText().equalsIgnoreCase(input)) {
				dropOptions.get(i).click();
				break;
			}
		}		
	}
	
	public void addCompanyProductDetails(List<String> inputdata) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String unlisted = "unlisted";
		
		internationalBarcode.type(inputdata.get(0));
		
		productBrand.click();
		selectUnlisted(unlisted);		
		addBrand.type(inputdata.get(1));
		
		trade.click();
		selectUnlisted(unlisted);		
		addTrade.type(inputdata.get(2));
		
		manufacturer.click();
		selectUnlisted(unlisted);		
		addManufacturer.type(inputdata.get(3));
		
		country.click();
		selectUnlisted(inputdata.get(4));
		
		weight.type(inputdata.get(5));
		
		weightUnit.click();
		selectUnlisted(inputdata.get(6));
		
		packagingType.click();
		selectUnlisted(inputdata.get(7));
		
		selfLife.type(inputdata.get(8));
		
		storageCondition.click();
		selectUnlisted(inputdata.get(9));
		
		hsCode.type(inputdata.get(10));
		
		
		if(inputdata.get(11).equalsIgnoreCase("true")) {
			halaCheckbox.click();			
		}
		
		String basepath = System.getProperty("user.dir");
		String path = utilities.fetchData("excelFilepath");
		ingredientFileUpload.getElement().sendKeys(basepath+path);
		pause(3);
		
		continueBtn.click();			
	}
	
	
	
	public void addAttachments() throws InterruptedException, IOException {
		String basepath = System.getProperty("user.dir");
		String path = utilities.fetchData("pdfFilepath");
		productImage.getElement().sendKeys(basepath+path);
		pause(3);
		labelImage.getElement().sendKeys(basepath+path);
		pause(3);
		otherDocument.getElement().sendKeys(basepath+path);
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
		utilities.storeData("requestProduct", requestCreated);
		LOGGER.info("Request ("+requestCreated+") is stored in property file");
		//return requestCreated;
	}
		
	public DashboardPage navigateToDashboard() {		
		dashboardTab.click();		
		return new DashboardPage(driver);
	}
	
	
	
	
		
	
	  
    
}
