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
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);;

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
		postFormPage.openPage();
		postFormPage.checkNavigationLink("Home", Constants.starterPageUrl);
		postFormPage.openPage();
		postFormPage.checkNavigationLink("Post", Constants.postsPageUrl);
	}
	
	@Test
	public void tc03TestAddEmptyPost() throws InterruptedException {
		postFormPage.openPage();
		postFormPage.inputPostString("");
		postFormPage.clickSave();

		assertEquals(postFormPage.getTitleInputErrorMessage(),"This field is required.");
		assertEquals(postFormPage.getDescriptionInputErrorMessage(),"This field is required.");
	}
	
	@Test
	public void tc04TestAddPostWithShortTitle() throws InterruptedException{
		postFormPage.openPage();
		postFormPage.inputPostString("Post1");
		postFormPage.clickSave();
		
		String errorMessage = postFormPage.getErrorMessage();
		
		assertEquals("Please enter at least 20 characters.", errorMessage);	
	}
	
	@Test
	public void tc05TestAddPostWithCorrectTitleAndShortDescription() throws InterruptedException{
		postFormPage.openPage();
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

		postFormPage.inputPostString(Constants.postTitle);
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
		
		String errorMessage = postFormPage.getErrorMessage("tags");
		
		assertEquals(errorMessage, "This field is required.");
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