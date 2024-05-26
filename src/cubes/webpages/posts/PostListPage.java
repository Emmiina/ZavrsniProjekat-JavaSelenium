package cubes.webpages.posts;

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

	public final String weParentDisablePostDialog = "//h4[text()='Disable Post']//parent::div//parent::div[@class='modal-content']";
	public final String weParentEnablePostDialog = "//h4[text()='Enable Post']//parent::div//parent::div[@class='modal-content']";
	public final String weParentDeleteDialog = "//h4[text()='Delete Post']//parent::div//parent::div[@class='modal-content']";
	public final String weParentImportantDialog = "//h4[text()='Set as Important Post']//parent::div//parent::div[@class='modal-content']";
	public final String weParentUnimportantDialog = "//h4[text()='Set as Unimportant Post']//parent::div//parent::div[@class='modal-content']";

	@FindBy(xpath = "//div[@id='entities-list-table_filter']//input[@type='search']")
	private WebElement weSearchInputBar;
    @FindBy(id = "toast-container")
	private WebElement weSuccessSavedMessage;
	@FindBy(xpath = "//th[@class='sorting_asc']")
	private WebElement weSortingPosts;
	@FindBy(xpath = "//a[@class='btn btn-success']")
	private WebElement weAddNewPost;
	@FindBy(xpath = weParentDeleteDialog + "//button[text()='Delete']")
	private WebElement weDialogDelete;
	@FindBy(xpath = weParentDisablePostDialog + "//button[@class='btn btn-danger']")
	private WebElement weDialogDisable;
	@FindBy(xpath = weParentEnablePostDialog + "//button[@class='btn btn-success']")
	private WebElement weDialogEnable;
	@FindBy(xpath =  weParentUnimportantDialog + "//button[@class='btn btn-danger']")
	private WebElement weDialogUnimportant;
	@FindBy(xpath = weParentImportantDialog + "//button[@class='btn btn-success']")
	private WebElement weDialogImportant;
	
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

	public void clickOnAddNewPost() {
		weAddNewPost.click();
	}

	public void checkSuccessMessage(){
		driverWait.until(ExpectedConditions.visibilityOf(weSuccessSavedMessage));
	}
	
	public boolean checkPost(String postTitle) {
		WebElement postTitleResult = driver.findElement(By.xpath("//td[text()='"+postTitle+"']"));
		return postTitleResult.isDisplayed();
	}

	public void searchPost(String postTitle) {
		weSearchInputBar.sendKeys(postTitle);
	}

	public boolean isPostInList(String postTitle) {
		return driver.findElements(By.xpath("//td[text()='" + postTitle + "']")).size() > 0;
	}

	public boolean isPostIsDisabled(String postTitle, String status) {
		return driver.findElements(By.xpath("//td[text()='" + postTitle + "']/preceding-sibling::td//span[text()='"+status+"']")).size() > 0;
	}

	public boolean isPostIsImportant(String postTitle, String status) {
		return driver.findElements(By.xpath("//td[text()='" + postTitle + "']/preceding-sibling::td//span[text()='"+status+"']")).size() > 0;
	}

	public void clickOnSortingButton(){
		driverWait.until(ExpectedConditions.visibilityOf(weSortingPosts));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weSortingPosts);
	}

	public void clickOnViewPostActionButton(String postTitle){
		String postTitleUrl = postTitle.toLowerCase();
		WebElement weViewButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//div[@class='btn-group']//a[contains(@href,'"+postTitleUrl+"')]"));
		driverWait.until(ExpectedConditions.visibilityOf(weViewButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weViewButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weViewButton);
	}

	public void checkPostTitleNavigationBrand(String postTitle){
		WebElement wePostTitle = driver.findElement(By.xpath("//a[text()='"+postTitle+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(wePostTitle));
	}

	public void clickOnEditActionButton(String postTitle) {
		WebElement weEditPost = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//div[@class='btn-group']//a[contains(@href,'edit')]"));
		driverWait.until(ExpectedConditions.visibilityOf(weEditPost));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weEditPost);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weEditPost);
	}

	public void clickOnDeleteActionButton(String postTitle) {
		WebElement weDeleteButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//div[@class='btn-group']//button[@data-action='delete']"));
		driverWait.until(ExpectedConditions.visibilityOf(weDeleteButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDeleteButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDeleteButton);
	}

	public void clickOnDisableActionButton(String postTitle) {
		WebElement weDisableButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//div[@class='btn-group']//button[@data-action='disable']"));
		driverWait.until(ExpectedConditions.visibilityOf(weDisableButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDisableButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDisableButton);
	}

	public void clickOnEnableActionButton(String postTitle) {
		WebElement weDisableButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//div[@class='btn-group']//button[@data-action='enable']"));
		driverWait.until(ExpectedConditions.visibilityOf(weDisableButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDisableButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDisableButton);
	}

	public void clickOnImportantActionButton(String postTitle, String status) {
		WebElement weImportantButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//div[@class='btn-group']//button[@data-action='"+status+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(weImportantButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weImportantButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weImportantButton);
	}

	public void clickOnDialogCancelButton(String dialog) {
        WebElement weDialogCancel = driver.findElement(By.xpath(dialog + "//button[text()='Cancel']"));
		driverWait.until(ExpectedConditions.visibilityOf(weDialogCancel));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDialogCancel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDialogCancel);
	}
	public void clickOnDialogDeleteButton() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogDelete));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDialogDelete);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDialogDelete);
	}

	public void clickOnDialogDisableButton() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogDisable));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDialogDisable);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDialogDisable);
	}

	public void clickOnDialogEnableButton() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogEnable));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDialogEnable);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDialogEnable);
	}

	public void clickOnDialogUnimportantButton() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogUnimportant));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDialogUnimportant);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDialogUnimportant);
	}

	public void clickOnDialogImportantButton() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogImportant));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDialogImportant);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", weDialogImportant);
	}
}
