package elcoso.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import elcoso.pageobjects.CartPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	public void waitForElementToAppear(By element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));

	}
	
	public void waitForWebElementToAppear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public CartPage goToCartPage() {

		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public void waitForElementToDisappear(WebElement element) throws InterruptedException {

		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(elect));
	}

	public void scrollDownuntilElementAppears(WebElement element) throws InterruptedException {

        new Actions(driver)
                .scrollToElement(element)
                .perform();
	}
	

}
