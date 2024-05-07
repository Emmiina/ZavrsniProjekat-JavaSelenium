package cubes.test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

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
public class TestDeletePost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

	private String postTitle;

	@Test
	public void tc01TestCancelOnDelete() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		postTitle = postFormPage.addNewRandomPost();

		postFormPage.inputPostTitleString(postTitle);
		postFormPage.inputDescriptionString(Constants.postDescription);
		postFormPage.clickTagString(Constants.tagTest);
		postFormPage.inputContentString("Text inside iframe");
		postFormPage.clickSave();

		postListPage.clickOnDeletePost(postTitle);
		
		postListPage.clickOnDialogCancel();
		
		String expectedPostTitle = postListPage.checkPost(postTitle);
		
		assertEquals(postTitle, expectedPostTitle);
	}

	@Test
	public void tc02TestDeletePost() {
		postListPage.openPage();

		postListPage.clickOnDeletePost(postTitle);
		postListPage.clickOnDialogDelete();

		assertEquals(postListPage.isPostInList(postTitle), false);
	}
}
