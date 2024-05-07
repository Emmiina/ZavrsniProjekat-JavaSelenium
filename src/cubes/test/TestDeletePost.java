package cubes.test;

import static org.junit.Assert.*;

import java.time.Duration;

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
	
	private static ChromeDriver driver;
	private static PostFormPage postFormPage;
	private static PostListPage postListPage;
	
	private static String postTitle;

	@Test
	public void tc1TestCancelOnDelete() {
		postListPage.clickOnAddNewPost();
		
		postTitle = postFormPage.addNewRandomPost();
		
		postListPage.clickOnDeletePost(postTitle);
		
		postListPage.clickOnDialogCancel();
		
		String expectedPostTitle = postListPage.checkPost(postTitle);
		
		assertEquals(postTitle, expectedPostTitle);
	}
/*	@Test
	public void tc2TestDeletePost() {
		postListPage.clickOnAddNewPost();
		
		String postTitle = postFormPage.addNewRandomPost();
		
		postListPage.clickOnDeletePost(postTitle);
		
		postListPage.clickOnDialogDelete();
		
		assertEquals(false, postListPage.isPostInList(postTitle));
		
	}*/
	@Test
	public void tc2TestDeletePost() {
		
		postListPage.clickOnDeletePost(postTitle);
		
		postListPage.clickOnDialogDelete();
		
		assertEquals(false, postListPage.isPostInList(postTitle));
		
	}

}
