package Functions;

import myFramework.GlobalHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import LoginPageOR.LoginPageOR;
public class Test1 extends GlobalHelper{

	@Test(priority = 1)
	public void loginTest() throws InterruptedException {
		openBrowser("chrome");
		openUrl("http://trackmygps.co.in/gps/public/login");
		LoginPageOR login=new LoginPageOR(driver);
		login.logInMethod();
		//Thread.sleep(10000);
	}
	@Test(priority = 2)
	public void dropDownTest() throws InterruptedException {
		dropDownMethod(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul[2]/li[1]/select"), "ALL");
		List<WebElement> list=driver.findElements(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul[2]/li[2]/ul/li"));
		System.out.println("Vehicle Details");
		for(WebElement ele:list) {
			System.out.println(ele.getText());
		}
	}
	@Test(priority = 3)
	public void search() throws InterruptedException {
		String vehicleNumber="MH-42-AQ-216";
		driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul[2]/li[1]/div/input")).sendKeys(vehicleNumber);
		
			}

}