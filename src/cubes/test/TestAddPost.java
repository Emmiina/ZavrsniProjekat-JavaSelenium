package cubes.test;

import static org.testng.Assert.assertEquals;

import cubes.constants.Constants;
import cubes.main.TestBase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAddPost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);;

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
		postFormPage.openPage();
		postFormPage.checkNavigationLink("Home", Constants.starterPageUrl);
		postFormPage.openPage();
		postFormPage.checkNavigationLink("Post", Constants.postsPageUrl);
	}
	
	@Test
	public void tc3TestAddEmptyPost() throws InterruptedException {
		postFormPage.openPage();
		postFormPage.inputPostString("");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage("title");
		
		assertEquals(errorMessage, "This field is required.");
	}
	
	@Test
	public void tc4TestAddPostWithShortTitle() throws InterruptedException{
		postFormPage.openPage();
		postFormPage.inputPostString("Post1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage("title");
		
		assertEquals(errorMessage, "Please enter at least 20 characters.");
	}
	
	@Test
	public void tc5TestAddPostWithCorrectTitleAndShortDescription() throws InterruptedException{
		postFormPage.openPage();
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("Test 1");
		postFormPage.clickSave();
		
		String errorMessageDescription = postFormPage.getErrorMessage("description");

		assertEquals(errorMessageDescription, "Please enter at least 50 characters.");
	}
	
	@Test
	public void tc6TestAddPostWithCorrectTitleCorrectDescriptionAndEmptyOthersFields() throws InterruptedException{
		postFormPage.openPage();
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage("tags");
		
		assertEquals(errorMessage, "This field is required.");
	}
	
	@Test
	public void tc7TestAddPostWithCorrectTitleCorrectDescriptionCheckTagsNameAndEmptyOthersFields() throws InterruptedException{
		postFormPage.openPage();
		postFormPage.inputPostString("Post1Post1Post1Post1Post1");
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.clickTags();
		postFormPage.clickSave();

		String errorMessageContent = postFormPage.getErrorMessage("content");

		assertEquals(errorMessageContent, "The content field is required.");
	}

	@Test
	public void tc8TestCancel() throws InterruptedException {
		postFormPage.openPage();
		postFormPage.inputPostString("Post test title");
		postFormPage.clickCancel();
		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(), Constants.postsPageUrl);
	}
}
