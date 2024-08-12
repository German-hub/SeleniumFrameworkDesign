package elcoso.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import elcoso.TestComponents.BaseTest;
import elcoso.pageobjects.CartPage;
import elcoso.pageobjects.CheckoutPage;
import elcoso.pageobjects.ConfirmationPage;
import elcoso.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest{


		@Test
		public void loginErrorValidation() throws IOException, InterruptedException {
			
			landingPage.loginApplication("anshia@gmail.com", "Iamki000");
			Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());			
			
		}
		
		@Test
		public void productErrorValidation() throws IOException, InterruptedException {
			
			String productName = "ZARA COAT 3";
			
			ProductCatalog productCatalog = landingPage.loginApplication("elmoskito@coso.com","Pass123!");
			
			List<WebElement> products = productCatalog.getProductList();
			productCatalog.addProductTocart(productName);
			CartPage cartPage = productCatalog.goToCartPage();		

			Boolean match = cartPage.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			
			
		}
	}

	
