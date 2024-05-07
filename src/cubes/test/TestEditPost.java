package cubes.test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;

import cubes.constants.Constants;
import cubes.main.TestBase;
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
public class TestUpdatePost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);
	private static String postTitle;

	@Test
	public void tc01TestEditPost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		Random random = new Random();

		String postTitle = Constants.postTitle + random.nextInt(1000);
		String postDescription = "DescriptionDescriptionDescriptionDescriptionDescription "+random.nextInt(1000);

		postFormPage.inputPostTitleString(postTitle);
		postFormPage.inputDescriptionString(postDescription);
		postFormPage.clickTagString(Constants.tagTest);
		postFormPage.inputContentString("Text inside iframe");

		postFormPage.clickSave();

		postListPage.clickOnEditPost(postTitle);

		postFormPage.inputPostTitleString("NewNewNewNewNewNewNewNewNew");

		postFormPage.clickSave();

		assertEquals(postListPage.isPostInList("NewNewNewNewNewNewNewNewNew"), false);
	}

	@Test
	public void tc1TestUpdateEmptyPostTitle() throws InterruptedException {
		postListPage.clickOnAddNewPost();
		
		postTitle = postFormPage.addNewRandomPost();
		
		postListPage.clickOnEditPost(postTitle);
		
		postFormPage.inputPostTitleString("");
		
		postFormPage.clickSave();
		
	//	String error = postFormPage.getErrorMessage();
		
	//	assertEquals("This field is required.", error);	
	}
	
	@Test
	public void tc2TestUpdatePostWithExistingTitle() throws InterruptedException {
		postListPage.clickOnEditPost(postTitle);
		
		postFormPage.inputTags("zvejtidkjauiepmuljnspkbo");
		
		postFormPage.clickSave();
		
//		String error = postFormPage.getErrorMessage();
		
	//	assertEquals("The title has alredy exsist", error);
		
	}
	
	@Test
	public void tc3TestUpdatePostTitle() throws InterruptedException {
		
		postListPage.clickOnEditPost(postTitle);
		
		String newPostTitle = "New PosstPosstPosstPosst "+postFormPage.getPostString();
		
		postFormPage.inputPostTitleString("newPostTitle");
		
		postFormPage.clickSave();
		
		assertEquals(true, postListPage.isPostInList(newPostTitle));

	}
}
