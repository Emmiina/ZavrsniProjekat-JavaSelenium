package cubes.test;

import cubes.constants.Constants;
import cubes.main.TestBase;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDisableEnablePost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

	@Test
	public void tc01TestCancelOnDisablePost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnDisableActionButton(postTitle);

		postListPage.clickOnDialogCancelButton(postListPage.weParentDisablePostDialog);

		boolean expectedPostTitle = postListPage.checkPost(postTitle);

		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostTitle);
	}

	@Test
	public void tc02TestDisablePost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnDisableActionButton(postTitle);

		postListPage.clickOnDialogDisableButton();
		Thread.sleep(2000);
		boolean expectedPostStatus = postListPage.isPostIsDisabled(postTitle, Constants.disabledPost);

		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostStatus);
	}

	@Test
	public void tc03TestEnablePost() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnDisableActionButton(postTitle);

		postListPage.clickOnDialogDisableButton();

		Thread.sleep(2000);
		postListPage.clickOnEnableActionButton(postTitle);

		postListPage.clickOnDialogEnableButton();
		Thread.sleep(2000);
		boolean expectedPostStatus = postListPage.isPostIsDisabled(postTitle, Constants.enabledPost);

		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostStatus);
	}
}
