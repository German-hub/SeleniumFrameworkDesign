package elcoso.stepDefinitions;

import java.io.IOException;
import java.util.Arrays;
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
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApp();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {

		productCatalogue.addProductTocart(productName);
	}
	
	@When("^I add multiple products (.+) to Cart$")
	public void i_add_multiple_products_to_cart(String productName) throws InterruptedException {
		
		List<String> productList = Arrays.asList(productName.split(",\\s*"));
		
		for(String product : productList) {
			
			productCatalogue.addProductTocart(product);
		}
		
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		
		List<String> productList = Arrays.asList(productName.split(",\\s*"));
		
		CartPage cartPage = productCatalogue.goToCartPage();		
		
		for(String product : productList) {
			
			Boolean match = cartPage.verifyProductDisplay(product);
			Assert.assertTrue(match);
		}
		
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void error_message_is_displayed(String strArg) throws Throwable {

		Assert.assertEquals(strArg, landingPage.getErrorMessage());
		driver.close();
	}
}
