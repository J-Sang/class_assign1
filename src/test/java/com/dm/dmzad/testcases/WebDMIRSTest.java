package com.dm.dmzad.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dm.IRS.web.gui.pages.HomePage;
import com.dm.IRS.web.gui.pages.LoginPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;


public class WebDMIRSTest extends AbstractTest {	

	@Test(testName = "TC001", description = "Login Test - IRS",dataProvider = "DataProvider")
	@MethodOwner(owner = "dmqa")
	@TestLabel(name = "feature", value = { "web", "Sanity" })
	@XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "IRS", dsUid = "TestCaseID", executeColumn = "TestCaseID", executeValue = "TC001")
	public void loginTest(HashMap<String, String> args) throws Exception {
		LoginPage loginpage = new LoginPage(getDriver());
		loginpage.open();		
		loginpage.Login(args.get("Username"),args.get("Password"));
		
		HomePage hmpage = new HomePage(getDriver());
		String actualTitle = hmpage.GetTitleOfpage();
		Assert.assertEquals(actualTitle, args.get("HomePageTitle"));
		
		
		
	}

}
