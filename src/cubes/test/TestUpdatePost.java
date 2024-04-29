package cubes.test;

import static org.testng.Assert.assertEquals;

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
public class TestUpdatePost {
	
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
		postListPage.deletePost(postTitle);
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
	public void tc1TestUpdateEmptyPostTitle() throws InterruptedException {
		postListPage.clickOnAddNewPost();
		
		postTitle = postFormPage.addNewRandomPost();
		
		postListPage.clickOnUpdatePost(postTitle);
		
		postFormPage.inputPostString("");
		
		postFormPage.clickSave();
		
	//	String error = postFormPage.getErrorMessage();
		
	//	assertEquals("This field is required.", error);	
	}
	
	@Test
	public void tc2TestUpdatePostWithExistingTitle() throws InterruptedException {
		postListPage.clickOnUpdatePost(postTitle);
		
		postFormPage.inputTagsString("zvejtidkjauiepmuljnspkbo");
		
		postFormPage.clickSave();
		
//		String error = postFormPage.getErrorMessage();
		
	//	assertEquals("The title has alredy exsist", error);
		
	}
	
	@Test
	public void tc3TestUpdatePostTitle() throws InterruptedException {
		
		postListPage.clickOnUpdatePost(postTitle);
		
		String newPostTitle = "New PosstPosstPosstPosst "+postFormPage.getPostString();
		
		postFormPage.inputPostString("newPostTitle");
		
		postFormPage.clickSave();
		
		assertEquals(true, postListPage.isPostInList(newPostTitle));

	}
	

}
