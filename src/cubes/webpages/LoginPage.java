package cubes.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
	private static final String PAGE_URL="http://testblog.kurs-qa.cubes.edu.rs/login";
	//WebElements
	@FindBy(name="email")
	private WebElement weEmail;
	@FindBy(name="password")
	private WebElement wePassword;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement weButton;
	@FindBy(css = ".content-header h1")
	private WebElement weStarterPage;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	public void login(String email, String password) {
		weEmail.sendKeys(email);
		wePassword.sendKeys(password);
		weButton.click();
	}
	
	public void loginSuccess() {
		if (weStarterPage.isDisplayed()) {
			System.out.println("Uspesno ste se ulogovali!");
		}
		else {
			System.out.println("Niste se ulogovali. proverite email i password!");
		}
	}
}
