
package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "nav-cart")
    private WebElement clickCartIocn;

    // lists, because a cart can have several items (no @CacheLookup!)
    @FindBy(xpath = "//button[@data-a-selector='increment']")
    private List<WebElement> incrementBtns;

    @FindBy(xpath = "//button[@data-a-selector='decrement']")
    private List<WebElement> decrementBtns;

    @FindBy(xpath = "//fieldset[@name='sc-quantity']")
    private List<WebElement> quantityBoxes;

    @FindBy(xpath = "//span[@data-action='delete-active']")
    private List<WebElement> deleteBtns;

   

    public void clickOnCartPageIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(clickCartIocn));
        clickCartIocn.click();
        wait.until(ExpectedConditions.urlContains("/cart"));
    }

    // ---------- helpers ----------
    public int getItemCount() {
        return deleteBtns.size();            // re-finds every call
    }

    // one JS call = no stale element between find and read
    public int getQuantity(int itemIndex) {
        for (int i = 0; i < 5; i++) {
            try {
                return Integer.parseInt(
                    quantityBoxes.get(itemIndex).getAttribute("data-steppervalue").trim());
            } catch (StaleElementReferenceException e) {
                // row re-rendered, try again
            }
        }
        throw new IllegalStateException("Quantity not found for item " + itemIndex);
    }

    

    // ---------- increase ----------
    public void increaseQuantity(int itemIndex) {
        int before = getQuantity(itemIndex);

        wait.until(d -> incrementBtns.size() > itemIndex);
        wait.until(d -> incrementBtns.get(itemIndex).isEnabled());
        incrementBtns.get(itemIndex).click();

        wait.until(d -> getQuantity(itemIndex) == before + 1);
    }

//    // ---------- decrease ----------
//    public void decreaseQuantity(int itemIndex) {
//        int before = getQuantity(itemIndex);
//        if (before <= 1) return;             // at 1 the minus becomes a delete icon
//
//        wait.until(d -> decrementBtns.size() > itemIndex);
//        decrementBtns.get(itemIndex).click();
//
//        wait.until(d -> getQuantity(itemIndex) == before - 1);
//    }
    
    public void decQ() {
		// TODO Auto-generated method stub
		WebElement dec =driver.findElement(By.xpath("(//button[@data-a-selector='decrement'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(dec));
		dec.click();
	}
    @FindBy(name = "proceedToRetailCheckout")
    private WebElement proceedToBuy;

    public void clickProceedToBuy() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToBuy));
        proceedToBuy.click();
        wait.until(ExpectedConditions.urlContains("/checkout"));
    }
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.wait.ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }

	
}