package LoginPageOR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageOR
{
	WebDriver driver;
	
	public static final By userName_TextBox=By.xpath("//input[@id='userIds']");
	public static final By password_TextBox=By.xpath("//input[@name='password']");
	public static final By login_Button=By.xpath("//button[@id='clickme'][@type='submit']");

	String userName="MAGICGLASS";
	String passWord="Magic1234";
	public LoginPageOR(WebDriver driver) {
		this.driver=driver;
	}
	public void logInMethod() throws InterruptedException {
		driver.findElement(userName_TextBox).sendKeys(userName);
		driver.findElement(password_TextBox).sendKeys(passWord);
		Thread.sleep(4000);
		driver.findElement(login_Button).click();
	}
}


