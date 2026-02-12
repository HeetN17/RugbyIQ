package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Trash {

	WebDriver driver;
	WebDriverWait wait;
	
	public Trash(WebDriver driver) {
		
		this.driver=driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@id='trashed-drills-tab']") 
	WebElement drillTab;
	
	@FindBy(xpath=" //a[@type='submit'][normalize-space()='Restore']")
	WebElement clickRestore;
	
	@FindBy(xpath=" //a[contains(text(),'Empty')]")
	WebElement emptyTrash;
	
	@FindBy(xpath="//a[@type='submit' and normalize-space()='Yes, delete forever']")
	WebElement deleteForever;
	
	
	public void gotoDrill() {
		
		wait.until(ExpectedConditions.visibilityOf(drillTab)).click();
	}
	
	
	public void clickRestoreByName(String drillName) {
	    By restoreBtn = By.xpath(
	        "//a[contains(@class,'restore-link') and @data-trash_drill_name='" + drillName + "']");
	    wait.until(ExpectedConditions.elementToBeClickable(restoreBtn)).click();
	}
	
	public void clickDeleteForeverByName(String drillName) {
	    By deleteBtn = By.xpath(
	        "//a[contains(@class,'delete-link') and @data-trash_drill_name='" + drillName + "']"
	    );
	    wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
	}
	
	public void clickRestoreLink() {
		
		wait.until(ExpectedConditions.visibilityOf(clickRestore)).click();
	}
	
	public void emptyTrash() {
		
		wait.until(ExpectedConditions.visibilityOf(emptyTrash)).click();
	}
	
	public void foreverDelete() {
		
		By confirmDeleteBtn = By.xpath(
			    "//a[contains(@class,'empty-trash-btn') and normalize-space()='Yes, delete forever']"
			);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			WebElement deleteBtn = wait.until(
			    ExpectedConditions.presenceOfElementLocated(confirmDeleteBtn)
			);

			((JavascriptExecutor) driver).executeScript(
			    "arguments[0].scrollIntoView({block:'center'});", deleteBtn
			);

			wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();

	}
	
	public void confirmDeleteForever() {

	    By confirmDelete = By.xpath(
	        "//a[contains(@class,'delete-submit-btn') and normalize-space()='Yes, delete forever']"
	    );

	    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(confirmDelete));

	    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

	    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	public boolean isDrillPresent(String drillName) {
		By drillLocator = By.xpath("//a[contains(@class,'restore-link') and @data-trash_drill_name='" + drillName + "']");
		try {
			// Check if element is present and displayed
			WebElement element = driver.findElement(drillLocator);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
