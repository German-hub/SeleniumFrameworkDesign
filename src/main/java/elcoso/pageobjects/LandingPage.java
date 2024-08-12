package elcoso.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import elcoso.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {		

		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//WebElement userEmail = driver.findElement(By.id("userPassword"));
	//WebElement userEmail = driver.findElement(By.id("login"));


	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;	

	@FindBy(id="userPassword")
	WebElement password;

	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalog loginApplication (String email, String pass) {

		userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver); 
		return productCatalog;
	}

	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}
}
