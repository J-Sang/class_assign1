package com.dm.dmzad.web.gui.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class BaseUtilities {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	WebDriver driver;	
	String basepath = System.getProperty("user.dir");
	String pathOfPropFile = basepath+"\\src\\main\\java\\com\\dm\\dmzad\\web\\gui\\base\\data.properties";

	public BaseUtilities(WebDriver driver) {
		this.driver = driver;
	}
	
	public void storeData(String propItem, String value) throws IOException {		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(pathOfPropFile);
		prop.load(fis);
		//String b = prop.getProperty("request");
		//System.out.println(b);
		prop.setProperty(propItem, value);
		//String url = prop.getProperty("url");
		//System.out.println(url);
		
		FileOutputStream fos = new FileOutputStream(pathOfPropFile);
		prop.store(fos, "request added");		
	}
	
	public String fetchData(String propItem) throws IOException {		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(pathOfPropFile);
		prop.load(fis);
		String value = prop.getProperty(propItem);
		return value;		
	}	

	// list out page url/title of all the browser tabs opened
	public List<String> fetchUrlList(String data) {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		List<String> listOfURL = new ArrayList<String>();
		List<String> listOfTitle = new ArrayList<String>();
		List<String> resultList = new ArrayList<String>();
		while (it.hasNext()) {
			String currentTab = it.next();
			driver.switchTo().window(currentTab);
			listOfURL.add(driver.getCurrentUrl());
			listOfTitle.add(driver.getTitle());
		}
		if (data.equals("url")) {
			resultList = listOfURL;
		} else if (data.equals("title")) {
			resultList = listOfTitle;
		}
		return resultList;
	}
	
			
	
	//check if alert window pops up
	public boolean CheckAlert() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	// Check Broken links
	public int brokenLink(List<ExtendedWebElement> links, List<ExtendedWebElement> linkText) throws IOException {
		String eachUrl;
		int responseCode;
		int noOfBrokenUrl=0;
		List<String> brokenLinks = new ArrayList<String>();
		for (int i = 0; i < links.size(); i++) {
			eachUrl = links.get(i).getAttribute("href");
			System.out.println(eachUrl);
			URL url1 = new URL(eachUrl);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			responseCode = conn.getResponseCode();
			if (responseCode <= 300) {
				// System.out.println("URL : " + eachUrl + " Pass! Response code is : " +
				// responseCode+" "+linkText.get(i).getText()+" is a valid link!");				
				LOGGER.info("URL : " + eachUrl + " PASS! Response code is : " + responseCode + " ("+ linkText.get(i).getText() + ") is a valid link!");
			} else {
				System.err.println("URL : (" + eachUrl + ") FAIL! Response code is : " + responseCode);
				brokenLinks.add(linkText.get(i).getText());
				LOGGER.error("URL : " + eachUrl + " Fail! Response code is : " + responseCode + " ("+ linkText.get(i).getText() + ") is an invalid link!");
				noOfBrokenUrl++;
			}
		}
		// return brokenLinks;
		// add if list is not empty condition
		return noOfBrokenUrl;
	}
	
	public int isbrokenLink(String url, String linkText) throws IOException {
		//String eachUrl;
		int responseCode;
		int noOfBrokenUrl=0;
		URL url1 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		responseCode = conn.getResponseCode();
		if (responseCode <= 300) {						
			LOGGER.info("URL : " + url + " PASS! Response code is : " + responseCode + " ("+ linkText + ") is a valid link!");
		} else {
			System.err.println("URL : (" + url + ") FAIL! Response code is : " + responseCode);				
			LOGGER.error("URL : " + url + " Fail! Response code is : " + responseCode + " ("+ linkText + ") is an invalid link!");
			noOfBrokenUrl++;
		}		
		return noOfBrokenUrl;
	}



}
