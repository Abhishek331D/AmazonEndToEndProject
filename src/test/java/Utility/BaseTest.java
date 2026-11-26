package Utility;


import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest extends ListenerLogic {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	@Parameters("Browser")
	@BeforeMethod
	public void SelectBrowse(String NameOfBrowser)  {
		if(NameOfBrowser.equals("Edge")) {
			driver=new EdgeDriver();
		}
		if(NameOfBrowser.equals("Chrome")) {
			driver=new ChromeDriver();
		}
		if(NameOfBrowser.equals("Firefox")) {
			driver=new FirefoxDriver();
		}
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.titleIs(driver.getTitle()));
	}
	@AfterMethod
	public void QuitBrowse() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		
	}
	

}
