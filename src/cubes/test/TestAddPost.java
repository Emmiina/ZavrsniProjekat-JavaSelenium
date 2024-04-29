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
public class TestAddPost {
	
	private static ChromeDriver driver;
	private static LoginPage loginPage;
	private static PostFormPage postFormPage;
//	private static PostListPage postListPage;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Users/emina/Downloads/webDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofMillis(10000));
		
		loginPage = new LoginPage(driver);
		postFormPage = new PostFormPage(driver, driverWait);
//		postListPage = new PostListPage(driver, driverWait);
		loginPage.loginSuccess();	
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc1TestLinkFromMenu() {
		postFormPage.checkMenuLink("Sliders list", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders");
		postFormPage.checkMenuLink("Add Slider", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders/add");
		postFormPage.checkMenuLink("Post Categories list", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories");
		postFormPage.checkMenuLink("Add Post Category", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories/add");
		postFormPage.checkMenuLink("Tags list", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags");
		postFormPage.checkMenuLink("Add Tag", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add");
		postFormPage.checkMenuLink("Posts list", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		postFormPage.checkMenuLink("Add Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		postFormPage.checkMenuLink("Comments List", "https://testblog.kurs-qa.cubes.edu.rs/admin/comments");
		postFormPage.checkMenuLink("Users List", "https://testblog.kurs-qa.cubes.edu.rs/admin/users");
		postFormPage.checkMenuLink("Add User", "https://testblog.kurs-qa.cubes.edu.rs/admin/users/add");	
	}
	
	@Test
	public void tc2TestNavigationLink() {
		postFormPage.checkNavigationLink("Home", "https://testblog.kurs-qa.cubes.edu.rs/admin");
		postFormPage.checkNavigationLink("Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
	}
	
	@Test
	public void tc3TestAddEmptyPost() throws InterruptedException {
		postFormPage.inputPostString("");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("This field is required.", errorMessage);	
	}
	
	@Test
	public void tc4TestAddPostWithShortTitle() throws InterruptedException{
		postFormPage.inputPostString("Post1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("Please enter at least 20 characters.", errorMessage);	
	}
	
	@Test
	public void tc5TestAddPostWithCorectTitleAndShortDescription() throws InterruptedException{
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("Test 1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("Please enter at least 50 characters.", errorMessage);	
	}
	
	@Test
	public void tc6TestAddPostWithCorectTitleCorectDescriptionAndEmptyOthersFields() throws InterruptedException{
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("This field is required.", errorMessage);	
	}
	
	@Test
	public void tc7TestAddPostWithCorectTitleCorectDescriptionCheckTagsNameAndEmptyOthersFields() throws InterruptedException{
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.inputTagsString("");
		postFormPage.inputContentString("");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("The content field is required.", errorMessage);
		
	}
	@Test
	public void tc8tectCancel() throws InterruptedException {
		postFormPage.inputPostString("Post test title");
		postFormPage.clickCancel();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		
		postFormPage.openPage();
	}
	@Test
	public void tc9TestLogout() {
		postFormPage.clickProfile();
		postFormPage.clickLogout();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/login");
	}
	
	

}
