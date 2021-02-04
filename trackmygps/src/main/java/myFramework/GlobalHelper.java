package myFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GlobalHelper {
	public Properties pro;
	public WebDriver driver;
	public Logger log = Logger.getLogger("devpinoyLogger");
	public static String str_todaysDateTime = new SimpleDateFormat("dd-MM-YYYY HH-mm-ss").format(new Date());
	
	@BeforeSuite
	public void configRead() throws IOException {
		System.setProperty("current.date.time", str_todaysDateTime);
		readProperties("log4j");
		readProperties("config");
	}
	public void readProperties(String fileName) throws IOException {

		File file = new File(".\\src\\main\\java\\myFramework\\" + fileName + ".properties");
		FileInputStream fis = new FileInputStream(file);

		pro = new Properties();
		pro.load(fis);

		if (fileName.equals("log4j")) {
			PropertyConfigurator.configure(pro);
		}
		else if(fileName.equals("config")){
			PropertyConfigurator.configure(pro);
		}
	}

	public void openBrowser(String browserName)
	{
		if(browserName.equalsIgnoreCase(pro.getProperty("test_chrome_browser")))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			log.info("Chrome Browser has been started");
		}
		else if(browserName.equalsIgnoreCase(pro.getProperty("test_firefox_browser")))
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options=new FirefoxOptions();
			driver=new FirefoxDriver(options);
			log.info("Firefox Browser has been started");
		}
		else
		{
			System.out.println("Invalid Input");
		}
	}
	public void openUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		log.info(url+" has been opened");
	}
	public void highlightEle(WebElement elementToHighlight) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border:2px solid red;')",
				elementToHighlight);
	}
	public void clickon(By object) {
		WebElement ele=waitForVisibility(object);
		highlightEle(ele);
		ele.click();
		log.info("Clicked on "+ele.getText());
	}
	public void sendText(By object, String value) {
		WebElement ele=waitForVisibility(object);
		highlightEle(ele);
		ele.sendKeys(value);
		log.info("Entered into Textbox "+value);
	}
	public WebElement waitForVisibility(By locator) {
		WebDriverWait wt=new WebDriverWait(driver, 60);
		return wt.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	}
	public void checkBox(By locator) {
		WebElement ele=driver.findElement(locator);
		boolean status=ele.isSelected();
		if(status==false) {
			ele.click();
			log.info("Checkbox operation Performed on"+ele.getText());
		}
		
	}
	public void isDisplayed_Method(By locator) {
		WebElement ele=waitForVisibility(locator);
		Boolean status=ele.isDisplayed();
		if(status) {
			System.out.println("Site opened Successfully");
		}
		else {
			System.out.println("Site not opened");
		}
	}
	public void dragAndDropMethod(By source,By destination)
	{
		WebElement sourceElement=driver.findElement(source);
		highlightEle(sourceElement);
		WebElement destinationElement=driver.findElement(destination);
		highlightEle(destinationElement);
		Actions act=new Actions(driver);
		act.dragAndDrop(sourceElement,destinationElement).build().perform();
		log.info("Drag and Drop Performed");
	}
	public void switchToFrame(By locator)
	{
		WebElement ele=waitForVisibility(locator);
		driver.switchTo().frame(ele);
		log.info("Switched to next frame "+ele.getText());
	}
	public void dropDownMethod(By locator,String value)
	{
		WebElement ele=waitForVisibility(locator);
		highlightEle(ele);
		Select sl=new Select(ele);
		sl.selectByValue(value);
		log.info("Dropdown performed");
	}
	public void close() {
		driver.close();
		log.info("Browser has been closed");
	}
	public  String capture_screenShotMethod(String screenshotName) throws IOException
	{

		File source=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshotName=screenshotName+str_todaysDateTime;
		File destination=new File(".\\Results\\Screenshots\\"+screenshotName+"_"+str_todaysDateTime+".jpeg");
		FileHandler.copy(source, destination);
		return screenshotName;
	}
//
//	@AfterMethod
//	public void afterMethod(ITestResult result) throws IOException {
//		if(result.getStatus()==ITestResult.FAILURE) {
//			capture_screenShotMethod(result.getName());
//		}
//	}















}
