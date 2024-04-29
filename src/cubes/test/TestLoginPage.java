package cubes.test;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.webpages.LoginPage;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;

public class TestLoginPage {
	
	private static ChromeDriver driver;
	private static WebDriverWait wait;
	private static LoginPage loginPage;
	private static PostFormPage postFormPage;
	private static PostListPage postListPage;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Users/emina/Downloads/webDriver/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginPage = new LoginPage(driver);
		postFormPage = new PostFormPage(driver, wait);
		postListPage = new PostListPage(driver, wait);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddAndDeletePost() {
		loginPage.loginSuccess();
		postFormPage.addNewPost("Test Post");
		postListPage.deletePost("Test Post");
	}

}
