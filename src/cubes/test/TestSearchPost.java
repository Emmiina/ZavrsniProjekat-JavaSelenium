package cubes.test;

import cubes.main.TestBase;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostListPage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSearchPost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

	@Test
	public void tc01TestSearchPostPage() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.searchPost(postTitle);
		Thread.sleep(2000);

		boolean expectedPostTitle = postListPage.checkPost(postTitle);

		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", expectedPostTitle);
	}
}
