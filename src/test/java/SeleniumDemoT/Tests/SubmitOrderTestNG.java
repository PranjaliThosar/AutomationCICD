package SeleniumDemoT.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumDemoT.TestComponants.BaseTests;
import SeleniumDemoT.pageobjects.CartPage;
import SeleniumDemoT.pageobjects.CheckoutPage;
import SeleniumDemoT.pageobjects.ConfirmationPage;
import SeleniumDemoT.pageobjects.OrderPage;
import SeleniumDemoT.pageobjects.ProductCatalog;

public class SubmitOrderTestNG extends BaseTests {
	
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap <String,String> input) throws IOException, InterruptedException
	{

		ProductCatalog productCatalog=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage= productCatalog.goToCartPage();

		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartPage.gotoCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


	}

	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalog productCatalog=landingPage.loginApplication("pranjali1@gmail.com", "Pranjali@123");
		OrderPage orderPage=productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderstDisplay(productName));
	}







	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap <String,String>map=new HashMap<String,String>();
//		map.put("email", "pranjali1@gmail.com");
//		map.put("password", "Pranjali@123");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap <String,String>map1=new HashMap<String,String>();
//		map.put("email", "DemoTest1@gmail.com");
//		map.put("password", "Pranjali@123");
//		map.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\SeleniumDemoT\\Data\\PurchaseOrder.json");
	    return new Object[][] {{data.get(0)},{data.get(1)}};

	}



}
