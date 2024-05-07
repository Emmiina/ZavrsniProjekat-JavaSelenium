package cubes.webpages.posts;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import cubes.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PostFormPage {
	
	private final WebDriver driver;
	private final WebDriverWait driverWait;

	//WebElements
	@FindBy(name="title")
	private WebElement wePostTitle;
	@FindBy(name="description")
	private WebElement wePostDescription;
	@FindBy(xpath = "//input[@name='tag_id[]']")
	private List<WebElement> wePostTags;
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

	//Error messages
	@FindBy(id="title-error")
	private WebElement wePostTitleErrorMessage;
	@FindBy(id="description-error")
	private WebElement wePostDescriptionErrorMessage;
	@FindBy(id = "tag_id[]-error")
	private WebElement wePostTagsErrorMessage;
	@FindBy(css = "div.invalid-feedback")
	private WebElement wePostContentErrorMessage;



	public PostFormPage(WebDriver driver,WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;
		
		this.driver.get(Constants.addPostPageUrl);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	public void openPage() {
		driver.get(Constants.addPostPageUrl);
	}
	
	public void addNewPost(String postTitle) {
		wePostTitle.sendKeys(postTitle);
		weButtonSave.click();
	}

	public String addNewRandomPost() {
		Random random = new Random();
		String postTitle = Constants.postTitle + random.nextInt(100);
		wePostTitle.sendKeys(postTitle);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weButtonSave);
		return postTitle;
	}
	
	public void checkMenuLink(String title, String url) {
		WebElement weMenu = driver.findElement(By.xpath("//p[text()='"+title+"']//ancestor::li[2]"));
		
		if(!weMenu.getAttribute("class").equalsIgnoreCase("nav-item has-treeview menu-open")) {
			weMenu.click();
		}
		
		WebElement weLink = driver.findElement(By.xpath("//p[text()='"+title+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(weLink));
		weLink.click();
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);
		
		driver.get(Constants.addPostPageUrl);
	}
	
	public void checkNavigationLink(String title, String url) {
		WebElement weLink = driver.findElement(By.xpath("//a[text()='"+title+"']"));
		weLink.click();
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);
		
		driver.get(Constants.addPostPageUrl);

	}
	
	public void inputPostTitleString(String postTitle) {
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

	public void inputTags(String text) throws InterruptedException {
		for (WebElement postTag : wePostTags) {
			postTag.sendKeys(text);
		}
	}

	public void clickTagString (String postTag) {
		WebElement weTestTag = driver.findElement(By.xpath("//label[@class='form-check-label'] " +
				"[contains(text(),'"+ postTag +"')]/preceding-sibling::input[@name='tag_id[]']"));
		weTestTag.click();
	}

	public void clickTags(){
		for (WebElement postTag : wePostTags) {
			postTag.click();
		}
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

	public void clickSave() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weButtonSave);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weButtonSave);
	}

	public void clickCancel() throws InterruptedException {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weButtonCancel);
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", weButtonCancel);
		}

	public String getErrorMessage(String field) {
		WebElement we = switch (field) {
            case "title" -> wePostTitleErrorMessage;
            case "description" -> wePostDescriptionErrorMessage;
            case "tags" -> wePostTagsErrorMessage;
			case "content" -> wePostContentErrorMessage;
            default -> throw new IllegalStateException("Unexpected value: " + field);
        };
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
