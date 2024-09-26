package SeleniumDemoT.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumDemoT.TestComponants.BaseTests;
import SeleniumDemoT.TestComponants.Retry;
import SeleniumDemoT.pageobjects.CartPage;
import SeleniumDemoT.pageobjects.ProductCatalog;


	public class ErrorValidation extends BaseTests{

		@Test(groups= {"ErrorHandling"})//, retryAnalyzer=Retry.class
		public void loginErrorValidation() throws IOException, InterruptedException
		{
	        String productName="ZARA COAT 3";
			landingPage.loginApplication("pranjali1", "Pranjali@123");
			Assert.assertEquals("Incorrect email password",landingPage.getErrorMessage());

		}




		@Test
		public void productErrorValidation() throws IOException, InterruptedException
		{
	       // String productName="ZARA COAT 3";
	        String productName1="ZARA COAT 3";
			ProductCatalog productCatalog=landingPage.loginApplication("DemoTest1@gmail.com", "Pranjali@123");
			List<WebElement> products=productCatalog.getProductList();
			//productCatalog.addProductToCart(productName);
			CartPage cartPage= productCatalog.goToCartPage();

			Boolean match=cartPage.VerifyProductDisplay(productName1);
			Assert.assertTrue(match);
		}
	

   }

