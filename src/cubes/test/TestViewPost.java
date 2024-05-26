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
public class TestViewPost extends TestBase {

	PostFormPage postFormPage = new PostFormPage(TestBase.driver, TestBase.wait);
	PostListPage postListPage = new PostListPage(TestBase.driver, TestBase.wait);

	@Test
	public void tc01TestViewPostPage() throws InterruptedException {
		postListPage.openPage();
		postListPage.clickOnAddNewPost();

		String postTitle = postFormPage.addNewRandomPost();
		postListPage.checkSuccessMessage();

		postListPage.clickOnSortingButton();
		Thread.sleep(2000);
		postListPage.clickOnViewPostActionButton(postTitle);
		Thread.sleep(2000);
        var tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		postListPage.checkPostTitleNavigationBrand(postTitle);
		assertTrue("There is no exist "+ postTitle +" post title in the posts list results", driver.getCurrentUrl().contains(postTitle.toLowerCase()));
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}
}
