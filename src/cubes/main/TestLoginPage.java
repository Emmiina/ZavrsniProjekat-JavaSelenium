package cubes.main;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLoginPage {

	public static void main(String[] args) throws InterruptedException {
		
		String urlLoginPage = "http://testblog.kurs-qa.cubes.edu.rs/login";
		
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/emina/Downloads/webDriver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();	
		
		driver.get(urlLoginPage);
		driver.manage().window().maximize();
		
		WebElement weEmail = driver.findElement(By.name("email"));
		WebElement wePassword = driver.findElement(By.name("password"));
		WebElement weButton = driver.findElement(By.xpath("//button[@type='submit']"));
		
		weEmail.sendKeys("kursqa@cubes.edu.rs");
		wePassword.sendKeys("cubesqa");
		weButton.click();
		
		WebElement weStarterPage = driver.findElement(By.xpath("//h1[@class='m-0 text-dark']"));
		
		if (weStarterPage.getText().equalsIgnoreCase("Starter Page")) {
			System.out.println("Uspesno ste se ulogovali!");
		}
		else {
			System.out.println("Niste se ulogovali. proverite email i password!");
		}
		
		driver.navigate().to("https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		
		
	//Add tile
		
		WebElement wePostTitle = driver.findElement(By.name("title"));
		
		Random random = new Random();
		
		String postTitle = "Post titleeeeeeeeeeeeeeeeeeeeeeeeeeeeee 1"+random.nextInt(100);
		
		wePostTitle.sendKeys(postTitle);
		
	//Add description
		WebElement wePostDescription = driver.findElement(By.name("description"));
		
		String postDescription = "Descriptioooooooooooooooooooooooooooooooooooooooon";
		
		wePostDescription.sendKeys(postDescription);
		
	//Important
	    WebElement weImportantRadioButtonYes = driver.findElement(By.xpath("//*[@class='custom-control-label']"));
	    
		if (!weImportantRadioButtonYes.isSelected()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", weImportantRadioButtonYes);
            System.out.println("Important Yes radio button clicked.");
        }
		else {
			System.out.println("Important Yes radio button not clicked.");
		}
		
	//Tags
		WebElement wePostTags = driver.findElement(By.name("tag_id[]"));
		String postTags = "";
		wePostTags.click();

	//Content
		
		WebElement wePostContent = driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
		driver.switchTo().frame(wePostContent);
		
		WebElement iframeElement = driver.findElement(By.tagName("p"));
		
		iframeElement.sendKeys("Text inside iframe");
		
		driver.switchTo().defaultContent();
		
	// Save post
		
		Thread.sleep(3000);
		
		WebElement weButtonSave = driver.findElement(By.xpath("//button[@type='submit']"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weButtonSave);
		Thread.sleep(3000);
		weButtonSave.click();
		
	//delete	
		WebElement weDeleteButton = driver.findElement(By.xpath("//td[text()='"+postTitle+"']//ancestor::tr//td[12]//button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", weDeleteButton);
		Thread.sleep(7000);
		weDeleteButton.click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='btn btn-danger']"))));
		
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='toast-message']"))));
		WebElement weProfile = driver.findElement(By.xpath("//i[@class='far fa-user']"));
		weProfile.click();
		
		WebElement weLogout = driver.findElement(By.xpath("//a[@href='https://testblog.kurs-qa.cubes.edu.rs/logout']"));
		weLogout.click();
		driver.close();

	}

}
