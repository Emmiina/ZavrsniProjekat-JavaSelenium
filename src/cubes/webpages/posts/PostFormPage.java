package cubes.webpages.posts;


import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PostFormPage {
	
	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String PAGE_URL="https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";
	//WebElements
	@FindBy(name="title")
	private WebElement wePostTitle;
	@FindBy(name="description")
	private WebElement wePostDescription;
	@FindBy(xpath = "//*[@class='form-check-input']")
	private WebElement wePostTags;
	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	private WebElement wePostContent;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement weButtonSave;
	@FindBy(xpath = "//a[text()='Cancel']")
	private WebElement weButtonCancel;
	@FindBy(xpath = "//i[@class='far fa-user']")
	private WebElement weButtonProfile;
	@FindBy(id = "title-error")
	private WebElement weErrorTitle;
	@FindBy(id = "description-error")
	private WebElement weErrorDescription;
	@FindBy(id = "tag_id[]-error")
	private WebElement weErrorTags;
	@FindBy(xpath = "//*[@class='invalid-feedback']")
	private WebElement weErrorContent;
	
	
	public PostFormPage(WebDriver driver,WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;
		
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}	
	
	public void openPage() {
		driver.get(PAGE_URL);
	}
	
	public void clickSave() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weButtonSave);
		Thread.sleep(2000);
		weButtonSave.click();
	}
	public void clickCancel() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weButtonCancel);
		Thread.sleep(1000);
		weButtonCancel.click();
	}
	
	
	
	public void addNewPost(String postTitle) {
		wePostTitle.sendKeys(postTitle);
		weButtonSave.click();
	}
	public String addNewRandomPost() {
		Random random = new Random();
		String postTitle = "Post "+random.nextInt(100);
		wePostTitle.sendKeys(postTitle);
		weButtonSave.click();
		return postTitle;
	}
	
	public void checkMenuLink(String title, String url) {
		WebElement weMenu = driver.findElement(By.xpath("//p[text()='"+title+"']//ancestor::li[2]"));
		
		if(!weMenu.getAttribute("class").toString().equalsIgnoreCase("nav-item has-treeview menu-open")) {
			weMenu.click();
		}
		
		WebElement weLink = driver.findElement(By.xpath("//p[text()='"+title+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(weLink));
		weLink.click();
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);
		
		driver.get(PAGE_URL);
	}
	
	public void checkNavigationLink(String title, String url) {
		WebElement weLink = driver.findElement(By.xpath("//a[text()='"+title+"']"));
		weLink.click();
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);
		
		driver.get(PAGE_URL);

	}
	
	public void inputPostString(String postTitle) {
		wePostTitle.clear();
		wePostTitle.sendKeys(postTitle);
	}
	public String getPostString() {
		return wePostTitle.getAttribute("value");
	}
	public void inputDescriptionString(String postDescription) {
		wePostDescription.clear();
		wePostDescription.sendKeys(postDescription);
	}
	public String inputTagsString(String postTags) {
		wePostTags.sendKeys(postTags);
	
		wePostTags.click();
		return postTags;
	}
	public void inputContentString(String postContent) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", wePostContent);
		Thread.sleep(1000);
	
		wePostContent.sendKeys(postContent);
		driver.switchTo().frame(wePostContent);

		WebElement iframeElement = driver.findElement(By.tagName("p"));

		iframeElement.sendKeys("");

		driver.switchTo().defaultContent();
		wePostContent.sendKeys(postContent);
	}
	

	public void clickProfile() {
		weButtonProfile.click();
	}
	public void clickLogout() {
		WebElement weLogout = driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt']"));
		driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt']"))));
		driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt']")).click();
		
	}
	public String getErrorMessage() {
		WebElement we = driver.findElement(By.xpath("//*[@class='error invalid-feedback']"));
		return we.getText();
	}
	public String getTitleInputErrorMessage() {
		return weErrorTitle.getText();
		
	}
	public String getDescriptionInputErrorMessage() {
		return weErrorDescription.getText();
	}
	public String getTagsInputErrorMessage() {
		return weErrorTags.getText();
	}
	public String getContentInputErrorMessage() {
		return weErrorContent.getText();
	}
	


}
