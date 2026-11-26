package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
		WebDriver driver;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//step 1
		@FindBy(id="ap_email_login")
		private WebElement UserName;
		
		@FindBy(id="continue")
		private WebElement ContinueButton;
		
		@FindBy(id="ap_password")
		private WebElement Password;
		
		@FindBy(id="signInSubmit")
		private WebElement Sign;
		
		@FindBy(xpath = "//div[@id='claim-collection-container']/h1")
		private WebElement signoutTitle;
		
		//step 2
		public void enterUserName() {
			wait.until(ExpectedConditions.visibilityOf(UserName));
			UserName.sendKeys("abhishek2003d@gmail.com");
		}
		public void ClickOnContinubutton() {
			wait.until(ExpectedConditions.elementToBeClickable(ContinueButton));
			ContinueButton.click();
		}
		public void enterPassword() {
			wait.until(ExpectedConditions.visibilityOf(Password));
			Password.sendKeys("Abhishek##123");
		}
		public void ClickOnSignButton() {
			wait.until(ExpectedConditions.elementToBeClickable(Sign));
			Sign.click();
		}
		
		public String VerifySignOut() {
			String title=signoutTitle.getText();
			return title;
		}
		//step 3
		public LoginPage(WebDriver driver) {
			this.driver = driver;                                        // assign the field
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // create the wait here
			PageFactory.initElements(driver, this);
		}
//		
}
