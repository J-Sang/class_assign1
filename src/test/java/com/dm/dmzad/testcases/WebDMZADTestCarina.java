package com.dm.dmzad.testcases;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.dm.dmzad.web.gui.pages.AddCompanyPage;
import com.dm.dmzad.web.gui.pages.AddCompanyProductPage;
import com.dm.dmzad.web.gui.pages.DashboardPage;
import com.dm.dmzad.web.gui.pages.ZadWelcomePage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;

/**
 * This sample shows how create Web test.
 * 
 * @author qpsdemo
 */
public class WebDMZADTestCarina extends AbstractTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());	
	
	List<String> inputdata;
	List<String> inputContactDetails;
	List<String> inputProductDetails;
	
	@Test(testName = "TC001", description = "Create company - ZAD",dataProvider = "DataProvider")
	@MethodOwner(owner = "dmqa")
	@TestLabel(name = "feature", value = { "web", "Sanity" })
	@XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "ZAD_login", dsUid = "TestCaseID", executeColumn = "TestCaseID", executeValue = "TC001")
	public void test1_AddCompany(HashMap<String, String> args) throws Exception {
		ZadWelcomePage wcpg = new ZadWelcomePage(getDriver());
		wcpg.open();
		DashboardPage dashboardPage = wcpg.logintoZadUAELocalAuth(args.get("Username"), args.get("Password"));	
		String pageTitle = dashboardPage.isDashboardPresent();
		Assert.assertTrue(pageTitle.equalsIgnoreCase(args.get("Navigation Page Title")), "User is not navigated to Dashboard page Expected page url: "+args.get("Navigation Page Title")+" but found: "+pageTitle);
		LOGGER.info("User is navigated to "+pageTitle+ " page");
		
		inputdata = Stream.of(args.get("Emirates"), args.get("Issuing Authority"), args.get("Trade License"),args.get("Company Name (English)"), args.get("Company Name (Arabic)"), args.get("Business Type"), args.get("Issue Date"), args.get("Expiry Date"), args.get("Company Activities"), args.get("Customs Importer Code")).map(s -> s.toString()).collect(Collectors.toList());
		AddCompanyPage addCompPg = dashboardPage.navigateToCompany();			
		addCompPg.addCompanyDetails(inputdata);
		
		inputContactDetails = Stream.of(args.get("Owner Name"), args.get("Manager Name"), args.get("Company Mobile"),args.get("Company Phone"), args.get("Post Office"), args.get("Company Fax"), args.get("Company Email"), args.get("Address")).map(s -> s.toString()).collect(Collectors.toList());
		addCompPg.addContactDetails(inputContactDetails);
		addCompPg.addAttachments();
		// fetch the request created and store it in property file for further reference		
		addCompPg.storeRequestNumber();		
		dashboardPage = addCompPg.navigateToDashboard();		
		pageTitle = dashboardPage.isDashboardPresent();		
		Assert.assertTrue(pageTitle.equalsIgnoreCase(args.get("Navigation Page Title")), "User is not navigated to Dashboard page Expected page url: "+args.get("Navigation Page Title")+" but found: "+pageTitle);
		LOGGER.info("User is navigated to "+pageTitle+ " page");
				
	}
	
	@Test(testName = "TC002", description = "Verify company - ZAD",dataProvider = "DataProvider")
	@MethodOwner(owner = "dmqa")
	@TestLabel(name = "feature", value = { "web", "Sanity" })
	@XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "ZAD_login", dsUid = "TestCaseID", executeColumn = "TestCaseID", executeValue = "TC002")
	public void test2_testCompanyAdded(HashMap<String, String> args) throws Exception {
		ZadWelcomePage wcpg = new ZadWelcomePage(getDriver());
		wcpg.open();		
		DashboardPage dashboardPage = wcpg.logintoZadUAELocalAuth(args.get("Username"), args.get("Password"));		
		String pageTitle = dashboardPage.isDashboardPresent();		
		Assert.assertTrue(pageTitle.equalsIgnoreCase(args.get("Navigation Page Title")), "User is not navigated to Dashboard page Expected page url: "+args.get("Navigation Page Title")+" but found: "+pageTitle);
		LOGGER.info("User is navigated to "+pageTitle+ " page");
		String companyReqNo = dashboardPage.isCompanyAdded();
		LOGGER.info("the company "+companyReqNo+" is added in ZAD");		
		
	}
	
	@Test(testName = "TC003", description = "Add company product - ZAD",dataProvider = "DataProvider")
	@MethodOwner(owner = "dmqa")
	@TestLabel(name = "feature", value = { "web", "Sanity" })
	@XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "ZAD_login", dsUid = "TestCaseID", executeColumn = "TestCaseID", executeValue = "TC003")
	public void test3_AddProduct(HashMap<String, String> args) throws Exception {
		ZadWelcomePage wcpg = new ZadWelcomePage(getDriver());
		wcpg.open();		
		DashboardPage dashboardPage = wcpg.logintoZadUAELocalAuth(args.get("Username"), args.get("Password"));		
		String pageTitle = dashboardPage.isDashboardPresent();		
		Assert.assertTrue(pageTitle.equalsIgnoreCase(args.get("Navigation Page Title")), "User is not navigated to Dashboard page Expected page url: "+args.get("Navigation Page Title")+" but found: "+pageTitle);
		LOGGER.info("User is navigated to "+pageTitle+ " page");	
		
		inputdata = Stream.of(args.get("Emirates"), args.get("Issuing Authority"), args.get("Trade License"),args.get("Company Name (English)")).map(s -> s.toString()).collect(Collectors.toList());
		AddCompanyProductPage addProductPg = dashboardPage.navigateToProduct();
		addProductPg.FetchCompanyToAddProduct(inputdata);	
		
		inputProductDetails = Stream.of(args.get("International Barcode"), args.get("Brand"), args.get("Trade Name"),args.get("Manufacturer"), args.get("Country of Origin"), args.get("Weight"), args.get("Weight Unit"), args.get("Packaging Type"), args.get("Shelf life"), args.get("Storage Conditions"), args.get("HS Code"), args.get("Is Halal")).map(s -> s.toString()).collect(Collectors.toList());
		addProductPg.addCompanyProductDetails(inputProductDetails);
		addProductPg.addAttachments();		 
		addProductPg.storeRequestNumber();		
		dashboardPage = addProductPg.navigateToDashboard();		
		pageTitle = dashboardPage.isDashboardPresent();		
		Assert.assertTrue(pageTitle.equalsIgnoreCase(args.get("Navigation Page Title")), "User is not navigated to Dashboard page Expected page url: "+args.get("Navigation Page Title")+" but found: "+pageTitle);
		LOGGER.info("User is navigated to "+pageTitle+ " page");
	}
	
	@Test(testName = "TC004", description = "Verify company - ZAD",dataProvider = "DataProvider")
	@MethodOwner(owner = "dmqa")
	@TestLabel(name = "feature", value = { "web", "Sanity" })
	@XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "ZAD_login", dsUid = "TestCaseID", executeColumn = "TestCaseID", executeValue = "TC004")
	public void test4_testProductAdded(HashMap<String, String> args) throws Exception {
		ZadWelcomePage wcpg = new ZadWelcomePage(getDriver());
		wcpg.open();		
		DashboardPage dashboardPage = wcpg.logintoZadUAELocalAuth(args.get("Username"), args.get("Password"));		
		String pageTitle = dashboardPage.isDashboardPresent();		
		Assert.assertTrue(pageTitle.equalsIgnoreCase(args.get("Navigation Page Title")), "User is not navigated to Dashboard page Expected page url: "+args.get("Navigation Page Title")+" but found: "+pageTitle);
		LOGGER.info("User is navigated to "+pageTitle+ " page");
		String productReqNo = dashboardPage.isProductAdded();
		LOGGER.info("the company "+productReqNo+" is added in ZAD");		
	}
}
