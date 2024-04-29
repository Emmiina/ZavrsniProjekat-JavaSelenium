package cubes.main;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAddPost {
	
	private static ChromeDriver driver;
	private static 	String urlLoginPage = "http://testblog.kurs-qa.cubes.edu.rs/login";
	private static WebDriverWait wait;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/emina/Downloads/webDriver/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		
		driver.get(urlLoginPage);
		driver.manage().window().maximize();
		
		WebElement weEmail = driver.findElement(By.name("email"));
		WebElement wePassword = driver.findElement(By.name("password"));
		WebElement weButton = driver.findElement(By.xpath("//button[@type='submit']"));
		
		weEmail.sendKeys("kursqa@cubes.edu.rs");
		wePassword.sendKeys("cubesqa");
		weButton.click();
		
		WebElement weStarterPage = driver.findElement(By.xpath("//h1[@class='m-0 text-dark']"));
		
		if (weStarterPage.getText().equalsIgnoreCase("Starter Page")) {
			System.out.println("Uspesno ste se ulogovali!");
		}
		else {
			System.out.println("Niste se ulogovali. proverite email i password!");
		}
	}

	@After
	public void tearDown() throws Exception {
	/*	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='toast-message']"))));
		WebElement weProfile = driver.findElement(By.xpath("//i[@class='far fa-user']"));
		weProfile.click();
		
		WebElement weLogout = driver.findElement(By.xpath("//a[@href='https://testblog.kurs-qa.cubes.edu.rs/logout']"));
		weLogout.click();*/
	}

	@Test
	public void testAddPost() throws InterruptedException {
		driver.navigate().to("https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		
		WebElement wePostTitle = driver.findElement(By.name("title"));
		
		String postTitle = "Post title 1";
		
		wePostTitle.sendKeys(postTitle);
		
		WebElement weButtonSave = driver.findElement(By.xpath("//button[@type='submit']"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weButtonSave);
		Thread.sleep(3000);
		weButtonSave.click();
	}
	
	@Test
	public void testDeletePost(String postTitle) {
		driver.navigate().to("https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		
		WebElement weDeleteButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button"));
		weDeleteButton.click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i[@class='fas fa-trash']"))));
		
		driver.findElement(By.xpath("//i[@class='fas fa-trash']")).click();
		
		
	}
	
	
	

}
