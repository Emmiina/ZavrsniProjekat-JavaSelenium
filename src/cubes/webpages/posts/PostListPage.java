package cubes.webpages.posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostListPage {
	
	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String PAGE_URL="https://testblog.kurs-qa.cubes.edu.rs/admin/posts";
	
	@FindBy(xpath = "//a[@class='btn btn-success']")
	private WebElement weAddNewPost;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement weDialogCancel;
	@FindBy(xpath = "//button[@text = 'Delete']")
	private WebElement weDialogDelete;
	private WebElement weDialogUnimportant;
	private WebElement weDialogUpdate;

	
	public PostListPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.driverWait = wait;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void deletePost(String postTitle) {
		WebElement weDeleteButton = driver.findElement(By.xpath("//strong[text()='"+postTitle+"']//ancestor::tr//td[10]//button"));
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
	
	public void openPage() {
		driver.get(PAGE_URL);
	}
	
	public void clickOnAddNewPost() {
		weAddNewPost.click();
	}
	public void clickOnDeletePost(String postTitle) {
		WebElement weDeleteButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button"));
		weDeleteButton.click();
	}
	public void clickOnDisablePost(String postTitle) {
		WebElement weDisableButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button"));
		weDisableButton.click();
	}
	public void clickOnUnimportantPost(String postTitle) {
		WebElement weUnimportantButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button[2]"));
		weUnimportantButton.click();
	}
	public void clickOnUpdatePost(String postTitle) {
		WebElement weUpdateButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button[2]"));
		weUpdateButton.click();
	}
	public void clickOnDialogCancel() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogCancel));
		weDialogCancel.click();
	}
	public void clickOnDialogDelete() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogDelete));
		weDialogDelete.click();
	}
	public void clickOnDialogUnimportant() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogUnimportant));
		weDialogUnimportant.click();
	}
	public void clickOnDialogUpdate() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogUpdate));
		weDialogUpdate.click();
	}


}
