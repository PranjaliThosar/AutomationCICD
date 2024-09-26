package SeleniumDemoT.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumDemoT.pageobjects.CartPage;
import SeleniumDemoT.pageobjects.CheckoutPage;
import SeleniumDemoT.pageobjects.ConfirmationPage;
import SeleniumDemoT.pageobjects.LandingPage;
import SeleniumDemoT.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder {
	
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
        String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		LandingPage landingpage=new LandingPage(driver);
		landingpage.goTo();
		ProductCatalog productCatalog=landingpage.loginApplication("pranjali1@gmail.com", "Pranjali@123");

		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage= productCatalog.goToCartPage();

		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartPage.gotoCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
