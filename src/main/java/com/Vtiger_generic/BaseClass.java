package com.Vtiger_generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import Pom_Classes.Homepage;
import Pom_Classes.Vtigerloginpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public   WebDriver driver;
	public static WebDriver sdriver;
	fileproperty property=new fileproperty();

	@BeforeSuite(groups= {"Smoke","Regression"})
	public void connectToDB() {
		System.out.println("====DB connection done");
	}
	@AfterSuite(groups= {"Smoke","Regression"})
	public void closeConnectToDB() {
		System.out.println("====DB connection closed");
	}
	@BeforeClass(groups= {"Smoke","Regression"})
	public void launchBrowser() throws Throwable {

		String value=property.readpropertydata("browser");
		if(value.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (value.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else {
			System.out.println("invalid browser");
		}
		sdriver=driver;
		driver.manage().window().maximize();
		driver.get(property.readpropertydata("url"));
		Thread.sleep(3000);
	}
	
	
	@AfterClass(groups= {"Smoke","Regression"})
	public void closeBrowser() throws Throwable {
		Webdriver_utility utility = new Webdriver_utility(driver);
		Thread.sleep(3000);
		utility.closeBrowser();
	}
	@BeforeMethod(groups= {"Smoke","Regression"})
	public void logionAppln() throws Throwable {

		Vtigerloginpage login=new Vtigerloginpage(driver);
		login.logintoapp(property.readpropertydata("username"), property.readpropertydata("password"));

		//		login.getUsername().sendKeys(property.readpropertydata("username"));
		//		login.getPassword().sendKeys(property.readpropertydata("password"));
		//		login.getLoginbutton().click();
	}
	@AfterMethod(groups= {"Smoke","Regression"})
	public void logoutAppln() throws Throwable {
		Homepage homepage = new Homepage(driver);
		homepage.getSignoutimage().click();
		homepage.getSignoutbuttonlink().click();
	}


}
