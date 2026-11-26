package Pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	//step 1
	//Account & List
	@FindBy(xpath="//span[@class='nav-line-2 ']")
	private WebElement AccountAndList;
	
	//Find sign button
	@FindBy(xpath="//a[@class='nav-action-signin-button']")
	private WebElement Sign;
	
	//Find signOut 
	@FindBy(xpath = "//span[text()='Sign Out']")
	private WebElement signout;
	

	
	
	//step 2
	public void hoverOverAccAndList(WebDriver driver) {
		Actions a1=new Actions(driver);
		a1.moveToElement(AccountAndList).perform();
	}
	public void ClickSignbutton() {
		wait.until(ExpectedConditions.elementToBeClickable(Sign));
		Sign.click();
	}
	
	public void ClickSignOutbutton() {
		wait.until(ExpectedConditions.elementToBeClickable(signout));
		signout.click();
		
	}


	//step 3
	public HomePage(WebDriver driver) {
		this.driver = driver;                                        // assign the field
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // create the wait here
		PageFactory.initElements(driver, this);
	}
}

