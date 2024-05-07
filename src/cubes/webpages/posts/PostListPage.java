package cubes.webpages.posts;

import java.util.ArrayList;

import cubes.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostListPage {
	
	private final WebDriver driver;
	private final WebDriverWait driverWait;
	
	@FindBy(xpath = "//a[@class='btn btn-success']")
	private WebElement weAddNewPost;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement weDialogCancel;
	@FindBy(xpath = "//button[@text = 'Delete']")
	private WebElement weDialogDelete;
	@FindBy(xpath = "//button[@text = 'rvtqaxkilbnwtmxcxyvyye']")
	private WebElement weDialogUnimportant;
	
	//@FindBy(xpath = "//button[@text = '']")
	private WebElement weDialogEdit;

	
	public PostListPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.driverWait = wait;
		this.driver.get(Constants.postsPageUrl);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void openPage() {
		driver.get(Constants.postsPageUrl);
	}
	public void openLinkParentInMenu(String title) {
		WebElement weMenu = driver.findElement(By.xpath("//p[text()='"+title+"']//ancestor::li[2]"));
		
		if(!weMenu.getAttribute("class").equalsIgnoreCase("nav-item has-treeview menu-open")) {
			weMenu.click();
		}
	}
	public void clickOnLinkInMenu(String title) {
		WebElement weLink = driver.findElement(By.xpath("//p[text()='"+title+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(weLink));
		weLink.click();
	}
	public void clickOnNavigationLink(String title) {
		WebElement weLink = driver.findElement(By.xpath("//a[text()='"+title+"']"));
		weLink.click();
	}
	public void clickOnAddNewPost() {
		weAddNewPost.click();
	}
	
	public void deletePost(String postTitle) {
		WebElement weDeleteButton = driver.findElement(By.xpath("//strong[text()='"+postTitle+"']//ancestor::tr//td[12]//button[1]"));
		weDeleteButton.click();
		
		driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i[@class='fas fa-trash']"))));
		
		driver.findElement(By.xpath("//i[@class='fas fa-trash']")).click();
	}
	
	public String checkPost(String postTitle) {
		try {
			WebElement webElementStrong = driver.findElement(By.xpath("//strong[text()='"+postTitle+"']"));
			return webElementStrong.getText();
		} catch (Exception e) {
			return "";
		}
	}
	public boolean isPostInList(String postTitle) {
		return !driver.findElements(By.xpath("//strong[text()='"+postTitle+"']")).isEmpty();
	}
	
	public void clickOnDeletePost(String postTitle) {
		WebElement weDeleteButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//i[@class='fas fa-trash']"));
		//driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDeleteButton);
	}
	public void clickOnEditPost(String postTitle) {
		WebElement weEditPost = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//i[@class='fas fa-edit']"));
		weEditPost.click();
	}
	public void clickOnDialogCancel() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogCancel));
		weDialogCancel.click();
	}
	public void clickOnDialogDelete() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogDelete));
		weDialogDelete.click();
	}
	public void clickOnDisablePost(String postTitle) {
		WebElement weDisableButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button"));
		weDisableButton.click();
	}
	public void clickOnUnimportantPost(String postTitle) {
		WebElement weUnimportantButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button[2]"));
		weUnimportantButton.click();
	}
	public void clickOnDialogUnimportant() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogUnimportant));
		weDialogUnimportant.click();
	}
	public void clickOnDialogEdit() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogEdit));
		weDialogEdit.click();
	}
	public boolean isPostInList1(String postTitle) {
		
		ArrayList<WebElement> wePosts = (ArrayList<WebElement>) driver.findElements(By.xpath("//td[text()='"+postTitle+"']"));
	
		return !wePosts.isEmpty();
	}
}
