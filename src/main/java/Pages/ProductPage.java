package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



//public class ProductPage extends MultipleSearchData{
public class ProductPage{
	
	WebDriver driver;
	WebDriverWait wait;
	public static String searchTabHandle;
	//Find SearchBox
	@FindBy(id = "twotabsearchtextbox")
	private WebElement search;
	
	//Filter price
	@FindBy(xpath = "//ul[@id='filter-p_36']//a")
    List<WebElement> priceRanges;
	
	//ClickOnProduct
	@FindBy(xpath = "//a[@class='a-link-normal s-no-outline']")
	private List<WebElement> clickOnProd;
	
//	//Find wishList
	@FindBy(css = "#add-to-wishlist-button-submit")
	private WebElement wishList;
	
	@FindBy(id = "add-to-cart-button")
	private WebElement addToCart;

	@FindBy(xpath = "//div[@id='inline-twister-expander-content-size_name']//span[contains(@class,'swatch-title-text')] | //ul[contains(@class,'swatches')]//li[not(contains(@class,'unavailable'))]//button")
	private List<WebElement> availableSizes;
	
	
	//closeWishListPopup
	@FindBy(xpath = "//button[@class=' a-button-close a-declarative']/i")
	private WebElement closeWishListpop;
	
//	public void SearchProduct(String items) 
//	{
//	    wait.until(ExpectedConditions.elementToBeClickable(search));
//	    search.sendKeys(items+Keys.ENTER);
//
//	}
	public void SearchProduct() 
	{
	    wait.until(ExpectedConditions.elementToBeClickable(search));
	    search.sendKeys("shoes"+Keys.ENTER);

	}
	
	public void clickProduct() {
		      // first tab
	    String parent = driver.getWindowHandle();          // 1. remember the current tab
	    wait.until(ExpectedConditions.elementToBeClickable(clickOnProd.get(6)));
	    clickOnProd.get(6).click();                        // 2. click the product (opens new tab)

	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));   // 3. wait for the new tab

	    for (String handle : driver.getWindowHandles()) {  // 4. switch to the new tab
	        if (!handle.equals(parent)) {
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	    searchTabHandle = driver.getWindowHandle();
	}
	public void wichList() {
		wait.until(ExpectedConditions.elementToBeClickable(wishList));
		wishList.click();
	}
	public void closeWishlistPopup() {
	    wait.until(ExpectedConditions.elementToBeClickable(closeWishListpop));
	    closeWishListpop.click();
	 
	}
	
	
	
	// print all ranges (use once to find the index you want)
    public void printPriceRanges() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//ul[@id='filter-p_36']//a"), 0));
        for (int i = 0; i < priceRanges.size(); i++) {
            System.out.println(i + " -> " + priceRanges.get(i).getText());
        }
    }
 // click a price range by index
    public void filterPrize(int index) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//ul[@id='filter-p_36']//a"), 0));
        WebElement range = priceRanges.get(index);
        range.click();
        // wait until the old page is refreshed
        wait.until(ExpectedConditions.stalenessOf(range));
    }
    public void selectSizeIfPresent() {
	    // sizes may not exist for non-apparel products, so don't fail if none
	    List<WebElement> sizes = driver.findElements(By.xpath(
	        "//div[@id='variation_size_name']//li[not(contains(@class,'swatchUnavailable'))]//*[self::button or self::span][@role='button' or self::button]"));
	    if (!sizes.isEmpty()) {
	        wait.until(ExpectedConditions.elementToBeClickable(sizes.get(0)));
	        sizes.get(0).click();
	    }
	}

	public void addToCart() {
	    selectSizeIfPresent();
//	    WebElement addToCart=driver.findElement(By.id("add-to-cart-button"));
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
	    addToCart.click();

	    
	}

   
	//step 3
    	public ProductPage(WebDriver driver) {
		this.driver = driver;                                        // assign the field
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // create the wait here
        
		PageFactory.initElements(driver, this);
	}
}