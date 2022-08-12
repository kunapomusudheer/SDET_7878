package com.Vtiger_generic;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Webdriver_utility {
	WebDriver driver;
	//public static WebDriver sdriver;

	public Webdriver_utility(WebDriver driver) {
		this.driver=driver;
	}

	public void maximize() {
		driver.manage().window().maximize();
	}
	public void pageloadtimeout() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void waitankclick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	public void DropdownbyIndex(WebElement element,int index  ) {
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	public void DropdownbyVisibletext(WebElement element,String text  ) {
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}
	public void DropdownbyValue(WebElement element,String target ) {
		Select select=new Select(element);
		select.selectByValue(target);
	}
	public void mouseHandle(WebElement ele1) {
		Actions act=new Actions(driver);
		act.moveToElement(ele1).click().build().perform();
		//ele1.click();
	}
	public void alertAccept() {
		Alert alt=driver.switchTo().alert();
		alt.accept();

	}
	public void alertDismiss() {
		Alert alt=driver.switchTo().alert();
		alt.accept();


	}
	public void refresh() {
		driver.navigate().refresh();
	}
	public void switchchildWindow() {
		String mainid = driver.getWindowHandle();
		Set<String> Allid = driver.getWindowHandles();
		for(String i:Allid) {
			if(!mainid.equals(i))  {
				driver.switchTo().window(i);
			}
		}
	}
	public void switchtoMainWindow() {
		driver.getWindowHandle();

	}
	public int random() {
		Random random=new Random();
		int rnumber = random.nextInt(9000);
		return rnumber;

	}
	public void closeBrowser() {
		driver.close();
	}

	public void takesscreenshot(String name) throws IOException {
		//  sdriver=driver;
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconsts.screenshotpath+name+Fakedata.number+".png";
		File dest = new File(path);
		Files.copy(src, dest);
		//return path;

	}

}
