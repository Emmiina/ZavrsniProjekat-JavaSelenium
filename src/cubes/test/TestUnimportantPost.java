package cubes.test;

import static org.junit.Assert.*;

import java.time.Duration;

import cubes.constants.Constants;
import cubes.main.TestBase;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.webpages.LoginPage;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUnimportantPost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

	@Test
	public void tc01TestCancelOnImportant() throws InterruptedException {
		postListPage.clickOnAddNewPost();
		
		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnImportantActionButton(postTitle, Constants.importantPost);
		
		postListPage.clickOnDialogCancelButton(postListPage.weParentImportantDialog);
		boolean expectedPostTitle = postListPage.checkPost(postTitle);
		
		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostTitle);
	}

	@Test
	public void tc02TestImportantPost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnImportantActionButton(postTitle, Constants.importantPost);

		postListPage.clickOnDialogImportantButton();
		Thread.sleep(2000);
		boolean expectedPostStatus = postListPage.isPostIsImportant(postTitle, Constants.statusYes);

		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostStatus);
	}

	@Test
	public void tc03TestUnimportantPost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnEditActionButton(postTitle);
		postFormPage.inputImportantStatus(Constants.importantPost);
		postFormPage.clickSave();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(1000);
		postListPage.clickOnImportantActionButton(postTitle, Constants.unimportantPost);

		postListPage.clickOnDialogUnimportantButton();
		Thread.sleep(2000);
		boolean expectedPostStatus = postListPage.isPostIsImportant(postTitle, Constants.statusNo);
		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostStatus);
	}
}
