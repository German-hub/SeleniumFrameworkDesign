package elcoso.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import elcoso.TestComponents.BaseTest;
import elcoso.pageobjects.CartPage;
import elcoso.pageobjects.CheckoutPage;
import elcoso.pageobjects.ConfirmationPage;
import elcoso.pageobjects.LandingPage;
import elcoso.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApp();
		//code
	}

	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		 confirmationPage = checkoutPage.submitOrder();
	}
	

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg) throws Throwable {
   
    	Assert.assertEquals(strArg, landingPage.getErrorMessage());
    	driver.close();
    }
}
