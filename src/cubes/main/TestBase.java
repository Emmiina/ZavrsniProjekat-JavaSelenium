package cubes.main;

import cubes.constants.Constants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import cubes.webpages.LoginPage;

import java.time.Duration;

public class TestBase {
	
	public static WebDriver driver;
	public static WebDriverWait wait;	
	public static LoginPage loginPage;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectLocation + "/lib/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		driver.quit();
	}
	
	@Before 
	public void setUp() throws Exception {
		loginPage = new LoginPage(driver);
		loginPage.login(Constants.email, Constants.password);
		loginPage.loginSuccess();
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		WebElement weProfile = driver.findElement(By.xpath("//i[@class='far fa-user']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weProfile);
		WebElement weLogout = driver.findElement(By.xpath("//a[@href='https://testblog.kurs-qa.cubes.edu.rs/logout']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weLogout);
	}
}
