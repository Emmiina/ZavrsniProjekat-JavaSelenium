package cubes.test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import cubes.main.TestBase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeletePost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

	@Test
	public void tc01TestCancelOnDelete() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnDeleteActionButton(postTitle);
		
		postListPage.clickOnDialogCancelButton(postListPage.weParentDeleteDialog);

		boolean expectedPostTitle = postListPage.checkPost(postTitle);
		
		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostTitle);
	}

	@Test
	public void tc02TestDeletePost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnDeleteActionButton(postTitle);
		postListPage.clickOnDialogDeleteButton();
		postListPage.checkSuccessMessage();
		Thread.sleep(2000);
        assertFalse(postListPage.isPostInList(postTitle));
	}
}
