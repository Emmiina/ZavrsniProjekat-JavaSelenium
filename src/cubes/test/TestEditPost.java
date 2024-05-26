package cubes.test;

import java.util.Random;

import cubes.constants.Constants;
import cubes.main.TestBase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;

import static org.testng.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEditPost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

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
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();

		Thread.sleep(2000);
		postListPage.clickOnEditActionButton(postTitle);

		String newTitle = "NewNewNewNewNewNewNewNewNew"  + random.nextInt(1000);

		postFormPage.inputPostTitleString(newTitle);

		postFormPage.clickSave();
		postListPage.checkSuccessMessage();

        assertFalse(postListPage.isPostInList(postTitle));

		postListPage.clickOnSortingButton();
		Thread.sleep(1000);
        assertTrue(postListPage.isPostInList(newTitle));
	}

	@Test
	public void tc02TestUpdateEmptyPostTitle() throws InterruptedException {
		postListPage.clickOnAddNewPost();
		
		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();

		Thread.sleep(2000);
		postListPage.clickOnEditActionButton(postTitle);
		
		postFormPage.inputPostTitleString("");
		
		postFormPage.clickSave();

		assertEquals(postFormPage.getTitleInputErrorMessage(),"This field is required.");
	}
	
	@Test
	public void tc03TestUpdatePostWithExistingTitle() throws InterruptedException {
		postListPage.clickOnAddNewPost();
		String postTitle = postFormPage.addNewRandomPost();

		postListPage.clickOnSortingButton();

		Thread.sleep(2000);
		postListPage.clickOnEditActionButton(postTitle);
		
		postFormPage.inputTags("zvejtidkjauiepmuljnspkbo");
		
		postFormPage.clickSave();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(1000);
		assertTrue(postListPage.isPostInList(postTitle));
	}
	
	@Test
	public void tc04TestUpdatePostTitle() throws InterruptedException {
		postListPage.clickOnAddNewPost();
		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();

		Thread.sleep(2000);
		postListPage.clickOnEditActionButton(postTitle);
		
		String newPostTitle = "New PosstPosstPosstPosst "+postFormPage.getPostString();
		
		postFormPage.inputPostTitleString(newPostTitle);
		
		postFormPage.clickSave();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		assertTrue(postListPage.isPostInList(newPostTitle));
	}
}
