package CommonFunLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	static WebDriver driver;

	// method for launching Browser
	public static WebDriver startBrowser() throws Throwable {
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E:\\Selenium_Evening\\ERP_Stock\\CommonDriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();
		}
		return driver;
	}

	// launch URL
	public static void openApplication(WebDriver driver) throws Throwable {
		driver.get(PropertyFileUtil.getValueForKey("Url"));
		driver.manage().window().maximize();
		System.out.println("Executing openApplication method");
	}

	// method for wait element
	public static void waitForElement(WebDriver driver, String locatortype, String locatorvalue, String timewait) {
		WebDriverWait mywait = new WebDriverWait(driver, Integer.parseInt(timewait));
		if (locatortype.equalsIgnoreCase("id")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		} else if (locatortype.equalsIgnoreCase("name")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		} else if (locatortype.equalsIgnoreCase("xpath")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}
	}

	// method for close browser
	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	// type action method
	public static void typeAction(WebDriver driver, String locatortype,String locatorvalue,String testdata)
	{
	if(locatortype.equalsIgnoreCase("id"))
	{
	driver.findElement(By.id(locatorvalue)).clear();
	driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
	}
	else if(locatortype.equalsIgnoreCase("name")){
		driver.findElement(By.name(locatorvalue)).clear();
		driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
	}
	else if (locatortype.equalsIgnoreCase("xpath"))
	{
	driver.findElement(By.xpath(locatorvalue)).clear();
	driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
	}
	}
	//click action method
	public static void clickAction(WebDriver driver, String locatortype, String locatorvalue){
	if(locatortype.equalsIgnoreCase("id")){
	driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
	}
	else if(locatortype.equalsIgnoreCase("name")){
		driver.findElement(By.name(locatorvalue)).click();
	}
	else if(locatortype.equalsIgnoreCase("xpath")){
	driver.findElement(By.xpath(locatorvalue)).click();
	}
		
	}
	
	public static String generateDate(){
		Date date= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
	}
	
	
	
}

