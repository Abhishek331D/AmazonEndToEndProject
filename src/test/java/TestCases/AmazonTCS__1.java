package TestCases;


import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import Pages.CartPage;
import Pages.CheckoutPage;
//import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
import Utility.BaseTest;
import Utility.ListenerLogic;
import Utility.RetryLogic;
@Listeners(ListenerLogic.class)
public class AmazonTCS__1 extends BaseTest{
//	    @Test(dataProviderClass = MultipleSearchData.class,dataProvider = "SearchItems",retryAnalyzer = RetryLogic.class)
	    @Test(retryAnalyzer = RetryLogic.class)
//		public void AmazonEndToEnd(String items) throws InterruptedException {
	    	public void AmazonEndToEnd() throws InterruptedException {
			SoftAssert s1 =new SoftAssert();
		
			Reporter.log("Website Luanched Successfully");
			
			HomePage homepage=new HomePage(driver);
			
			LoginPage loginpage=new LoginPage(driver);
			
			ProductPage productpage=new ProductPage(driver);
			
			CartPage cartpage=new CartPage(driver);
			
			s1.assertEquals(driver.findElement(By.xpath("//a[@id='nav-logo-sprites']")).isDisplayed(), true);
			Reporter.log("Verified Homepage displayed");
			homepage.hoverOverAccAndList(driver);
			Reporter.log("HoveroverOnAcc&List");
			homepage.ClickSignbutton();
			Reporter.log("Cick on sign button successfull");
			
			
			loginpage.enterUserName();
			Reporter.log("Entered Emailid successfull");
			loginpage.ClickOnContinubutton();
			Reporter.log("Cick on Continue button successfull");
			loginpage.enterPassword();
			Reporter.log("Enter Password successfull");
			loginpage.ClickOnSignButton();
			Reporter.log("Cick on sign button successfull");
			s1.assertEquals(driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).getText(), "Hello, Abhishek");
			Reporter.log("Verified login Successfully");
			s1.assertEquals(driver.getTitle(), "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
			
			

			productpage.SearchProduct();

			productpage.printPriceRanges();
			

			productpage.filterPrize(1);
			
			s1.assertEquals(driver.findElements(By.xpath("//ul[@id='filter-p_36']/descendant::a")).get(1).isDisplayed(),true);
			Reporter.log("Verified Apply filter Search on product prize b/w range "+driver.findElements(By.xpath("//ul[@id='filter-p_36']/descendant::a")).get(1).getText());
			

			productpage.clickProduct();

			
			s1.assertEquals(driver.findElement(By.xpath("//span[@id='productTitle']")).isDisplayed(),true);
			Reporter.log("Verified product title"+driver.findElement(By.xpath("//span[@id='productTitle']")).getText());


			productpage.wichList();
			s1.assertEquals(driver.findElement(By.xpath("//input[@aria-label='Continue shopping']")).isDisplayed(), true);
			Reporter.log("Verifird add to wishlist done");

			productpage.closeWishlistPopup();
			
			s1.assertEquals(driver.findElement(By.id("submit.add-to-cart-announce")).getText(), "Add to cart");
			productpage.selectSizeIfPresent();
			productpage.addToCart();
			

			cartpage.clickOnCartPageIcon();

			int qtyBefore = cartpage.getQuantity(0);
			cartpage.increaseQuantity(0);
			s1.assertEquals(cartpage.getQuantity(0), qtyBefore + 1);
			Reporter.log("Verified quantity increased");
			
			cartpage.decQ();
			cartpage.clickProceedToBuy();
			Reporter.log("Clicked Proceed to Buy, checkout page opened");

			CheckoutPage checkoutpage = new CheckoutPage(driver);

			checkoutpage.clickChangeAddress();
			Reporter.log("Clicked Change address");

			checkoutpage.selectAddress(1);                 // 2nd address
			Reporter.log("Selected 2nd address");

			checkoutpage.clickDeliverToThisAddress();
			Reporter.log("Clicked Deliver to this address");
			
			checkoutpage.ClickUPIRadioButton();
			checkoutpage.UseSelectedPayment();
			checkoutpage.ClickNoThanks();
			checkoutpage.payWithUpi();

			// 1. Give control back to the first (homepage/search) tab
			String checkoutTab = driver.getWindowHandle(); // the tab we are in now
			System.out.println(checkoutTab);
			Set<String> s2=driver.getWindowHandles();
			System.out.println(s2);
			
			for (String handle : driver.getWindowHandles()) {
				System.out.println(handle);
			    if (!handle.equals(checkoutTab)) {
			        driver.switchTo().window(handle);               // switch to the other tab
			        break;
			    }
			}
			Reporter.log("Control given back to homepage tab");

			// 2. Scroll up so the nav bar is visible
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0)");
			Reporter.log("Scrolled up to nav bar");

			// 3. Your existing logic
			homepage.hoverOverAccAndList(driver);
			Reporter.log("HoveroverOnAcc&List successfull");

			homepage.ClickSignOutbutton();
			Reporter.log("Click on signout button successfull");

			Assert.assertEquals(loginpage.VerifySignOut(), "Sign in or create account");
			Reporter.log("Verified signout successfully done");

			s1.assertAll();
			
			                 		
		}
}

