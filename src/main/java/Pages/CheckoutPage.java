package Pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    // payment page: "Change" link next to "Delivering to ..."
    @FindBy(xpath = "//a[@data-csa-c-slot-id='checkout-change-shipaddressselect']")
    private WebElement changeAddress;

    // address page: one label per address (the radio input is styled, so click the label)
    @FindBy(xpath = "//input[@name='destinationSubmissionUrl']/ancestor::label")
    private List<WebElement> addressOptions;

    // address page: "Deliver to this address" button
    @FindBy(xpath = "//span[@id='checkout-primary-continue-button-id']/descendant::input")
    private WebElement deliverToThisAddress;
    
    @FindBy(xpath = "//span[normalize-space()='Scan and Pay with']/ancestor::div[contains(@class,'a-fixed-left-grid-inner')]//input[@type='radio']")
    private WebElement SelectUPI;
    
    @FindBy(xpath = "//input[@data-csa-c-slot-id='checkout-primary-continue-payselect']")
    private WebElement useThisPayment;
    
    @FindBy(xpath = "(//input[@data-csa-c-slot-id='checkout-place-your-order-button'])[1]")
    private WebElement payWithUPI;
    
    @FindBy(xpath = "//button[@class=' a-button-close a-declarative a-button-top-right']")
    private WebElement clickNoThanks;
    

    public void clickChangeAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(changeAddress));
        changeAddress.click();
        wait.until(ExpectedConditions.urlContains("/address"));
    }

    // index 0 = first address, 1 = second address
    public void selectAddress(int index) {
        wait.until(d -> addressOptions.size() > index);

        WebElement option = addressOptions.get(index);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", option);
        option.click();

        // confirm that radio is now selected (fresh lookup each poll)
        wait.until(d -> d.findElements(By.xpath("//input[@name='destinationSubmissionUrl']"))
                         .get(index).isSelected());
    }

    public void clickDeliverToThisAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(deliverToThisAddress));
        deliverToThisAddress.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/address?")));
        
    }
    public void ClickUPIRadioButton() {
		wait.until(ExpectedConditions.elementToBeClickable(SelectUPI));
		SelectUPI.click();

	}
    public void UseSelectedPayment() {
		wait.until(ExpectedConditions.elementToBeClickable(useThisPayment));
		useThisPayment.click();
	}
    
    public void ClickNoThanks() {
		wait.until(ExpectedConditions.elementToBeClickable(clickNoThanks));
		clickNoThanks.click();
	}
    
    public void payWithUpi() {
		wait.until(ExpectedConditions.elementToBeClickable(payWithUPI));
		payWithUPI.click();
	}
//    public void payWithUpi() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("placeOrder")));
//        WebElement btn = driver.findElement(By.id("placeOrder"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
//    }
    //step 3
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.wait.ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }
}