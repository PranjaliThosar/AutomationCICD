package SeleniumDemoT.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumDemoT.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(css=".action__submit")
	WebElement submit;

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;

	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement SelectCountry;

	By result=By.cssSelector(".ta-results");

	public void selectCountry(String countryname )
	{
		Actions a=new Actions(driver);
		a.sendKeys(Country, countryname).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		SelectCountry.click();

	}
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

}
