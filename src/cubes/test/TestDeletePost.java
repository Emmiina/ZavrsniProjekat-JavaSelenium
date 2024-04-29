package cubes.test;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.webpages.LoginPage;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeletePost {
	
	private static ChromeDriver driver;
	private static LoginPage loginPage;
	private static PostFormPage postFormPage;
	private static PostListPage postListPage;
	
	private static String postTitle;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Users/emina/Downloads/webDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofMillis(10000));
		
		loginPage = new LoginPage(driver);
		postFormPage = new PostFormPage(driver, driverWait);
		postListPage = new PostListPage(driver, driverWait);
		
		loginPage.loginSuccess();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		postListPage.openPage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc1TestCancelOnDelete() {
		postListPage.clickOnAddNewPost();
		
		postTitle = postFormPage.addNewRandomPost();
		
		postListPage.clickOnDeletePost(postTitle);
		
		postListPage.clickOnDialogCancel();
		
		String expectedPostTitle = postListPage.checkPost(postTitle);
		
		assertEquals(postTitle, expectedPostTitle);
	}
/*	@Test
	public void tc2TestDeletePost() {
		postListPage.clickOnAddNewPost();
		
		String postTitle = postFormPage.addNewRandomPost();
		
		postListPage.clickOnDeletePost(postTitle);
		
		postListPage.clickOnDialogDelete();
		
		assertEquals(false, postListPage.isPostInList(postTitle));
		
	}*/
	@Test
	public void tc2TestDeletePost() {
		
		postListPage.clickOnDeletePost(postTitle);
		
		postListPage.clickOnDialogDelete();
		
		assertEquals(false, postListPage.isPostInList(postTitle));
		
	}

}
