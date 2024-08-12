package elcoso.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import elcoso.TestComponents.BaseTest;
import elcoso.pageobjects.CartPage;
import elcoso.pageobjects.CheckoutPage;
import elcoso.pageobjects.ConfirmationPage;
import elcoso.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {



		ProductCatalog productCatalogue = landingPage.loginApplication(input.get("email"), input.get("pass"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@DataProvider
	public Object[][] getData() {

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "elmoskito@coso.com");
		map1.put("pass", "Pass123!");
		map1.put("product", "ZARA COAT 3");

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "shetty@gmail.com");
		map2.put("pass", "Iamking@000");
		map2.put("product", "IPHONE 13 PRO");

		return new Object[][] {{map1},{map2}};

	}

	//@DataProvider
	//public Object[][] getData() {

	//	return new Object[][] {{"elmoskito@coso.com", "Pass123!", "ZARA COAT 3"},{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
	//}
}
