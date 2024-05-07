package cubes.test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;

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
	private static PostListPage postListPage;
	
	private static String postTitle;
	private static String postDescription;


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
		//driver.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc01TestLinkFromMenu() {
		checkMenuLink("Sliders list", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders");
		checkMenuLink("Add Slider", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders/add");
		checkMenuLink("Post Categories list", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories");
		checkMenuLink("Add Post Category", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories/add");
		checkMenuLink("Tags list", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags");
		checkMenuLink("Add Tag", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add");
		checkMenuLink("Posts list", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		checkMenuLink("Add Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		checkMenuLink("Comments List", "https://testblog.kurs-qa.cubes.edu.rs/admin/comments");
		checkMenuLink("Users List", "https://testblog.kurs-qa.cubes.edu.rs/admin/users");
		checkMenuLink("Add User", "https://testblog.kurs-qa.cubes.edu.rs/admin/users/add");	
	}

	@Test
	public void tc02TestNavigationLink() {
		checkNavigationLink("Home", "https://testblog.kurs-qa.cubes.edu.rs/admin");
	}
	
	@Test
	public void tc03TestAddEmptyAllFields() throws InterruptedException {
		postListPage.openPage();
		
		postListPage.clickOnAddNewPost();
		
		postFormPage.inputPostString("");
		
		postFormPage.inputDescriptionString("");
		
		postFormPage.clickSave();
		
	//	String errorMessage = postFormPage.getErrorMessage();

		assertEquals(postFormPage.getTitleInputErrorMessage(),"This field is required.");	
		assertEquals(postFormPage.getDescriptionInputErrorMessage(),"This field is required.");
	}

	@Test
	public void tc04TestAddPostWithShortTitle() throws InterruptedException{
		postListPage.openPage();
		
		postListPage.clickOnAddNewPost();

		postFormPage.inputPostString("Post1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("Please enter at least 20 characters.", errorMessage);	
	}
	
 	@Test
	public void tc05TestAddPostWithCorectTitleAndEmptyDescription() throws InterruptedException{
		postListPage.openPage();
		
		postListPage.clickOnAddNewPost();
		
		postFormPage.inputPostString("TesstTesstTesstTesst");
		
		postFormPage.inputDescriptionString("");
		
		postFormPage.clickSave();
	String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("This field is required.", errorMessage);	
		
		//assertEquals(postFormPage.getDescriptionInputErrorMessage(),"This field is required.");
	}
 
 	@Test
	public void tc06TestAddPostWithCorectTitleAndShortDescription() throws InterruptedException{
 		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("Test 1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("Please enter at least 50 characters.", errorMessage);	
	} 
 
	@Test
	public void tc07TestAddPostWithCorectTitleCorectDescriptionAndEmptyOthersFields() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("This field is required.", errorMessage);	
	}
	
	@Test
	public void tc08TestAddPostWithCorectTitleCorectDescriptionCheckTagsNameAndEmptyOthersFields() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.inputTagsString("");
		postFormPage.inputContentString("");	
		postFormPage.clickSave();
		
		assertEquals(postFormPage.getContentInputErrorMessage(),"The content field is required.");
	}
	@Test
	public void tc09TestAddPost() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.inputTagsString("");
		postFormPage.inputContentString("Text inside iframe");	
		postFormPage.clickSave();
	
		
		assertEquals(postListPage.isPostInList("Post1Post1Post1Post1Post1"), false);
	}

	@Test
	public void tc10TectCancel() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		
		postFormPage.inputPostString("Post test title Post test title");
		postFormPage.clickCancel();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
	}

	@Test
	public void tc11TestLogout() {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		
		postFormPage.clickProfile();
		postFormPage.clickLogout();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/login");
	}
	
		@Test
	public void tc12TestDeletePost() {
		postListPage.openPage();
		
		postListPage.clickOnDeletePost("Post1Post1Post1Post1Post1");
		postListPage.clickOnDialogDelete();
		
		assertEquals(postListPage.isPostInList("Post1Post1Post1Post1Post1"), false);
	}
		@Test
	public void tc13TestEditPost() throws InterruptedException {
		postListPage.openPage();
		
		postListPage.clickOnAddNewPost();
		
		Random random = new Random();
		
		postTitle = "TitleTitleTitleTitle "+random.nextInt(1000);	
		postDescription = "DescriptionDescriptionDescriptionDescriptionDescription "+random.nextInt(1000);
	
		postFormPage.inputPostString(postTitle);
		postFormPage.inputDescriptionString(postDescription);
		postFormPage.inputTagsString("");
		postFormPage.inputContentString("Text inside iframe");	
		
		postFormPage.clickSave();
		
		postListPage.clickOnEditPost(postTitle);
		
		postFormPage.inputPostString("NewNewNewNewNewNewNewNewNew");
		
		postFormPage.clickSave();
		
		assertEquals(postListPage.isPostInList("NewNewNewNewNewNewNewNewNew"), false);	
	}
	
	
	
	
	public void checkMenuLink(String title, String url) {
		postListPage.openLinkParentInMenu(title);
		
		postListPage.clickOnLinkInMenu(title);
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);
		
		postListPage.openPage();
	}
	
	public void checkNavigationLink(String title, String url) {
		postListPage.clickOnNavigationLink(title);
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);
		
		postListPage.openPage();
	}

}
