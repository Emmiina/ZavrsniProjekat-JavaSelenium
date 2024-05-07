package cubes.test;

import static org.testng.Assert.assertEquals;

import cubes.constants.Constants;
import cubes.main.TestBase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAddPost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

	@Test
	public void tc01TestLinkFromMenu() {
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
	public void tc02TestNavigationLink() {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		postListPage.clickOnAddNewPost();
		postFormPage.checkNavigationLink("Home", Constants.starterPageUrl);

		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		postFormPage.checkNavigationLink("Post", Constants.postsPageUrl);
	}
	
	@Test
	public void tc03TestAddEmptyPost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		postFormPage.inputPostTitleString("");
		postFormPage.clickSave();

		assertEquals(postFormPage.getTitleInputErrorMessage(),"This field is required.");
		assertEquals(postFormPage.getDescriptionInputErrorMessage(),"This field is required.");
	}
	
	@Test
	public void tc04TestAddPostWithShortTitle() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		postFormPage.inputPostTitleString("Post1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage("title");
		
		assertEquals("Please enter at least 20 characters.", errorMessage);	
	}
	
	@Test
	public void tc05TestAddPostWithCorrectTitleAndShortDescription() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		postFormPage.inputPostTitleString(Constants.postTitle);

		postFormPage.inputDescriptionString("dsdsdasdasd");

		postFormPage.clickSave();
		String errorMessage = postFormPage.getErrorMessage("description");

		assertEquals(errorMessage, "Please enter at least 50 characters.");
	}

 	@Test
	public void tc06TestAddPostWithCorrectTitleAndShortDescription() throws InterruptedException{
 		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		postFormPage.inputPostTitleString(Constants.postTitle);
		postFormPage.inputDescriptionString("Test 1");
		postFormPage.clickSave();
	
		String errorMessage = postFormPage.getErrorMessage("description");

		assertEquals("Please enter at least 50 characters.", errorMessage);
	}

	@Test
		public void tc07TestAddPostWithCorrectTitleCorrectDescriptionAndEmptyOthersFields() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		postFormPage.inputPostTitleString(Constants.postTitle);
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage("tags");
		
		assertEquals(errorMessage, "This field is required.");
	}
	
	@Test
	public void tc08TestAddPostWithCorrectTitleCorrectDescriptionCheckTagsNameAndEmptyOthersFields() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		postFormPage.inputPostTitleString(Constants.postTitle);
		postFormPage.inputDescriptionString("test1test1test1test1test1test1test1test1test1test1");
		postFormPage.clickTagString(Constants.tagTest);
		postFormPage.inputContentString("");
		postFormPage.clickSave();
		
		assertEquals(postFormPage.getContentInputErrorMessage(),"The content field is required.");
	}

	@Test
	public void tc09TestAddPost() throws InterruptedException{
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		postFormPage.inputPostTitleString(Constants.postTitle);
		postFormPage.inputDescriptionString(Constants.postDescription);
		postFormPage.clickTagString(Constants.tagTest);
		postFormPage.inputContentString("Text inside iframe");
		postFormPage.clickSave();


		assertEquals(postListPage.isPostInList(Constants.postTitle), false);
	}

	@Test
	public void tc10TestCancel() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		postFormPage.inputPostTitleString(Constants.postTitle);
		postFormPage.clickCancel();
		
		assertEquals(driver.getCurrentUrl(), Constants.postsPageUrl);
	}
}